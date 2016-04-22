package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 03/02/15
 */
public class BOLoginPage extends BasePageObject
{

    @FindBy(id = "j_username") WebElement userNameTextBox;

    @FindBy(id = "j_password") WebElement passwordTextBox;

    @FindBy(id = "submit") WebElement loginButton;

    public BOLoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void open(String url)
    {
        openUrl(url);
        waitForElementPresent(10, "#submit");
        deleteAllCookies();
    }

    public void login(String user, String password)
    {
        enterTextInput(userNameTextBox, user);
        enterTextInput(passwordTextBox, password);
        jsClick(loginButton);
    }
}
