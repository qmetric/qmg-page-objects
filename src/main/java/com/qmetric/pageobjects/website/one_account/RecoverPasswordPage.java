package com.qmetric.pageobjects.website.one_account;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/06/2014
 */
public class RecoverPasswordPage extends BasePageObject
{
    @FindBy(id = "email")
    WebElement emailInputBox;

    @FindBy(id = "postcode")
    WebElement postCodeInputBox;

    @FindBy(id = "dob")
    WebElement dateOfBirthInputBox;

    @FindBy(id = "send-button")
    WebElement sendButton;

    public RecoverPasswordPage(WebDriver driver)
    {
        super(driver);
    }

    public void insertEmail(String email)
    {
        LOG.info("Recover Password - inserting email: " + email);
        enterTextInput(emailInputBox, email);
    }

    public void insertPostCode(String postCode)
    {
        LOG.info("Recover Password - inserting postcode: " + postCode);
        enterTextInput(postCodeInputBox, postCode);
    }

    public void insertDateOfBirth(String dateOfBirth)
    {
        LOG.info("Recover Password -  inserting date of birth: " + dateOfBirth);
        enterTextInput(dateOfBirthInputBox, dateOfBirth);
    }

    public void clickSendButton()
    {
        LOG.info("Recover Password - clicking send button");
        sendButton.click();
    }

    public void doRecoverPassword(String email, String postCode, String dateOfBirth)
    {
        insertEmail(email);
        insertPostCode(postCode);
        insertDateOfBirth(dateOfBirth);
        clickSendButton();
    }
}
