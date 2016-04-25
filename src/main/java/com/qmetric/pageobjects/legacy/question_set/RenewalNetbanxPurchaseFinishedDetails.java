package com.qmetric.pageobjects.legacy.question_set;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by pgnanasekaran on 26/06/2015.
 */
public class RenewalNetbanxPurchaseFinishedDetails extends BasePageObject
{

    @FindBy(css = "#policySummary dl dd:nth-child(7)")
    private WebElement policyNumber;

    @FindBy(css = "#policySummary dl dd:nth-child(5)")
    private WebElement customerID;

    @FindBy(css = "#policySummary dl dd:nth-child(11)")
    private WebElement grossPremium;

    public RenewalNetbanxPurchaseFinishedDetails(WebDriver driver)
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
}
