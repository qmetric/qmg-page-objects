package com.qmetric.pageobjects.workflow;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 04/08/2014
 */
public class EnquiriesTable extends BasePageObject
{
    @FindBy(css = "section#workflow-quotes table")
    WebElement enquiryTableElement;

    public EnquiriesTable(WebDriver driver)
    {
        super(driver);
    }

    public int getNumberOfEnquiryRows()
    {
        return enquiryTableElement.findElements(By.cssSelector("tr")).size() - 1;
    }

    public String getReceived(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(1)")).get(index).getText();
    }

    public String getQuoteDate(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(2)")).get(index).getText();
    }

    public String getCID(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(3)")).get(index).getText();
    }

    public String getPrice(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(4)")).get(index).getText();
    }

    public String getBestPrice(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(5)")).get(index).getText();
    }

    public String getType(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(6)")).get(index).getText();
    }

    public String getOrigin(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(7)")).get(index).getText();
    }

    public String getSource(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(8)")).get(index).getText();
    }

    public String getMarketCampaign(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(9)")).get(index).getText();
    }

    public String getTelephone(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(10)")).get(index).getText();
    }

    public String getCustomerName(int index)
    {
        return enquiryTableElement.findElements(By.cssSelector("tr td:nth-child(11)")).get(index).getText();
    }
}
