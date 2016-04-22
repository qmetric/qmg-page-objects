package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackOfficePolicyHolderInfoTab extends BasePageObject
{
    public BackOfficePolicyHolderInfoTab(WebDriver driver)
    {
        super(driver);
    }

    private WebElement getPolicyHolderInfoLeftTable()
    {
        WebElement policyInformationSection = findElement(By.id("policy-informations"));
        return policyInformationSection.findElement(By.cssSelector("table[class=\"l-span6 info-panel\"]"));
    }

    private WebElement getPolicyHolderInfoRightTable()
    {
        WebElement policyInformationSection = findElement(By.id("policy-informations"));
        return policyInformationSection.findElement(By.cssSelector("table[class=\"l-span6\"]"));
    }

    public String getJointPolicyHolders()
    {
        return getPolicyHolderInfoLeftTable().findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).getText();
    }

    public String getPolicyHolderName()
    {
        return getPolicyHolderInfoLeftTable().findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
    }

    public String getAddress()
    {
        return getPolicyHolderInfoRightTable().findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText();
    }
}
