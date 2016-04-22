package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 09/05/2013
 */
public class PolicyCancellationPage extends BasePageObject
{

    @FindBy(css = "form#policyCancellationBean")
    private WebElement policyCancelationFormContainer;

    @FindBy(css = "input#refundTransactionId")
    private WebElement netbanxRefundReferenceInput;

    @FindBy(css = "button#cancelButton")
    private WebElement cancelButton;

    @FindBy(css = "input#yesIAM")
    private WebElement confirmPolicyCancellationCheckBox;

    public PolicyCancellationPage(WebDriver driver)
    {
        super(driver);
    }

    void enterNetbanxRefundReferenceId(String referenceId)
    {
        enterTextInput(netbanxRefundReferenceInput, referenceId);
    }

    void clickConfirmPolicyCancellation()
    {
        jsClick(confirmPolicyCancellationCheckBox);
    }

    void clickCancelButton()
    {
        jsClick(cancelButton);
    }

    //use this to do a common policy cancellation
    public void defaultCancellationFlow()
    {
        String netbanxReference = driver.findElement(By.id("cancellationTransactionId")).getText();
        enterNetbanxRefundReferenceId(netbanxReference);
        waitForElementVisible(5, cancelButton);
        clickConfirmPolicyCancellation();
        clickCancelButton();
    }
}
