package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Answer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesDropDownAnswersSection extends BasePageObject
{
    public EnquiriesDropDownAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectDropDownAnswer(String questionId, String answer) throws Exception
    {
        WebElement answerElement = findElement(By.id(questionId));
        selectDropDownValueByVisibleText(answerElement, answer);
    }

    public void getDropDownAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        WebElement dropDownElement = mainQuestionContainer.findElement(By.tagName("select"));
        Select selectElement = new Select(dropDownElement);
        answer.setAnswerValue(selectElement.getFirstSelectedOption().getText());
        answer.setQuestionId(dropDownElement.getAttribute("id"));
    }

    public WebElement getDropDownElement(String questionId)
    {
        return findElement(By.id(questionId));
    }

    public Answer getDropDownAnswer(String questionId) throws Exception
    {
        Answer answer = new Answer();
        WebElement dropDownElement = getVisibleElementFromElementsList(By.id(questionId));
        Select selectElement = new Select(dropDownElement);
        answer.setAnswerValue( selectElement.getFirstSelectedOption().getText());
        answer.setQuestionId(dropDownElement.getAttribute("id"));
        return answer;
    }
}
