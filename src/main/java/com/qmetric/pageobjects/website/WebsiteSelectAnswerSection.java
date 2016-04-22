package com.qmetric.pageobjects.website;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteSelectAnswerSection extends BasePageObject
{
    public WebsiteSelectAnswerSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectAnswer(WebElement containerElement, String answer)
    {
        final WebElement dropDownElement = containerElement.findElement(By.cssSelector(".dk_container.dk_theme_default.dk-enabled"));
        selectDropDownValue(dropDownElement, answer);
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver input)
            {
                return !dropDownElement.getAttribute("class").contains("dk_open");
            }
        });
    }

    public void selectAnswerForCustomisable(WebElement questionElement, String answer)
    {
        final WebElement dropDownElement = questionElement.findElement(By.cssSelector("div[class='dk_container dk_theme_default dk-enabled']"));
        selectDropDownValue(dropDownElement, answer);
    }

    public void selectExtraForCustomisable(final WebElement extraElement, final String value)
    {
        WebElement dropDownLabel = extraElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']"));
        jsClick(dropDownLabel);
        WebElement optionsElement = extraElement.findElement(By.className("dk_options"));
        List<WebElement> dropDownValues = optionsElement.findElements(By.cssSelector("li a"));
        for (WebElement dropDownValue : dropDownValues)
        {
            if (dropDownValue.getText().equals(value))
            {
                dropDownValue.click();
                break;
            }
        }
    }

    private void selectDropDownValue(final WebElement dropDownElement, final String answer)
    {
        waitForElementVisible(30, dropDownElement);
        WebElement defaultDropDownElement = dropDownElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']"));
        ExpectedConditions.elementToBeClickable(defaultDropDownElement);
        jsClick(defaultDropDownElement);
        WebElement optionsElement = dropDownElement.findElement(By.className("dk_options_inner"));
        waitForElementVisible(30, optionsElement);
        WebElement answerElement = optionsElement.findElement(By.cssSelector("a[data-dk-dropdown-value='" + answer + "']"));
        waitForElementVisible(30, answerElement);
        ExpectedConditions.elementToBeClickable(answerElement);
        jsClick(answerElement);
    }
}
