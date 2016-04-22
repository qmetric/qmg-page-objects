package com.qmetric.pageobjects.legacy;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 03/07/2013
 */

public class RenewalStopSection extends BasePageObject
{

    @FindBy(css = "#renewalStopContainer")
    private WebElement renewalStopContainer;

    @FindBy(css = "#renewalStopClaimsContainer > div")
    private List<WebElement> uploadedClaimsList;

    @FindBy(css = "button.renewalStopButton")
    private WebElement renewalStopButton;

    public RenewalStopSection(WebDriver driver)
    {
        super(driver);
    }

    boolean isRenewalStopSectionDisplayed()
    {
        return renewalStopContainer.isDisplayed();
    }

    public void waitForRenewalStopDisplayed()
    {
        waitForElementVisible(10, renewalStopContainer);
    }

    public int getNumberOfUploadedClaims()
    {
        if (!isRenewalStopSectionDisplayed())
        {
            return 0;
        }
        else
        {
            webDriverWaitWithPolling(10, 1, uploadedClaimsAreDisplayed());
            return uploadedClaimsList.size();
        }
    }

    Predicate<WebDriver> uploadedClaimsAreDisplayed()
    {
        return new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return uploadedClaimsList.size() >= 1;
            }
        };
    }

    public boolean isClearRenewalStopPresent()
    {
        try
        {
            return renewalStopButton.isDisplayed() && renewalStopButton.getText().equals("Clear");
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickClearRenewalStopButton()
    {
        pause(5000);
        jsClick(renewalStopButton);
        pause(5000);
        waitForRenewalStopDisplayed();
        waitForClearRenewalStopSuccess();
        driver.navigate().refresh();
        waitForRenewalStopDisplayed();
        waitForClearRenewalStopSuccess();
    }

    public void clickSetRenewalStopButton()
    {
        pause(5000);
        jsClick(renewalStopButton);
        pause(5000);
        waitForRenewalStopDisplayed();
        waitForSetRenewalStopSuccess();
        driver.navigate().refresh();
        waitForRenewalStopDisplayed();
        waitForSetRenewalStopSuccess();
    }

    private void waitForSetRenewalStopSuccess()
    {
        waitForElementPresent(5, "#renewalStopContent > h4.error");
    }

    private void waitForClearRenewalStopSuccess()
    {
        waitForElementPresent(5, "#renewalStopContent > h4.success");
    }
}
