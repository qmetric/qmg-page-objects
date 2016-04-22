package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by vketipisz on 27/11/2015.
 */
public class BackofficeRetryLoanSetupPopUp extends BasePageObject
{

    @FindBy(css = "#modal-anchor-content > div > div:nth-child(1) > input")
    private WebElement sortCodeField;

    @FindBy(css = "#modal-anchor-content > div > div:nth-child(2) > input")
    private WebElement accountNumberField;

    @FindBy(css = "#modal-anchor-content > div > div:nth-child(3) > input")
    private WebElement accountNameField;

    @FindBy(css = "#modal-anchor-content > div > footer > nav > button")
    private WebElement setupNewLoanButton;

    public BackofficeRetryLoanSetupPopUp(WebDriver driver)
    {
        super(driver);
    }

    public BackofficeRetryLoanSetupPopUp enterSortCode(String sortCode)
    {
        sortCodeField.sendKeys(sortCode);
        return this;
    }

    public BackofficeRetryLoanSetupPopUp enterAccountNumber(String accountNumber)
    {
        accountNumberField.sendKeys(accountNumber);
        return this;
    }

    public BackofficeRetryLoanSetupPopUp enterAccountName(String accountName)
    {
        accountNameField.sendKeys(accountName);
        return this;
    }

    public BackofficeRetryLoanSetupPopUp clrearFields()
    {
        sortCodeField.clear();
        accountNumberField.clear();
        accountNameField.clear();
        return this;
    }

    public boolean isSetupNewLoanButtonEnabled()
    {
        return setupNewLoanButton.isEnabled();
    }

    public void clickSetupNewLoan()
    {
        setupNewLoanButton.click();
    }


    public String getErrorMessage()
    {
        return driver.findElement(By.cssSelector("#modal-anchor-content > div > div.cf.error-message > p")).getText();
    }




}
