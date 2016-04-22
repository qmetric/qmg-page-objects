package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 09/05/2013
 */

/**
 * This page object should be initialized from a WebElement representing the 'tr' that contains the policy summary element
 */
public class PolicySummary extends BasePageObject
{

    @FindBy(css = "td:nth-child(1)") WebElement viewPolicyButton;

    @FindBy(css = "td:nth-child(2)") WebElement productType;

    @FindBy(css = "td:nth-child(3)") WebElement policyNumber;

    @FindBy(css = "td:nth-child(4)") WebElement premium;

    @FindBy(css = "td:nth-child(5)") WebElement description;

    @FindBy(css = "td:nth-child(6)") WebElement startDate;

    @FindBy(css = "td:nth-child(7)") WebElement expiryDate;

    @FindBy(css = "td:nth-child(8)") WebElement cancellationDate;

    @FindBy(css = "td:nth-child(9)") WebElement status;

    public PolicySummary(WebDriver driver)
    {
        super(driver);
        pause(3000);
    }

    public void clickViewPolicy()
    {
        jsClick(viewPolicyButton);
        waitForElementPresent(8, "a#policyTab.selected");
    }

    public String getProductType()
    {
        return productType.getText();
    }

    public String getPolicyNumber()
    {
        return policyNumber.getText();
    }

    public String getPremium()
    {
        return premium.getText();
    }

    public String getDescription()
    {
        return description.getText();
    }

    public String getStartDate()
    {
        return startDate.getText();
    }

    public String getExpiryDate()
    {
        return expiryDate.getText();
    }

    public String getCancellationDate()
    {
        return cancellationDate.getText();
    }

    public String getStatus()
    {
        return status.getText();
    }
}
