package com.qmetric.pageobjects.website.one_account;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 16/04/2014
 */
public class OneAccountMenu extends BasePageObject
{
    @FindBy(css = "a.button.quotes")
    WebElement yourQuotesButton;
    @FindBy(css = "a.button.policies")
    WebElement yourPoliciesButton;
    @FindBy(css = "a.button.settings")
    WebElement settingsButton;
    @FindBy(css = "a.button.navigation-logout-btn")
    WebElement logoutButton;

    public OneAccountMenu(WebDriver driver)
    {
        super(driver);
    }

    public YourQuotes clickYourQuotesButton()
    {
        yourQuotesButton.click();
        YourQuotes yourQuotes = PageFactory.initElements(driver, YourQuotes.class);
        yourQuotes.waitToBeDisplayed();
        return yourQuotes;
    }

    public YourPolicies clickYourPoliciesButton()
    {
        yourPoliciesButton.click();
        YourPolicies yourPolicies = PageFactory.initElements(driver, YourPolicies.class);
        yourPolicies.waitToBeDisplayed();
        return yourPolicies;
    }

    public void clickSettingsButton()
    {
        settingsButton.click();
    }

    public void clickLogoutButton()
    {
        logoutButton.click();
    }

    public String getNumberOfQuotes()
    {
        return yourQuotesButton.findElement(By.className("badge")).getText();
    }

    public String getNumberOfPolicies()
    {
        return yourPoliciesButton.findElement(By.className("badge")).getText();
    }

    public void waitForPageToLoad()
    {
        webDriverWaitWithPolling(5, 30, new Predicate<WebDriver>()
        {
            @Override public boolean apply(WebDriver webDriver)
            {
                return !findElement(By.id("one-account")).getText().contains("Loading...");
            }
        });
    }
}
