package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesAbiAnswersSection extends BasePageObject
{
    public EnquiriesAbiAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectAbiCode(String questionId, String answer, String answerType) throws Exception
    {
        WebElement abiAnswerTypeElement;
        if (answerType.equals("select"))
        {
            abiAnswerTypeElement = findElement(By.cssSelector("select[data-name=\"" + questionId + "\"]"));
            selectDropDownValueByVisibleText(abiAnswerTypeElement, answer);
        }
        else
        {
            abiAnswerTypeElement = findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
            enterTextInput(abiAnswerTypeElement, answer);
            TimeHelper.waitInSeconds(2);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ENTER).perform();
        }
    }

    public Answer getAbiCodeAnswer(String questionId, String answerType) throws Exception
    {
        WebElement abiAnswerTypeElement;
        Answer answer = new Answer();
        answer.setQuestionType(QuestionType.ABICODE);
        answer.setQuestionId(questionId);
        if (answerType.equals("select"))
        {
            abiAnswerTypeElement = findElement(By.cssSelector("select[data-name=\"" + questionId + "\"]"));
        }
        else
        {
            abiAnswerTypeElement = findElement(By.cssSelector("input[data-name=\"" + questionId + "\"]"));
        }
        answer.setAnswerValue(abiAnswerTypeElement.getText());
        return answer;
    }

    public void getAbiCodeAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        if (doesWebElementExist(mainQuestionContainer, By.cssSelector("input[class=\"abicodelookup\"]")))
        {
            WebElement textBoxElement = mainQuestionContainer.findElement(By.cssSelector("input[class=\"abicodelookup\"]"));
            answer.setQuestionId(textBoxElement.getAttribute("data-name"));
            answer.setAnswerValue(textBoxElement.getAttribute("value"));
        }
        else
        {
            WebElement dropDownElement = mainQuestionContainer.findElement(By.cssSelector("select"));
            answer.setQuestionId(dropDownElement.getAttribute("data-name"));
            Select selectElement = new Select(dropDownElement);
            answer.setAnswerValue(selectElement.getFirstSelectedOption().getText());
        }
    }
}
