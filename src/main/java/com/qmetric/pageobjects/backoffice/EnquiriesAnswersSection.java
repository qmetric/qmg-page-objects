package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import com.qmetric.domain.RandomType;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.QuestionTypeMap;
import com.qmetric.utilities.TimeHelper;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesAnswersSection extends BasePageObject
{
    public final EnquiriesAbiAnswersSection enquiriesAbiAnswersSection;

    public final EnquiriesDateAnswersSection enquiriesDateAnswersSection;

    public final EnquiriesDropDownAnswersSection enquiriesDropDownAnswersSection;

    public final EnquiriesImageAnswerSection enquiriesImageAnswerSection;

    public final EnquiriesRadioButtonAnswersSection enquiriesRadioButtonAnswersSection;

    public final EnquiriesTextAnswersSection enquiriesTextAnswersSection;

    @FindBy(id = "question-set")
    WebElement questionSection;

    public EnquiriesAnswersSection(WebDriver driver)
    {
        super(driver);
        enquiriesAbiAnswersSection = PageFactory.initElements(driver, EnquiriesAbiAnswersSection.class);
        enquiriesDateAnswersSection = PageFactory.initElements(driver, EnquiriesDateAnswersSection.class);
        enquiriesDropDownAnswersSection = PageFactory.initElements(driver, EnquiriesDropDownAnswersSection.class);
        enquiriesImageAnswerSection = PageFactory.initElements(driver, EnquiriesImageAnswerSection.class);
        enquiriesTextAnswersSection = PageFactory.initElements(driver, EnquiriesTextAnswersSection.class);
        enquiriesRadioButtonAnswersSection = PageFactory.initElements(driver, EnquiriesRadioButtonAnswersSection.class);
    }

    public void waitForQuestionSetToLoad()
    {
        waitForElementVisible(30, questionSection);
        Predicate<WebDriver> isNotLoading = new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !questionSection.getText().contains("Loading");
            }
        };
        webDriverWaitWithPolling(30, 2, isNotLoading);
    }

    public void enterAnswers(List<Map<String, String>> answersMap) throws Exception
    {
        waitForQuestionSetToLoad();
        for (Map<String, String> answerMap : answersMap)
        {
            String questionId = answerMap.get("Question Id");
            if(questionId.equals("property_rebuild_value"))
            {
                SharedData.rebuildValue = answerMap.get("Answer");
            }
            String questionType = answerMap.get("Question Type");
            QuestionType type = new QuestionTypeMap().get(questionType);
            String answer = getAnswer(answerMap.get("Answer"));
            String answerType = answerMap.get("Answer Type");
            enterAnswer(questionId, type, answer, answerType);
            if (type.equals(QuestionType.ADDRESS) && StringUtils.isNotEmpty(answer))
            {
                selectRandomAddress(questionId);
            }
            if (type.equals(QuestionType.ROSSERLOOKUP))
            {
                if (!doesWebElementExist(By.cssSelector("div[data-id='rosser-selected-data']")))
                {
                    selectFirstRosserLookupDetails();
                }
            }
        }
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

    public void changePolicyStartDate(String date)
    {
        ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", driver.findElement(By.id("policy_start_date")), date);
    }

    public void selectFirstRosserLookupDetails()
    {
        TimeHelper.waitInSeconds(2);
        WebElement rosserLookupContainer = findElement(By.id("rosserlookup"));
        waitForElementPresent(30, "div[data-id=rosser-data]");
        WebElement rosserData = rosserLookupContainer.findElement(By.cssSelector("div[data-id=rosser-data]"));
        List<WebElement> elementsOfTable = rosserData.findElements(By.cssSelector("table tbody tr"));
        if (elementsOfTable.size() != 0)
        {
            elementsOfTable.get(0).click();
        }
    }

    public String getAnswer(final String answer)
    {
        if (answer.startsWith("<random"))
        {
            String randomAnswer = RandomType.getType(answer).generate();
            if (RandomType.getType(answer) == RandomType.RANDOM_EMAIL)
            {
                if (StringUtils.isNotEmpty(SharedData.currentEnquiryName))
                {
                    SharedData.updateEnquiryEmailAddress(SharedData.currentEnquiryName, randomAnswer);
                }
                SharedData.existingEnquiryMap.put("Email", randomAnswer);
                SharedData.email = SharedData.existingEnquiryMap.get("Email");
            }
            if (RandomType.getType(answer) == RandomType.RANDOM_PHONENO)
            {
                SharedData.existingEnquiryMap.put("Phone Number", randomAnswer);
            }
            return randomAnswer;
        }
        else if (answer.contains("<shared_policy_number>"))
        {
            return SharedData.policyNumber;
        }
        else if (answer.contains("<shared_customer_id>"))
        {
            return SharedData.customerId;
        }
        else
        {
            return answer;
        }
    }

    public void enterAnswer(String questionId, QuestionType questionType, String answer, String answerType) throws Exception
    {
        switch (questionType)
        {
            case RADIO:
            case YESNO:
                enquiriesRadioButtonAnswersSection.selectRadioButtonAnswer(questionId, answer);
                break;
            case NUMBER:
                enquiriesTextAnswersSection.enterNumber(questionId, answer);
                break;
            case CURRENCY:
            case TEXT:
                enquiriesTextAnswersSection.enterAnswerText(questionId, answer);
                break;
            case ABICODE:
                enquiriesAbiAnswersSection.selectAbiCode(questionId, answer, answerType);
                break;
            case SELECT:
                enquiriesDropDownAnswersSection.selectDropDownAnswer(questionId, answer);
                break;
            case DATE:
            case DATENOWORFUTURE:
            case DATEOFBIRTH:
            case DATEMONTHANDYEAR:
                enquiriesDateAnswersSection.selectDateAnswer(questionId, questionType, answer, answerType);
                break;
            case IMAGESELECT:
                enquiriesImageAnswerSection.selectImageAnswer(questionId, answer);
                break;
            case VEHICLE:
                enquiriesTextAnswersSection.enterVehicleOrLegacyTextAndLookup(questionId, answer);
                break;
            case ADDRESS:
                enquiriesTextAnswersSection.enterTextAndLookup(questionId, answer);
                break;
            case ROSSERLOOKUP:
                enquiriesTextAnswersSection.enterRosserLookup(questionId, answer);
                break;
            case LEGACYPOLICYLOOKUP:
                enquiriesTextAnswersSection.enterVehicleOrLegacyTextAndLookup(questionId, answer);
                enquiriesTextAnswersSection.waitForLegacyPolicyLookup();
                break;
        }
    }

    protected void putAnswersToMap(final List<Map<String, String>> answers, final WebElement questionPanelContainer, final WebElement mainQuestionContainer)
    {
        WebElement questionLabel = mainQuestionContainer.findElement(By.tagName("label"));
        Answer answer = new Answer();
        answer.setQuestion(questionLabel.getText());
        answer.setQuestionType(QuestionType.valueOf(mainQuestionContainer.getAttribute("data-type").toUpperCase()));
        switch (answer.getQuestionType())
        {
            case COLLECTION:
                return;
            case SELECT:
                enquiriesDropDownAnswersSection.getDropDownAnswer(mainQuestionContainer, answer);
                break;
            case RADIO:
            case YESNO:
                try
                {
                    enquiriesRadioButtonAnswersSection.getRadioButtonAnswer(questionPanelContainer, answer);
                }
                catch (Exception e)
                {
                    LOG.error("Could not get radio button answer: " + answer);
                }
                break;
            case ABICODE:
                enquiriesAbiAnswersSection.getAbiCodeAnswer(mainQuestionContainer, answer);
                break;
            case DATE:
            case DATEOFBIRTH:
            case DATENOWORFUTURE:
                enquiriesDateAnswersSection.getDateAnswer(mainQuestionContainer, answer);
                break;
            default:
                enquiriesTextAnswersSection.getInputAnswer(mainQuestionContainer, answer);
                break;
        }
        addAnswersToMap(answers, answer);
    }

    private void addAnswersToMap(final List<Map<String, String>> answers, final Answer answer)
    {
        Map<String, String> answerMap = new HashMap<String, String>();
        answerMap.put("Question Id", answer.getQuestionId());
        answerMap.put("Question Type", answer.getQuestionType().toString());
        answerMap.put("Question To Ask", answer.getQuestion());
        answerMap.put("Answer", answer.getAnswerValue());
        answers.add(answerMap);
    }

    public void selectAuditTypeFromDropDowns(String type) throws Exception
    {
        WebElement auditTypeDropDown = driver.findElement(By.cssSelector("select[class=\"select\"]"));
        selectDropDownValueByVisibleText(auditTypeDropDown, type);
    }

    public void putAuditNote(String notes)throws Exception
    {
        WebElement auditTypeDropDown = driver.findElement(By.cssSelector("textarea.input"));
        enterTextInput(auditTypeDropDown, notes);
    }

}
