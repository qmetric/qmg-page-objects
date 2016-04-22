package com.qmetric.pageobjects.website;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteTextInputAnswerSection extends BasePageObject
{
    public WebsiteTextInputAnswerSection(WebDriver driver)
    {
        super(driver);
    }

    public void enterTextInputAnswer(WebElement questionElement, String answer)
    {
        WebElement textBoxInputElement = questionElement.findElement(By.cssSelector("input[type=text]"));
        jsClick(textBoxInputElement);
        textBoxInputElement.clear();
        textBoxInputElement.sendKeys(answer);
        if (textBoxInputElement.getAttribute("class").contains("abicodelookup"))
        {
            waitForAbiAutoCompleteSuggestionsNotVisible();
        }
    }

    public void enterCustomerAnswer(WebElement questionElement, String answerType, String answer)
    {
        WebElement answerElement = questionElement.findElement(By.cssSelector("input[data-id=" + answerType + "]"));
        answerElement.click();
        answerElement.clear();
        answerElement.sendKeys(answer);
    }

    public boolean isAnswerPopulated(WebElement questionElement)
    {
        WebElement textBoxInputElement = questionElement.findElement(By.cssSelector("input[type=text]"));
        return StringUtils.isNotEmpty(textBoxInputElement.getAttribute("value"));
    }

    private void waitForAbiAutoCompleteSuggestionsNotVisible()
    {
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !areAllAutoCompleteSuggestionsInvisible();
            }
        });

        Actions clickTabBuilder = new Actions(driver);
        clickTabBuilder.sendKeys(Keys.TAB).perform();

        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return areAllAutoCompleteSuggestionsInvisible();
            }
        });
    }

    private boolean areAllAutoCompleteSuggestionsInvisible()
    {
        boolean allInvisible = true;
        List<WebElement> autoCompleteSuggestionsElements = findElements(By.cssSelector(".autocomplete-suggestions"));
        for (WebElement autoCompleteSuggestionsElement : autoCompleteSuggestionsElements)
        {
            if (autoCompleteSuggestionsElement.isDisplayed())
            {
                allInvisible = false;
            }
        }
        return allInvisible;
    }

    public String getTextAnswer(WebElement questionElement)
    {
        WebElement textBoxInputElement = questionElement.findElement(By.cssSelector("input[type=text]"));
        return textBoxInputElement.getText();
    }

    public Answer getYesNoAnswer(WebElement questionElement)
    {
        Answer answer = new Answer();
        answer.setQuestionType(QuestionType.YESNO);
        if(questionElement.findElement(By.className("selected")).getText().equals("Yes"))
        {
            answer.setAnswerValue("Yes");
        }
        else
        {
            answer.setAnswerValue("No");
        }
        return answer;
    }
}
