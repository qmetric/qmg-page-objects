package com.qmetric.pageobjects.gmail;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 03/03/2014
 */


public class GmailLoginPage extends BasePageObject
{

    private static final String EMAIL_INPUT_LOCATOR = "#Email";
    private static final String NEXT = "#next";
    private static final String PASSWORD_INPUT_LOCATOR = "#Passwd";
    private static final String SIGNIN_BUTTON_LOCATOR = "#signIn";

    @FindBy(css = EMAIL_INPUT_LOCATOR) WebElement emailInput;

    @FindBy(css = PASSWORD_INPUT_LOCATOR) WebElement passwordInput;

    @FindBy(css = SIGNIN_BUTTON_LOCATOR) WebElement signInButton;

    @FindBy(css = NEXT) WebElement next;

    public GmailLoginPage(WebDriver driver) {
        super(driver);
    }

    private void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    private void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    private void clickSignInButton() {
        signInButton.click();
    }

    public void openPage() {
        driver.get("http://gmail.com");
    }

    public GmailSearchPage login(String email, String password) {
        enterEmail(email);
        next.click();
        waitForElementPresent(10,PASSWORD_INPUT_LOCATOR);
        enterPassword(password);
        clickSignInButton();
        return PageFactory.initElements(driver, GmailSearchPage.class);
    }
}