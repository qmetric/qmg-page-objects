package com.qmetric.pageobjects.website.one_account;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/06/2014
 */
public class PolicyElement extends BasePageObject
{
    @FindBy(css = "h4")
    WebElement policyHeading;

    @FindBy(css = "span.data span:nth-child(1)")
    WebElement risk;

    @FindBy(css = "span.data span:nth-child(2)")
    WebElement startDate;

    @FindBy(css = "span.data span:nth-child(3)")
    WebElement status;

    @FindBy(css = "span.data button.view-policy")
    WebElement viewPolicyButton;

    @FindBy(css = "span.data form button")
    WebElement renewPolicyButton;

    @FindBy(css = "span.renewable-false .attention")
    WebElement renewalStopMessage;

    private int index;

    public PolicyElement(WebDriver driver)
    {
        super(driver);
    }

    public String getRisk()
    {
        return risk.getText();
    }

    public String getPolicyHeading()
    {
        return policyHeading.getText();
    }

    public String getStartDate()
    {
        return startDate.getText();
    }

    public PolicyDetailsPage clickViewPolicyButton()
    {
        viewPolicyButton.click();
        return PageFactory.initElements(driver, PolicyDetailsPage.class);
    }

    public boolean isRenewPolicyButtonDisplayed()
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                driver.navigate().refresh();
                OneAccountMenu oneAccountMenu = PageFactory.initElements(driver, OneAccountMenu.class);
                YourPolicies yourPolicies = oneAccountMenu.clickYourPoliciesButton();
                yourPolicies.waitToBeDisplayed();
                try
                {
                    return driver.findElement(By.cssSelector("span.renewable-true")).findElement(By.cssSelector("span[class=\"btn-text\"]")).getText().equals("View renewal price");
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        }.execute();
    }

    public void waitForRenewalButtonDisplayed()
    {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(renewPolicyButton));
    }

    public void clickRenewPolicyButton()
    {
        waitForRenewalButtonDisplayed();
        renewPolicyButton.click();
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public void waitForRenewalStop()
    {
        new WebDriverWait(driver, 200).pollingEvery(8, TimeUnit.SECONDS).until(renewalStopMessagePresentWithPageRefresh());
    }

    private Predicate<WebDriver> renewalStopMessagePresentWithPageRefresh()
    {
        return new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                driver.navigate().refresh();
                OneAccountMenu oneAccountMenu = PageFactory.initElements(driver, OneAccountMenu.class);
                YourPolicies yourPolicies = oneAccountMenu.clickYourPoliciesButton();
                yourPolicies.waitToBeDisplayed();
                pause(3000);
                try
                {
                    return driver.findElements(By.cssSelector(".home-policies-content > div")).get(index).findElement(By.cssSelector("span.renewable-false")).isDisplayed();
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        };
    }

    public String  getStatus()
    {
        return status.getText();
    }

    public String getRenewalStopStatus()
    {
        waitForRenewalStop();
        return driver.findElements(By.cssSelector(".home-policies-content > div")).get(index).findElement(By.cssSelector("span.renewable-false")).getText();
    }
}
