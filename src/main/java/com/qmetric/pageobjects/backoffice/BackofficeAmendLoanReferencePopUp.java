package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by vketipisz on 04/12/2015.
 */
public class BackofficeAmendLoanReferencePopUp extends BasePageObject
{

    public BackofficeAmendLoanReferencePopUp(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css = "#modal-anchor-content > div > div.cf > input")
    private WebElement loanReferenceField;

    @FindBy(css = "#modal-anchor-content > div > footer > nav > button")
    private WebElement saveButton;


    public BackofficeAmendLoanReferencePopUp enterLoanReference(String loanReference)
    {
        loanReferenceField.clear();
        loanReferenceField.sendKeys(loanReference);
        return this;
    }

    public void clickSaveButton()
    {
        saveButton.click();
    }

}
