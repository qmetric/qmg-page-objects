package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.domain.Answer;
import com.qmetric.domain.CollectionInfo;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.domain.QuestionType;
import com.qmetric.domain.QuestionTypeMap;
import com.qmetric.utilities.TimeHelper;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class BackOfficeEnquiryPage extends BasePageObject
{
    @FindBy(id = "getQuoteBtn")
    WebElement getQuoteButton;

    @FindBy(css = ".button")
    WebElement auditButton;

    @FindBy(css = ".Audit-Add-Note")
    WebElement addAuditButton;

    @FindBy(css = "section.question.type-legacypolicylookup")
    WebElement legacyLookupSection;

    @FindBy(id = "homepolicynumber")
    WebElement policyNumberField;

    @FindBy(css = "input[value='Lookup Policy']")
    WebElement lookupPolicyButton;

    @FindBy(css = "input[data-id='heating_state_of_repair0']")
    WebElement heatingSystemCheckbox;

    @FindBy(id = "rosserlookup")
    WebElement rosserLookupContainer;

    @FindBy(className = "referrer-holder")
    WebElement referrerInfo;

    @FindBy(id = "imported_claims_collection")
    WebElement claimsSection;

    private EnquiriesAnswersSection enquiriesAnswersSection;

    private EnquiriesCollectionAnswersSection enquiriesCollectionAnswersSection;

    public BackOfficeEnquiryPage(WebDriver driver)
    {
        super(driver);
        enquiriesAnswersSection = PageFactory.initElements(driver, EnquiriesAnswersSection.class);
        enquiriesCollectionAnswersSection = PageFactory.initElements(driver, EnquiriesCollectionAnswersSection.class);
    }

    public void lookupPolicy(String policyNumber)
    {
        waitForElementPresent(10, By.id("section.question.type-legacypolicylookup"));
        policyNumberField.sendKeys(policyNumber);
        lookupPolicyButton.click();
    }

    public void waitForQuestionsToLoad()
    {
        enquiriesAnswersSection.waitForQuestionSetToLoad();
    }

    public void enterAnswers(final List<Map<String, String>> answersMap) throws Exception
    {
        enquiriesAnswersSection.enterAnswers(answersMap);
    }

    public void clickOnGetQuoteButton()
    {
        jsClick(getQuoteButton);
    }

    public void clickOnAddAuditButton()
    {
        addAuditButton.click();
    }

    public void selectFirstRosserLookupDetails()
    {
        enquiriesAnswersSection.selectFirstRosserLookupDetails();
    }

    public void selectRosserLookupDetails(final Map<String, String> lookupRow)
    {
        TimeHelper.waitInSeconds(2);

        WebElement rosserData = rosserLookupContainer.findElement(By.cssSelector("div[data-id=rosser-data]"));
        final WebElement rosserDataTable = rosserData.findElement(By.cssSelector("table"));
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                HtmlTable rosserTable = new HtmlTable(rosserDataTable);
                int rowCounter = 0;
                boolean match = true;
                for (Map<String, String> rosserMap : rosserTable.getTableBodyColumnTextValues())
                {
                    match = true;
                    for (String rossMapKey : rosserMap.keySet())
                    {
                        if (!lookupRow.get(rossMapKey).equals(rosserMap.get(rossMapKey)))
                        {
                            match = false;
                            break;
                        }
                    }
                    if (match)
                    {
                        break;
                    }
                    rowCounter++;
                }
                if (!match)
                {
                    LOG.error("No matching rows found for " + lookupRow + ", actual rows found were: " + rosserTable.getTableBodyColumnTextValues());
                }
                jsClick(rosserTable.getAllTableBodyRows().get(rowCounter).findElement(By.cssSelector("td")));
                return null;
            }
        }.execute();
    }

    public List<Map<String, String>> getReferrerInfo()
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                HtmlTable referrerInfoTable = new HtmlTable(referrerInfo);
                return referrerInfoTable.getTableBodyColumnTextValues();
            }
        }.execute();
    }

    public void selectAddress(final String questionId, final String address) throws Exception
    {
        waitForElementPresent(5, "div[data-name=" + questionId + "]");
        WebElement addressDropDown = findElement(By.cssSelector("div[data-name=" + questionId + "] > select.address-select"));
        waitForElementVisible(5, addressDropDown);
        selectDropDownValueByVisibleText(addressDropDown, address);
        TimeHelper.waitHalfASecond();
    }

    private void selectRandomAddress(final String questionId) throws Exception
    {
        waitForElementPresent(5, "div[data-name=" + questionId + "]");
        WebElement addressDropDown = findElement(By.cssSelector("div[data-name=" + questionId + "] > select.address-select"));
        waitForElementVisible(5, addressDropDown);
        Select select = new Select(addressDropDown);
        int randomIndex = new Random().nextInt(select.getOptions().size() - 1) + 1;
        selectDropDownValueByIndex(addressDropDown, randomIndex);
        TimeHelper.waitHalfASecond();
    }

    public void enterAnswersFromFile(final List<Map<String, String>> answersMap) throws Exception
    {
        waitForQuestionsToLoad();
        CollectionInfo collectionInfo = new CollectionInfo(-1, "");
        for (Map<String, String> answerMap : answersMap)
        {
            String questionId = answerMap.get("Question Id");
            String questionType = answerMap.get("Question Type");
            QuestionType type = new QuestionTypeMap().get(questionType);
            String answer = patchCarExcessAnswer(questionId, enquiriesAnswersSection.getAnswer(answerMap.get("Answer")));
            String answerType = answerMap.get("Answer Type");
            String collectionId = answerMap.get("Collection Question Id");
            if (StringUtils.isNotEmpty(collectionId))
            {
                WebElement collectionQuestionContainer = findElement(By.id(collectionId));
                String collectionIndex = answerMap.get("Collection Index");
                collectionInfo = updateCollectionInfo(collectionInfo, Integer.parseInt(collectionIndex), collectionId, collectionQuestionContainer);
                WebElement latestCollectionQuestionContainer = enquiriesCollectionAnswersSection.getLatestCollectionQuestionContainer(collectionQuestionContainer);
                enquiriesCollectionAnswersSection.selectAnswerForCollection(latestCollectionQuestionContainer, questionId, type, answer, answerType);
            }
            else
            {
                collectionInfo = new CollectionInfo(-1, collectionInfo.previousCollectionId);
                enquiriesAnswersSection.enterAnswer(questionId, type, answer, answerType);
            }
            if (questionType.equals("Address"))
            {
                selectRandomAddress(questionId);
            }
            if (questionType.equals("Rosser Lookup"))
            {
                if (!doesWebElementExist(By.cssSelector("div[data-id='rosser-selected-data']")))
                {
                    enquiriesAnswersSection.selectFirstRosserLookupDetails();
                }
            }
        }
    }

    private String patchCarExcessAnswer(String questionId, String answer)
    {
        if (questionId.equals("Cover_VolXsAmt"))
        {
            return "\u00a3" + answer;
        }
        return answer;
    }

    public Answer getAnswers(String questionId, QuestionType questionType, String answerType) throws Exception
    {
        switch (questionType)
        {
            case TEXT:
                return enquiriesAnswersSection.enquiriesTextAnswersSection.getInputAnswer(questionId);
            case ABICODE:
                return enquiriesAnswersSection.enquiriesAbiAnswersSection.getAbiCodeAnswer(questionId, answerType);
            case YESNO:
                return enquiriesAnswersSection.enquiriesRadioButtonAnswersSection.getYesNoAnswer(questionId);
            case RADIO:
                return enquiriesAnswersSection.enquiriesRadioButtonAnswersSection.getRadioButtonAnswer(questionId);
            case SELECT:
                return enquiriesAnswersSection.enquiriesDropDownAnswersSection.getDropDownAnswer(questionId);
        }
        throw new Exception("Question Type " + questionType + " not implemented yet");
    }

    public boolean isQuestionElementEnabled(String questionId, QuestionType questionType) throws Exception
    {
        return getQuestionElement(questionId, questionType).isEnabled();
    }

    public void enterAnswersForCollection(String collectionId, List<Map<String, String>> answers) throws Exception
    {
        enquiriesCollectionAnswersSection.enterAnswersForCollection(collectionId, answers);
    }

    private WebElement getQuestionElement(String questionId, QuestionType questionType) throws Exception
    {
        switch (questionType)
        {
            case SELECT:
                return enquiriesAnswersSection.enquiriesDropDownAnswersSection.getDropDownElement(questionId);
            case TEXT:
                return enquiriesAnswersSection.enquiriesTextAnswersSection.getTextElement(questionId);
            case DATENOWORFUTURE:
                return (enquiriesAnswersSection.enquiriesDateAnswersSection.getDateAnswerElement(questionId));
        }
        throw new Exception("Question Type " + questionType + " not implemented yet");
    }

    private CollectionInfo updateCollectionInfo(CollectionInfo collectionInfo, int nextCollectionIndex, String existingCollectionId, final WebElement collectionQuestionContainer)
    {
        String previousCollectionId = collectionInfo.previousCollectionId;
        if (!previousCollectionId.equals(existingCollectionId) || nextCollectionIndex > collectionInfo.collectionIndex)
        {
            addAnotherCollection(collectionQuestionContainer);
            previousCollectionId = existingCollectionId;
        }
        return new CollectionInfo(nextCollectionIndex, previousCollectionId);
    }

    private void addAnotherCollection(final WebElement collectionQuestionContainer)
    {
        List<WebElement> addCollectionQuestionButtons = collectionQuestionContainer.findElements(By.className("collectionTrigger"));
        WebElement addCollectionQuestionButton = addCollectionQuestionButtons.get(addCollectionQuestionButtons.size() - 1);
        jsClick(addCollectionQuestionButton);
    }

    public String getValidationError(final String collectionId, final String collectionIndex, final String questionId)
    {
        WebElement errorElement = findElement(By.id("error-" + collectionId + "-" + collectionIndex + "-" + questionId));
        return errorElement.getText();
    }

    public String getValidationError(final String questionId)
    {
        WebElement errorElement = findElement(By.id("error-" + questionId));
        return errorElement.getText();
    }

    public boolean doesValidationErrorExist(String questionId)
    {
        return doesWebElementExist(By.id("error-" + questionId));
    }

    public void toggleAuditWindow()throws Exception
    {
        TimeHelper.waitInSeconds(2);
        jsClick(auditButton);
    }

    public void selectAuditType(String auditType)throws Exception
    {
        enquiriesAnswersSection.selectAuditTypeFromDropDowns(auditType);
    }

    public void enterAuditText(String auditText)throws Exception
    {
        enquiriesAnswersSection.putAuditNote(auditText);
    }

    public List<Map<String, String>> getAuditNoteTable(int expectedRowSize)
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                final WebElement auditMenu = findElement(By.id("right-audit-menu"));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override public boolean apply(final org.openqa.selenium.WebDriver driver)
                    {
                        WebElement auditTable = auditMenu.findElement(By.className("grid"));
                        return auditTable.findElements(By.cssSelector("tbody tr")).size() == expectedRowSize;
                    }
                });
                return new HtmlTable(auditMenu.findElement(By.className("grid"))).getTableBodyColumnTextValues();
            }
        }.execute();
    }

    public void removeAnswersForCollection(final String collectionId)
    {
        enquiriesCollectionAnswersSection.removeCollectionQuestion(collectionId, 1);
    }

    public boolean getQuoteButtonStatus(final String condition)
    {
        if(condition.equalsIgnoreCase("enabled"))
        {
            return getQuoteButton.isEnabled();
        }
        else
        {
            return !getQuoteButton.isEnabled();
        }
    }

    public String getRebuildValue() throws Exception
    {
        return driver.findElement(By.cssSelector("input[data-name=property_rebuild_value]")).getAttribute("value");
    }

    public Optional<String> getWarningMessage()
    {
        try
        {
            return Optional.of(driver.findElement(By.cssSelector(".warning")).getText());
        }
        catch(org.openqa.selenium.NoSuchElementException e)
        {
            return Optional.empty();
        }
    }

    public String getNoClaimsTYear()
    {
        WebElement noOfClaimsYearsDD = driver.findElement(By.cssSelector("#no_claims_discount"));
        return new Select(noOfClaimsYearsDD).getFirstSelectedOption().getText();
    }

    public List<Map<String, String>> getClaims()
    {
        claimsTableVisibility();
        WebElement claimsTable = claimsSection.findElement(By.cssSelector("table.grid"));
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(claimsSection, By.cssSelector("table.grid")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {
                        if (claimsTable.isDisplayed())
                        {
                            return true;
                        }
                        return false;
                    }
                });
                return new HtmlTable(claimsTable).getTableBodyColumnTextValuesWithRowHeader();
            }
        }.execute();
    }

    public boolean claimsTableVisibility()
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                return doesWebElementExist(By.id("imported_claims_collection"));
            }
        }.execute();
    }

    public String getGlobalValidationErrorElementText() {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                WebElement globalErrorSection = findElement(By.id("global-validation-errors"));
                return globalErrorSection.findElement(By.cssSelector("label")).getText();
            }
        }.execute();
    }
}
