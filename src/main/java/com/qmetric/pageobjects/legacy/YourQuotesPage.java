package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourQuotesPage extends BasePageObject
{

    @FindBy(id = "quoteResultsWrapper")
    WebElement quoteResults;

    @FindBy(className = "btnPurple")
    WebElement tempBuyButton;

    @FindBy(css = "div.inner.txtSize11")
    WebElement renewButtonContainer;

    @FindBy(css = "select#excessLevel")
    WebElement excessDropDown;

    @FindBy(css = "button[data-action-url*='LEGAL_EXPENSES_AND_ID_THEFT']")
    WebElement legalExpensesButton;

    @FindBy(id = "enquiryAmendForm")
    WebElement amendEnquiryForm;



    private List<WebElement> quoteRows;

    private WebElement renewalQuoteRow;

    public YourQuotesPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean findAndClickQuote(int qglCode)
    {
        try
        {
            waitForQuotesPage();
        }
        catch (TimeoutException e)
        {
            return false;
        }
        quoteRows = driver.findElements(By.cssSelector("ul.quoteRow:not(.insDeclined)"));
        for (WebElement quoteRow : quoteRows)
        {
            WebElement insurerInfo = quoteRow.findElement(By.cssSelector("li:nth-child(1)"));
            String insurerInfoId = insurerInfo.getAttribute("id");
            int qglId = Integer.valueOf(insurerInfoId.replaceAll("[^0-9]", ""));
            if (qglCode == qglId)
            {
                WebElement button = quoteRow.findElement(By.className("btnPurple"));
                jsClick(button);
                return true;
            }
        }
        return false;
    }

    public void findAndClickQuote()
    {
        waitForQuotesPage();
        renewalQuoteRow = driver.findElement(By.cssSelector("ul.quoteRow:not(.insDeclined)"));
        WebElement insurerInfo = renewalQuoteRow.findElement(By.cssSelector("li:nth-child(1)"));
        String insurerInfoId = insurerInfo.getAttribute("id");
        System.out.println("\nRenewing legacy policy with insurer: " + insurerInfoId);
        WebElement button = renewalQuoteRow.findElement(By.cssSelector(".btnPurple"));
        jsClick(button);
    }

    public void findRenewButton()
    {
        WebElement renewButton = renewButtonContainer.findElement(By.className("btnPurple"));
        jsClick(renewButton);
    }

    public boolean findAndClickRenewButton()
    {
        try
        {
            WebElement renewButton = renewButtonContainer.findElement(By.cssSelector("ul.quoteRow.widthPrimaryInner.insurer button.btnPurple"));
            jsClick(renewButton);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public WebElement getContentWrapper()
    {
        return driver.findElement(By.id("contentWrapper"));
    }

    public int getNumberOfProviders()
    {
        try
        {
            waitForQuotesPage();
        }
        catch (TimeoutException e)
        {
            return 0;
        }
        quoteRows = driver.findElements(By.cssSelector("ul.quoteRow:not(.insDeclined)"));
        return quoteRows.size();
    }

    public void waitForQuotesPage()
    {
        waitForElementVisible(30, "ul.quoteRow");
    }

    /**
     * This only verifies the current url - this is only valid for policy expert website and not for backoffice
     *
     * @return
     */
    public boolean isInQuotesPanel()
    {
        return driver.getCurrentUrl().contains("quote-panel");
    }

    public void selectExcess(String excess) throws Exception
    {
        waitForElementPresent(5, "#excessLevel");
        waitForElementVisible(5, excessDropDown);
        selectDropDownValueByVisibleText(excessDropDown, excess);
        pause(3000);
    }

    public void clickLegalExpensesButton()
    {
        jsClick(legalExpensesButton);
        pause(2000);
        waitForElementVisible(5, legalExpensesButton);
    }

    public String getMyAccountLinkCSS()
    {
        return "#accountLinks > a:nth-child(1)";
    }

    public void amendEnquiry()
    {
        jsClick(amendEnquiryForm.findElement(By.cssSelector("button")));
    }

    public void amendEnquiryBackoffice()
    {
        jsClick(findElement(By.cssSelector(".amendAction")));
    }
}
