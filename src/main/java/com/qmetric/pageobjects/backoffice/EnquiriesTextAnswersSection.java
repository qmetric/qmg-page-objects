package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesTextAnswersSection extends BasePageObject
{
    public EnquiriesTextAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void enterTextAndLookup(String questionId, String answer) throws Exception
    {
        enterAnswerText(questionId, answer);
        WebElement lookupButton = findElement(By.cssSelector("button[data-question=\"" + questionId + "\"]"));
        jsClick(lookupButton);
    }

    public void enterVehicleOrLegacyTextAndLookup(String questionId, String answer) throws Exception
    {
        enterAnswerText(questionId, answer);
        WebElement lookupButton = findElement(By.cssSelector("input[type=\"button\"][data-question=\"" + questionId + "\"]"));
        jsClick(lookupButton);
    }

    public void enterNumber(final String questionId, final String answer) throws Exception
    {
        WebElement answerElement = getVisibleElementFromElementsList(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
        enterTextInput(answerElement, answer);
    }

    public void enterAnswerText(final String questionId, final String answer) throws Exception
    {
        WebElement answerElement = getVisibleElementFromElementsList(By.id(questionId));
        enterTextInput(answerElement, answer);
    }

    public Answer getInputAnswer(String questionId) throws Exception
    {
        WebElement answerElement = getVisibleElementFromElementsList(By.id(questionId));
        Answer answer = new Answer();
        answer.setAnswerValue(answerElement.getAttribute("value"));
        answer.setQuestionId(questionId);
        answer.setQuestionType(QuestionType.TEXT);
        return answer;
    }

    public void getInputAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        WebElement textBoxElement = mainQuestionContainer.findElement(By.tagName("input"));
        String questionId = textBoxElement.getAttribute("data-question");
        if (questionId == null)
        {
            questionId = textBoxElement.getAttribute("data-name");
        }
        answer.setQuestionId(questionId);
        answer.setAnswerValue(textBoxElement.getAttribute("value"));
    }

    public void waitForLegacyPolicyLookup()
    {
        webDriverWaitWithPolling(30, 3, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return new DynamicElementHandler<Boolean>()
                {
                    public Boolean handleDynamicElement()
                    {
                        WebElement status = findElement(By.className("status"));
                        return !status.getText().contains("Looking up policy");
                    }
                }.execute();
            }
        });

        new DynamicElementHandler<Void>()
        {
            public Void handleDynamicElement()
            {
                WebElement status = findElement(By.className("status"));
                if (!status.getText().startsWith("No policy found"))
                {
                    webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
                    {
                        @Override public boolean apply(WebDriver webDriver)
                        {
                            final WebElement questionSection = findElement(By.id("question-set"));
                            return !questionSection.getText().contains("Loading");
                        }
                    });
                }
                return null;
            }
        }.execute();
    }

    public void enterRosserLookup(final String questionId, final String answer)
    {
        WebElement rosserLookupElement = findElement(By.id(questionId));
        WebElement rosserLookupTextBox = rosserLookupElement.findElement(By.cssSelector("input"));
        enterTextInput(rosserLookupTextBox, answer);
        WebElement lookupButton = rosserLookupElement.findElement(By.cssSelector("button[data-id=lookup]"));
        jsClick(lookupButton);
    }

    public WebElement getTextElement(final String questionId)
    {
        return findElement(By.id(questionId));
    }
}
