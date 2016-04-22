package com.qmetric.pageobjects.website.one_account;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 16/04/2014
 */

//todo - this is only prepared for home quotes only
public class YourQuotes extends BasePageObject
{
    @FindBy(id = "quotes-content")
    WebElement quotesContent;

    @FindBy(css = ".view-quote.btn")
    WebElement viewQuoteButton;

    private DateTime startDate;
    private DateTime expiryDate;

    public YourQuotes(WebDriver driver)
    {
        super(driver);
    }

    public boolean isDisplayed()
    {
        try
        {
            return quotesContent.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void waitToBeDisplayed()
    {
        waitForElementVisible(5, quotesContent);
        waitForContentOpensCompletely(10);
    }

    public int getNumberOfHomeQuotes()
    {
        return quotesContent.findElements(By.cssSelector("div.home-quotes-content > div")).size();
    }

    public boolean checkQuoteType(String insuranceType)
    {
        boolean sameType = false;
        List<WebElement> quoteWebElements = quotesContent.findElements(By.cssSelector((".quote.l-span12-m > .content > .data > span")));
        for (WebElement quoteWebElement : quoteWebElements)
        {
            String quoteHeading = quoteWebElement.getText();
            if(quoteHeading.startsWith(insuranceType))
            {
                sameType = true;
            }
        }
        return sameType;
    }

    public DateTime getQuoteStartDate() throws ParseException
    {
        List<WebElement> quoteWebElements = quotesContent.findElements(By.cssSelector((".quote.l-span12-m > .content > .data > span")));
        for (WebElement quoteWebElement : quoteWebElements)
        {
            String quoteHeading = quoteWebElement.getText();
            if(quoteHeading.startsWith("Start date:"))
            {
                String[] dateContent = quoteHeading.split(":");
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMM yyyy");
                startDate = formatter.parseDateTime(dateContent[1].trim());
            }
        }
        return startDate;
    }

    public DateTime getQuoteExpiryDate() throws ParseException
    {
        startDate = getQuoteStartDate();
        expiryDate = startDate.plusDays(30);
        return expiryDate;
    }

    private void waitForContentOpensCompletely(int timeoutInSeconds)
    {
        new WebDriverWait(driver, timeoutInSeconds).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !quotesContent.getAttribute("style").contains("overflow");
            }
        });
    }

    public void clickViewQuotesButton()
    {
        viewQuoteButton.click();
    }
}
