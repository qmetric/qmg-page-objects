package com.qmetric.pageobjects.new_oneaccount;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneAccountLoginPage extends BasePageObject
{
    @FindBy(name = "email")
    WebElement emailInputBox;

    @FindBy(name = "password")
    WebElement passwordInputBox;

    @FindBy(css = "button[type=submit]")
    WebElement submitButton;

    @FindBy(css = "a[role=help]")
    WebElement helpLink;

    public OneAccountLoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void enterEmailAddress(String email)
    {
        enterTextInput(emailInputBox, email);
    }

    public void enterPassword(String password)
    {
        enterTextInput(passwordInputBox, password);
    }

    public void clickOnSubmitButton()
    {
        submitButton.click();
    }

    public void clickOnHelpLink()
    {
        helpLink.click();
    }

    public void login(String email, String password)
    {
        enterEmailAddress(email);
        enterPassword(password);
        clickOnSubmitButton();
    }

    public void waitForPageToLoad()
    {
        OneAccountSpinner oneAccountSpinner = new OneAccountSpinner(driver);
        oneAccountSpinner.waitForSpinnerToFinish();
    }
}
