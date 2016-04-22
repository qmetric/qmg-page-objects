package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesRadioButtonAnswersSection extends BasePageObject
{
    public EnquiriesRadioButtonAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectRadioButtonAnswer(final String questionId, final String answer)
    {
        int counter = 0;
        boolean radioButtonExists;
        do
        {
            By locator = By.cssSelector("input[data-id=\"" + questionId + counter + "\"]");
            radioButtonExists = doesWebElementExist(locator);
            if (radioButtonExists)
            {
                List<WebElement> radioButtonInputs = findElements(locator);
                for (WebElement radioButtonInput : radioButtonInputs)
                {
                    if (radioButtonInput.getAttribute("value").equals(answer))
                    {
                        String radioId = radioButtonInput.getAttribute("id");
                        WebElement radioLabelButton = findElement(By.cssSelector("label[for=\"" + radioId + "\"]"));
                        if (!radioLabelButton.isDisplayed())
                        {
                            continue;
                        }
                        jsClick(radioLabelButton);
                        break;
                    }
                }
            }
            counter++;
        }
        while (radioButtonExists);
    }

    public void getRadioButtonAnswer(WebElement questionPanelContainer, Answer answer) throws Exception
    {
        List<WebElement> radioButtonElements =
                questionPanelContainer.findElements(By.cssSelector("section[class=\"question type-" + answer.getQuestionType() + "\"] > div.onoff-switch > input"));
        for (WebElement radioButtonElement : radioButtonElements)
        {
            if (radioButtonElement.isSelected())
            {
                String radioId = radioButtonElement.getAttribute("id");
                WebElement radioButtonLabel = getVisibleElementFromElementsList(By.cssSelector("label[class=\"pushup\"][for=\"" + radioId + "\"]"));
                answer.setQuestionId(radioButtonElement.getAttribute("data-name"));
                answer.setAnswerValue(radioButtonLabel.getText());
                break;
            }
        }
    }

    public Answer getYesNoAnswer(String questionId) throws Exception
    {
        List<WebElement> radioAnswerTypeElement;
        Answer answer = new Answer();
        answer.setQuestionType(QuestionType.YESNO);
        answer.setQuestionId(questionId);
        radioAnswerTypeElement = findElements(By.cssSelector("input[data-name=\"" + questionId + "\"]"));

        if(radioAnswerTypeElement.get(0).isSelected())
        {
            answer.setAnswerValue("Yes");
        }
        else
        {
            answer.setAnswerValue("No");
        }
        return answer;
    }

    public Answer getRadioButtonAnswer(String questionId) throws Exception
    {
        List<WebElement> radioAnswerTypeElement;
        Answer answer = new Answer();
        answer.setQuestionType(QuestionType.RADIO);
        answer.setQuestionId(questionId);
        radioAnswerTypeElement = findElements(By.cssSelector("input[data-name=\"" + questionId + "\"]"));

        if(radioAnswerTypeElement.get(0).isSelected())
        {
            answer.setAnswerValue("I agree");
        }
        else
        {
            answer.setAnswerValue("I disagree");
        }
        return answer;
    }
}
