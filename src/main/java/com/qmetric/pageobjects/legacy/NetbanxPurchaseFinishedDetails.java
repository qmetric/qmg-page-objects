package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 10/05/2013
 */
public class NetbanxPurchaseFinishedDetails extends BasePageObject
{

    @FindBy(css = "#policySummary dl:nth-child(5) dd:nth-child(4)")
    private WebElement policyNumber;

    @FindBy(css = "#policySummary > dl:nth-child(2) > dd:nth-child(4)")
    private WebElement customerID;

    @FindBy(css = "#policySummary dl:nth-child(2) dd:nth-child(11)")
    private WebElement grossPremium;

    @FindBy(css = "#policySummary dl:nth-child(1) dd:nth-child(7)")
    private WebElement policyNumberWebSite;

    @FindBy(css = "#policySummary dl:nth-child(1) dd:nth-child(5)")
    private WebElement customerIDWebsite;

    @FindBy(css = "#policySummary dl:nth-child(1) dd:nth-child(11)")
    private WebElement grossPremiumWebsite;


    public NetbanxPurchaseFinishedDetails(WebDriver driver)
    {
        super(driver);
    }

    public String getPolicyNumber()
    {
        return policyNumber.getText();
    }

    public String getCustomerId()
    {
        return customerID.getText();
    }

    public String getGrossPremium()
    {
        return grossPremium.getText().replace("£", "");
    }

    public String getPolicyNumberWebSite()
    {
        return policyNumberWebSite.getText();
    }

    public String getCustomerIdWebSite()
    {
        return customerIDWebsite.getText();
    }

    public String getGrossPremiumWebSite()
    {
        return grossPremiumWebsite.getText().replace("£", "");
    }
}
