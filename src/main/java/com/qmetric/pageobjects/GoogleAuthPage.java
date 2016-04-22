package com.qmetric.pageobjects;

import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class GoogleAuthPage extends BasePageObject
{
    @FindBy(id = "Email")
    WebElement emailTextBox;

    @FindBy(id = "Passwd")
    WebElement passwordTextBox;

    @FindBy(id = "next")
    WebElement nextButton;

    @FindBy(id = "signIn")
    WebElement loginButton;

    public GoogleAuthPage(WebDriver driver)
    {
        super(driver);
    }

    public void login(final String userId, final String password)
    {
        enterTextInput(emailTextBox, userId);
        nextButton.click();
        enterTextInput(passwordTextBox, password);
        TimeHelper.waitInSeconds(1);
        loginButton.click();
    }
}
