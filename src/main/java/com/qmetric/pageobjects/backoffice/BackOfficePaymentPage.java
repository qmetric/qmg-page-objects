package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class BackOfficePaymentPage extends BasePageObject
{
    @FindBy(css = ".mBoxContent")
    WebElement systemMsgBox;

    public BackOfficePaymentPage(WebDriver driver)
    {
        super(driver);
    }

    public void isPayButtonVisible()
    {
        WebElement payButton = findElement(By.id("payNowButton"));
        waitForElementVisible(30, payButton);
    }

    public void enterCardNumber(String cardNumber)
    {
        WebElement cardNoTextBox = findElement(By.id("cardNo"));
        enterTextInput(cardNoTextBox, cardNumber);
    }

    public void enterCvvCode(String cvvCode)
    {
        WebElement cvvTextBox = findElement(By.id("cvv"));
        enterTextInput(cvvTextBox, cvvCode);
    }

    public void selectExpiryDates()
    {
        WebElement expiryMonthDropDown = findElement(By.id("expiryMonth"));
        WebElement expiryYearDropDown = findElement(By.id("expiryYear"));
        selectDropDownValueByIndex(expiryMonthDropDown, 7);
        selectDropDownValueByIndex(expiryYearDropDown, 3);
    }

    public void clickOnPayButton()
    {
        WebElement payButton = findElement(By.id("payNowButton"));
        jsClick(payButton);
    }

    public void switchToMainFrame()
    {
        driver.switchTo().defaultContent();
    }

    public void payByDirectDebit(final Map<String, String> directDebitDetails)
    {
        enterDirectDebitDetails(directDebitDetails);
        agreeToTermsAndConditionsForLoanPayment();
        clickOnPayButton();
        switchToMainFrame();
    }

    public void enterDirectDebitDetails(final Map<String, String> directDebitDetails)
    {
        WebElement accountHolderNameTextBox = findElement(By.id("acctHolderName"));
        WebElement accountNumberTextBox = findElement(By.id("accountNumber"));
        enterTextInput(accountHolderNameTextBox, directDebitDetails.get("Account Holder"));
        enterSortCodeDetails(directDebitDetails.get("Sort Code"));
        enterTextInput(accountNumberTextBox, directDebitDetails.get("Account Number"));
    }

    private void enterSortCodeDetails(String sortCode)
    {
        String[] sortCodes = sortCode.split("-");
        for (int i = 0; i < sortCodes.length; i++)
        {
            WebElement sortCodeTextBox = findElement(By.id("sortCode" + (i + 1)));
            enterTextInput(sortCodeTextBox, sortCodes[i]);
        }
    }

    public void agreeToTermsAndConditionsForLoanPayment()
    {
        List<WebElement> checkboxes = findElements(By.className("validate-required-check"));
        for (WebElement checkbox : checkboxes)
        {
            jsClick(checkbox);
        }
    }

    public void clickOnBackToQuotesButton()
    {
        WebElement backToQuotesBtn = findElement(By.cssSelector("a[data-action='back-to-quotes']"));
        jsClick(backToQuotesBtn);
    }

    public String getErrorMessage()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                waitForElementVisible(30, systemMsgBox);
                return systemMsgBox.getText();
            }
        }.execute();
    }
}
