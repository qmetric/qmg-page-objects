package com.qmetric.pageobjects.website.one_account;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/06/2014
 */
public class QuoteElement extends BasePageObject
{
    @FindBy(css = "span.data span:nth-child(1)")
    WebElement risk;

    @FindBy(css = "span.data span:nth-child(2)")
    WebElement bestQuote;

    @FindBy(css = "span.data span:nth-child(3)")
    WebElement startDate;

    @FindBy(css = "span.data span:nth-child(4)")
    WebElement quoteExpiryDate;

    @FindBy(css = "span.data a.view-quote")
    WebElement viewQuoteButton;

    public QuoteElement(WebDriver driver)
    {
        super(driver);
    }

    public String getRisk()
    {
        return risk.getText();
    }

    public String getBestQuotes()
    {
        return bestQuote.getText();
    }

    public String getStartDate()
    {
        return startDate.getText();
    }

    public String getQuoteExpiryDate()
    {
        return quoteExpiryDate.getText();
    }

    public void clickViewQuoteButton()
    {
        viewQuoteButton.click();
    }
}
