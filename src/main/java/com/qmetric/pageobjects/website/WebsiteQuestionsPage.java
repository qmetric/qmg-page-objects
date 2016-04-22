package com.qmetric.pageobjects.website;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.qmetric.browser.utility.Browser;
import com.qmetric.domain.QuestionType;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.shared.*;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.QuestionTypeMap;
import com.qmetric.utilities.TimeHelper;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteQuestionsPage extends BasePageObject
{
    private WebsiteRadioButtonAnswersSection websiteRadioButtonAnswersSection;

    private WebsiteSelectAnswerSection websiteSelectAnswerSection;

    private WebsiteTextInputAnswerSection websiteTextInputAnswerSection;

    private WebsiteDateAnswerSection websiteDateAnswerSection;

    private WebsiteImageAnswerSection websiteImageAnswerSection;

    public WebsiteQuestionsPage(WebDriver driver)
    {
        super(driver);
        websiteRadioButtonAnswersSection = PageFactory.initElements(driver, WebsiteRadioButtonAnswersSection.class);
        websiteSelectAnswerSection = PageFactory.initElements(driver, WebsiteSelectAnswerSection.class);
        websiteTextInputAnswerSection = PageFactory.initElements(driver, WebsiteTextInputAnswerSection.class);
        websiteDateAnswerSection = PageFactory.initElements(driver, WebsiteDateAnswerSection.class);
        websiteImageAnswerSection = PageFactory.initElements(driver, WebsiteImageAnswerSection.class);
    }

    public void waitForPageToLoad()
    {
        if (SharedData.BROWSER.equals(Browser.INTERNET_EXPLORER))
        {
            webDriverWaitWithPolling(360, 2, new Predicate<WebDriver>()
            {
                @Override
                public boolean apply(WebDriver driver)
                {
                    return doesWebElementExist(By.className("questions"));
                }
            });
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("questions")));
        }
        else
        {
            webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
            {
                @Override
                public boolean apply(WebDriver driver)
                {
                    return doesWebElementExist(By.className("questions"));
                }
            });
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("questions")));
        }


    }

    public void fillInAnswersFromFile(List<Map<String, String>> answersMap)
    {
        waitForElementVisible(30, findElement(By.id("content")));
        final WebElement containerElement = findElement(By.id("content"));
        int collectionIdx = -1;
        for (final Map<String, String> answerMap : answersMap)
        {
            final String collectionId = answerMap.get("Collection Question Id");
            collectionIdx = updateCollectionIndex(collectionIdx, answerMap, collectionId);
            new DynamicElementHandler<Void>()
            {
                @Override
                public Void handleDynamicElement()
                {
                    if (StringUtils.isNotEmpty(collectionId))
                    {
                        enterCollectionAnswer(collectionId, answerMap);
                    }
                    else
                    {
                        enterAnswerMap(containerElement, answerMap);
                    }
                    if ("Address".equals(answerMap.get("Question Type")))
                    {
                        selectRandomAddressFromPostcodeLookup(answerMap.get("Question Id"));
                    }
                    return null;
                }
            }.execute();
        }
    }

    public String getValidationErrorElementText(String questionId, String dataId) throws Exception
    {
        return getValidationErrorElement(questionId, dataId).getText();
    }

    public void fillInAnswers(List<Map<String, String>> answersMap)
    {
        waitForElementVisible(30, findElement(By.id("content")));
        final WebElement containerElement = findElement(By.id("content"));
        for (final Map<String, String> answerMap : answersMap)
        {
            new DynamicElementHandler<Void>()
            {
                @Override
                public Void handleDynamicElement()
                {
                    enterAnswerMap(containerElement, answerMap);
                    return null;
                }
            }.execute();
        }
    }

    private int updateCollectionIndex(int collectionIdx, final Map<String, String> answerMap, final String collectionId)
    {
        String collectionIndex = answerMap.get("Collection Index");
        if (StringUtils.isNotEmpty(collectionIndex))
        {
            final WebElement collectionContainer = getParentCollectionContainer(collectionId);
            if (collectionIdx == -1)
            {
                collectionIdx = Integer.parseInt(collectionIndex);
            }
            else if (collectionIdx >= 0)
            {
                int nextCollectionIndex = Integer.parseInt(collectionIndex);
                if (nextCollectionIndex > collectionIdx)
                {
                    WebElement saveButton = getSaveButton(collectionId, collectionContainer);
                    jsClick(saveButton);
                    WebElement addAnotherButton = getAddAnotherButton(collectionId, collectionContainer);
                    jsClick(addAnotherButton);
                }
                collectionIdx = nextCollectionIndex;
            }
        }
        return collectionIdx;
    }

    private void selectRandomAddressFromPostcodeLookup(final String questionId)
    {
        WebElement addressOptionsElement = getAddressOptionsElement(questionId);
        List<WebElement> addressOptions = addressOptionsElement.findElements(By.cssSelector("a"));
        int randomIndex = new Random().nextInt(addressOptions.size());
        WebElement randomLink = addressOptions.get(randomIndex);
        jsClick(randomLink);
    }

    private void enterAnswerMap(WebElement parentContainerElement, Map<String, String> answerMap)
    {
        String questionId = answerMap.get("Question Id");
        if(questionId.equals("property_rebuild_value"))
        {
            SharedData.rebuildValue = answerMap.get("Answer");
        }
        String questionType = answerMap.get("Question Type");
        QuestionType type = new QuestionTypeMap().get(questionType);
        String answer = DataPatcher.patchCarExcessAnswer(questionId, getAnswer(answerMap.get("Answer")));
        String answerType = answerMap.get("Answer Type");
        By questionElementLocator = By.cssSelector("section[data-question-id=" + questionId + "]");
        List<WebElement> questionElementsToFind = parentContainerElement.findElements(questionElementLocator);
        WebElement questionElement = null;
        for (WebElement questionElementFound : questionElementsToFind)
        {
            if (questionElementFound.isDisplayed())
            {
                questionElement = questionElementFound;
                break;
            }
        }
        if (questionElement == null)
        {
            LOG.error("Question element with css selector: section[data-question-id=" + questionId + "] and question id " + questionId + " not visible / found. Please check.");
        }
        try
        {
            scrollTo(questionElement);
            enterAnswer(questionElement, questionId, type, answer, answerType);
        }
        catch (Exception e)
        {
            LOG.error("An exception has occurred for question with question Id: " + questionId + " \n" + e.getMessage());
        }
    }

    private String getAnswer(final String answer)
    {
        if (answer.startsWith("<random"))
        {
            String randomAnswer = RandomType.getType(answer).generate();
            if (RandomType.getType(answer) == RandomType.RANDOM_EMAIL)
            {
                SharedData.existingEnquiryMap.put("Email", randomAnswer);
            }
            if (RandomType.getType(answer) == RandomType.RANDOM_PHONENO)
            {
                SharedData.existingEnquiryMap.put("Phone Number", randomAnswer);
            }
            if (RandomType.getType(answer) == RandomType.RANDOM_WORKFLOW_EMAIL)
            {
                SharedData.existingEnquiryMap.put("Email", randomAnswer);
            }
            return randomAnswer;
        }
        else
        {
            return answer;
        }
    }

    private Keys getKeys(String key)
    {
        if (key.equalsIgnoreCase("tab"))
        {
            return Keys.TAB;
        }
        else if(key.equalsIgnoreCase("enter"))
        {
            return Keys.ENTER;
        }
        else
        {
            return Keys.END;
        }
    }

    private WebElement getValidationErrorElement(String questionId, String dataId) throws Exception
    {
        return new DynamicElementHandler<WebElement>()
        {
            @Override
            public WebElement handleDynamicElement()
            {
                WebElement questionContainer = findElement(By.cssSelector("section[data-question-id=" + questionId + "]"));
                waitForElementVisible(30, questionContainer);
                WebElement errorElement = null;
                if (doesWebElementExist(questionContainer, By.cssSelector("span.error.validation")))
                {
                    if (StringUtils.isEmpty(dataId))
                    {
                        errorElement = questionContainer.findElement(By.cssSelector("span.error.validation"));
                    }
                    else
                    {
                        errorElement = questionContainer.findElement(By.cssSelector("span[class='error validation'][data-id='" + dataId + "']"));
                    }
                }
                if (errorElement == null)
                {
                    try {
                        throw new Exception("Element with question Id " + questionId + " and data Id " + dataId + " is not found");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return errorElement;
            }
        }.execute();
    }

    private void enterCollectionAnswerMap(String collectionId, WebElement parentContainerElement, Map<String, String> answerMap, String answer)
    {
        String questionId = answerMap.get("Question Id");
        String questionType = answerMap.get("Question Type");
        QuestionType type = new QuestionTypeMap().get(questionType);
        String answerType = answerMap.get("Answer Type");
        WebElement questionElementContainer = getQuestionElementContainer(collectionId, parentContainerElement, questionId);
        try
        {
            scrollTo(questionElementContainer);
            enterAnswer(questionElementContainer, questionId, type, answer, answerType);
        }
        catch (Exception e)
        {
            LOG.error("Question: " + questionId + " not found. \n" + e.getMessage());
        }
    }

    private WebElement getQuestionElementContainer(final String collectionId, WebElement parentContainerElement, final String questionId)
    {
        By questionElementLocator = By.cssSelector("section[data-question-id=" + questionId + "]");
        List<WebElement> questionElements = getQuestionElements(collectionId, parentContainerElement, questionElementLocator);
        WebElement questionElementContainer = null;
        for (WebElement questionElement : questionElements)
        {
            if (questionElement.isDisplayed())
            {
                questionElementContainer = questionElement;
                break;
            }
        }
        if (questionElementContainer == null)
        {
            questionElementContainer = questionElements.get(questionElements.size() - 1);
        }
        return questionElementContainer;
    }

    private List<WebElement> getQuestionElements(final String collectionId, WebElement parentContainerElement, final By questionElementLocator)
    {
        List<WebElement> questionElements = parentContainerElement.findElements(questionElementLocator);
        if (questionElements.isEmpty())
        {
            parentContainerElement = getAlternativeParentCollectionContainer(collectionId);
            questionElements = parentContainerElement.findElements(questionElementLocator);
        }
        return questionElements;
    }

    private void enterAnswer(WebElement questionElement, String questionId, QuestionType questionType, String answer, String answerType) throws Exception
    {
        switch (questionType)
        {
            case IMAGESELECT:
                websiteImageAnswerSection.selectImageAnswer(questionElement, answer);
                break;
            case CUSTOMERLOGIN:
                websiteTextInputAnswerSection.enterCustomerAnswer(questionElement, answerType, answer);
                break;
            case SELECT:
                websiteSelectAnswerSection.selectAnswer(questionElement, answer);
                break;
            case RADIO:
                websiteRadioButtonAnswersSection.selectRadioButtonAnswer(questionElement, answer);
                break;
            case YESNO:
                websiteRadioButtonAnswersSection.selectYesNoAnswer(questionElement, answer);
                break;
            case NUMBER:
            case CURRENCY:
            case TEXT:
                websiteTextInputAnswerSection.enterTextInputAnswer(questionElement, answer);
                break;
            case VEHICLE:
            case ADDRESS:
                doLookup(questionId, answer, questionElement);
                break;
            case DATE:
            case DATENOWORFUTURE:
            case DATEOFBIRTH:
            case DATEMONTHANDYEAR:
                websiteDateAnswerSection.selectDateAnswer(questionElement, questionType, answer, answerType);
                break;
            case ABICODE:
                if (answerType.equals("select"))
                {
                    websiteSelectAnswerSection.selectAnswer(questionElement, answer);
                }
                else
                {
                    websiteTextInputAnswerSection.enterTextInputAnswer(questionElement, answer);
                }
                break;
            default:
                break;
        }
    }

    public String getPopulatedAnswer(Map<String, String> questionMap) throws Exception
    {
        waitForElementVisible(30, findElement(By.id("content")));
        final WebElement containerElement = findElement(By.id("content"));
        String questionId = questionMap.get("Question Id");
        String questionType = questionMap.get("Question Type");
        QuestionType type = new QuestionTypeMap().get(questionType);
        By questionElementLocator = By.cssSelector("section[data-question-id=" + questionId + "]");
        List<WebElement> questionElementsToFind = containerElement.findElements(questionElementLocator);
        WebElement questionElement = null;
        for (WebElement questionElementFound : questionElementsToFind)
        {
            if (questionElementFound.isDisplayed())
            {
                questionElement = questionElementFound;
                break;
            }
        }
        return getPopulatedAnswer(questionElement, type);
    }


    private String getPopulatedAnswer(WebElement questionElement, QuestionType questionType) throws Exception
    {
        switch (questionType)
        {
            case TEXT:
                return websiteTextInputAnswerSection.getTextAnswer(questionElement);
            case YESNO:
                return websiteTextInputAnswerSection.getYesNoAnswer(questionElement).getAnswerValue();
            default:
                throw new Exception("Type " + questionType + " not implemented yet");
        }
    }

    public boolean isAnswerPopulated(Map<String, String> questionMap) throws Exception
    {
        waitForElementVisible(30, findElement(By.id("content")));
        final WebElement containerElement = findElement(By.id("content"));
        String questionId = questionMap.get("Question Id");
        String questionType = questionMap.get("Question Type");
        QuestionType type = new QuestionTypeMap().get(questionType);
        String answerType = questionMap.get("Answer Type");
        By questionElementLocator = By.cssSelector("section[data-question-id=" + questionId + "]");
        List<WebElement> questionElementsToFind = containerElement.findElements(questionElementLocator);
        WebElement questionElement = null;
        for (WebElement questionElementFound : questionElementsToFind)
        {
            if (questionElementFound.isDisplayed())
            {
                questionElement = questionElementFound;
                break;
            }
        }
        return isAnswerPopulated(questionElement, type);
    }

    private boolean isAnswerPopulated(WebElement questionElement, QuestionType questionType) throws Exception
    {
        switch (questionType)
        {
            case TEXT:
                return websiteTextInputAnswerSection.isAnswerPopulated(questionElement);
            default:
                throw new Exception("Type " + questionType + " not implemented yet");
        }
    }

    private void doLookup(String questionId, String answer, WebElement questionElement)
    {
        websiteTextInputAnswerSection.enterTextInputAnswer(questionElement, answer);
        final WebElement lookupButton = questionElement.findElement(By.cssSelector("button[data-question='" + questionId + "']"));
        lookupButton.click();

        if (questionId.equals("Vehicle_Lookup"))
        {
            webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
            {
                @Override
                public boolean apply(WebDriver webDriver)
                {
                    return !lookupButton.isDisplayed();
                }
            });
        }
    }

    public void selectAddressFromPostcodeLookup(String questionId, String address)
    {
        WebElement addressOptionsElement = getAddressOptionsElement(questionId);
        jsClick(addressOptionsElement.findElement(By.cssSelector("a[data-dk-dropdown-value=\"" + address + "\"]")));
    }

    private WebElement getAddressOptionsElement(final String questionId)
    {
        final WebElement questionElement = findElement(By.cssSelector("section[data-question-id=" + questionId + "]"));
        webDriverWaitWithPolling(120, 3, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                WebElement outputElement = questionElement.findElement(By.cssSelector("div.output"));
                return !outputElement.getText().contains("Loading...");
            }
        });
        WebElement outputElement = questionElement.findElement(By.cssSelector("div.output"));
        List<WebElement> dateDropDownElements = outputElement.findElements(By.id("dk_container_undefined"));
        WebElement addressDropDownElement = dateDropDownElements.get(0);
        jsClick(addressDropDownElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']")));
        return addressDropDownElement.findElement(By.className("dk_options_inner"));
    }

    public void requestQuotes()
    {
        final WebElement requestQuoteButton = findElement(By.id("sendQuestions"));
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                jsClick(requestQuoteButton);
                TimeHelper.waitInSeconds(2);
                if(driver.getTitle().contains("Your details"))
                    jsClick(requestQuoteButton);
                return null;
            }
        }.execute();
    }

    public void enterCollectionAnswer(final String collectionId, final Map<String, String> answerMap)
    {
        final WebElement collectionContainer = getParentCollectionContainer(collectionId);
        if (collectionContainer == null)
        {
            LOG.error("Collection question element with css selector: section[data-question-id=" + collectionId + "] and question id " + collectionId +
                      " not visible / found. Please check.");
        }
        final String answer = getAnswer(answerMap.get("Answer"));
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                enterCollectionAnswerMap(collectionId, collectionContainer, answerMap, answer);
                return null;
            }
        }.execute();
    }

    public void enterCollectionAnswers(final String collectionId, final List<Map<String, String>> answersMap)
    {
        final WebElement collectionContainer = getParentCollectionContainer(collectionId);
        if (collectionContainer == null)
        {
            LOG.error("Collection question element with css selector: section[data-question-id=" + collectionId + "] and question id " + collectionId +
                      " not visible / found. Please check.");
        }
        boolean hasAnswers = true;
        int answerCount = 1;
        do
        {
            for (final Map<String, String> answerMap : answersMap)
            {
                final String answer = getAnswer(answerMap.get("Collection Answer " + answerCount));
                new DynamicElementHandler<Void>()
                {
                    @Override
                    public Void handleDynamicElement()
                    {
                        enterCollectionAnswerMap(collectionId, collectionContainer, answerMap, answer);
                        return null;
                    }
                }.execute();
            }
            if (hasAnswers)
            {
                answerCount++;
                WebElement saveButton = getSaveButton(collectionId, collectionContainer);
                scrollTo(saveButton);
                jsClick(saveButton);
                String nextAnswer = answersMap.get(0).get("Collection Answer " + answerCount);
                if (!Strings.isNullOrEmpty(nextAnswer))
                {
                    WebElement addAnotherButton = getAddAnotherButton(collectionId, collectionContainer);
                    jsClick(addAnotherButton);
                }
                else
                {
                    hasAnswers = false;
                }
            }
        }
        while (hasAnswers);
    }

    public void removeCollectionAnswers(final String collectionId)
    {
        final WebElement collectionContainer = getParentCollectionContainer(collectionId);
        List<WebElement> collectionQuestionContainers = collectionContainer.findElements(By.cssSelector("div.collection"));
        for (WebElement collectionQuestionContainer : collectionQuestionContainers)
        {
            WebElement arrowButton = collectionQuestionContainer.findElement(By.cssSelector("a.edit-this.icon-arrow-table"));
            jsClick(arrowButton);
            WebElement removeButton = collectionQuestionContainer.findElement(By.className("remove-collection"));
            jsClick(removeButton);
        }
    }

    public String getCustomerStatementText()
    {
        return findElement(By.cssSelector("section[data-question-id=customer_login] span[class='l-span12-m l-span12-ts l-span12-d'] p")).getText();
    }

    public boolean customerStatementElementDoesNotExist()
    {
        By customerStatementLocator = By.cssSelector("section[data-question-id=customer_login] span[class='l-span12-m l-span12-ts l-span12-d'] p");
        waitForElementNotVisible(25, customerStatementLocator);
        return !doesWebElementExist(customerStatementLocator);
    }

    private WebElement getSaveButton(final String collectionId, final WebElement collectionContainer)
    {
        By saveButtonLocator = By.cssSelector("button[class='button save-collection actions-quote pull-left mgn-E']");
        List<WebElement> saveButtons = collectionContainer.findElements(saveButtonLocator);
        if (saveButtons.isEmpty())
        {
            saveButtons = getAlternativeParentCollectionContainer(collectionId).findElements(saveButtonLocator);
        }
        return saveButtons.get(saveButtons.size() - 1);
    }

    private WebElement getAddAnotherButton(final String collectionId, final WebElement collectionContainer)
    {
        By addAnotherButtonLocator = By.cssSelector("button[class='button add-another actions-quote']");
        List<WebElement> addAnotherButtons = collectionContainer.findElements(addAnotherButtonLocator);
        if (addAnotherButtons.isEmpty())
        {
            addAnotherButtons = getAlternativeParentCollectionContainer(collectionId).findElements(addAnotherButtonLocator);
        }
        return addAnotherButtons.get(addAnotherButtons.size() - 1);
    }

    private WebElement getParentCollectionContainer(final String collectionId)
    {
        List<WebElement> collectionContainers = findElements(By.cssSelector("section[data-question-id='" + collectionId + "']"));
        for (WebElement collectionContainerToFind : collectionContainers)
        {
            if (collectionContainerToFind.isDisplayed())
            {
                return collectionContainerToFind;
            }
        }
        return null;
    }

    /* We need this method because of question ids being duplicated and the UI is getting confused */
    private WebElement getAlternativeParentCollectionContainer(String collectionId)
    {
        List<WebElement> collectionContainers = findElements(By.cssSelector("span[data-related='" + collectionId + "']"));
        for (WebElement collectionContainerToFind : collectionContainers)
        {
            if (collectionContainerToFind.isDisplayed())
            {
                return collectionContainerToFind;
            }
        }
        return new RemoteWebElement();
    }


    public void clickAddButton(final String collectionId)
    {
        final WebElement collectionContainer = findElement(By.cssSelector("section[data-question-id=" + collectionId + "]"));
        By addAnotherButtonLocator = By.cssSelector("button[class='button add-another actions-quote']");
        WebElement addAnotherButton = collectionContainer.findElement(addAnotherButtonLocator);
        jsClick(addAnotherButton);
    }

    public void removeFirstCollectionAnswer(final String collectionId)
    {
        final WebElement collectionContainer = getParentCollectionContainer(collectionId);
        List<WebElement> collectionQuestionContainers = collectionContainer.findElements(By.cssSelector("div.collection"));
        WebElement arrowButton = collectionQuestionContainers.get(0).findElement(By.cssSelector("a.edit-this.icon-arrow-table"));
        jsClick(arrowButton);
        WebElement removeButton = collectionQuestionContainers.get(0).findElement(By.className("remove-collection"));
        jsClick(removeButton);
    }

    public String getRebuildValue()
    {
        return findElement(By.cssSelector("section[data-question-id=property_rebuild_value]")).findElement(By.cssSelector("input[type=text]")).getAttribute("value");
    }

    public String getGlobalValidationErrorElementText()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                WebElement globalErrorSection = findElement(By.cssSelector(".global-errors"));
                return globalErrorSection.findElement(By.cssSelector("label")).getText();
            }
        }.execute();
    }

    public void sendKey(String questionID, String key)
    {
        try
        {
            findElement(By.cssSelector("section[data-question-id="+ questionID +"]")).sendKeys(getKeys(key));
        }
        catch(Exception e)
        {

        }
    }

    public String getPolicyStartDate()
    {
        return findElement(By.cssSelector("section[data-question-id=policy_start_date]")).findElement(By.cssSelector("input[type=text]")).getAttribute("value");
    }

    public ExpectedCondition<WebElement> isPolicyDateModifiable()
    {
        return ExpectedConditions.elementToBeClickable(By.cssSelector("section[data-question-id=policy_start_date]"));
    }

    public void agreeToAllStatements()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                try {
                    List<WebElement> elements = getVisibleElementsFromElementsList(By.className("agree-to-all"));
                    for(WebElement element : elements)
                    {
                       WebElement radioBtn = element.findElement(By.cssSelector("button[data-role=\"select-all\"]"));
                       jsClick(radioBtn);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void disagreeToAllStatements()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                try {
                    List<WebElement> elements = getVisibleElementsFromElementsList(By.className("agree-to-all"));
                    for(WebElement element : elements)
                    {
                        WebElement radioBtn = element.findElement(By.cssSelector("button[data-role=\"display-individual-questions\"]"));
                        jsClick(radioBtn);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public String getRebuildHelpText()
    {
        return findElement(By.className("rebuild-explain")).getText();
    }

    public List<Map<String,String>> getClaims()
    {
        claimsTableVisibility();
        WebElement existingClaimsTable = driver.findElement(By.cssSelector("section[data-question-id=\"previous_claims_collection\"] > article > span > header"));
        WebElement claimsTable = existingClaimsTable.findElement(By.cssSelector("table"));
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(existingClaimsTable, By.cssSelector("table")));
                webDriverWaitWithPolling(30, 2, driver1 -> {
                    if (claimsTable.isDisplayed())
                    {
                        return true;
                    }
                    return false;
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
                WebElement existingClaimsTable = driver.findElement(By.cssSelector("span[class=\"header\"]"));
                return existingClaimsTable.getText().equalsIgnoreCase("Existing claims");
            }
        }.execute();
    }
}