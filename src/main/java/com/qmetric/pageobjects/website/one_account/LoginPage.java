package com.qmetric.pageobjects.website.one_account;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/06/2014
 */
public class LoginPage extends BasePageObject
{
    @FindBy(id = "email")
    WebElement emailInputBox;

    @FindBy(id = "password")
    WebElement passwordInputBox;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "a.lostPassword")
    WebElement lostPasswordButton;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void insertEmail(String email)
    {
        LOG.info("Login - Inserting email: " + email);
        enterTextInput(emailInputBox, email);
    }

    public void insertPassword(String password)
    {
        LOG.info("Login - Inserting password: " + password);
        enterTextInput(passwordInputBox, password);
    }

    public void clickLoginButton()
    {
        LOG.info("Login - clicking login button");
        loginButton.click();
    }

    public void clickLostPasswordButton()
    {
        LOG.info("Login - clicking lost password button");
        lostPasswordButton.click();
    }

    public boolean doLogin(String email, String password)
    {
        int loginAttempts = 0;
        do
        {
            insertEmail(email);
            insertPassword(password);
            TimeHelper.waitInSeconds(1);
            loginAttempts++;
        }
        while (loginAttempts < 5 && (!emailInputBox.getAttribute("value").equals(email) || !passwordInputBox.getAttribute("value").equals(password)));
            clickLoginButton();

        try
        {
            waitForUrlDoesNotContain(5, "login");
            waitForElementNotVisible(20, ".loading");
        }
        catch (TimeoutException e)
        {
            LOG.error("Login - Could not login - bad credentials");
            return false;
        }
        LOG.info("Login - Successful");
        return true;
    }

    public void waitForPageToLoad()
    {
        waitForElementVisible(30, findElement(By.id("one-account")));
    }
}
