package com.qmetric.pageobjects.new_oneaccount;

import com.google.common.collect.Lists;
import com.qmetric.domain.OneAccountEnquiry;
import com.qmetric.pageobjects.BasePageObject;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OneAccountMainPage extends BasePageObject
{
    @FindBy(css = "section.enquiries")
    WebElement enquiriesSection;

    @FindBy(css = "section.policies")
    WebElement policiesSection;

    public OneAccountMainPage(final WebDriver driver)
    {
        super(driver);
    }

    public List<OneAccountEnquiry> enquiries()
    {
        List<OneAccountEnquiry> oneAccountEnquiries = Lists.newArrayList();
        List<WebElement> enquiries = getEnquiries();
        for(WebElement enquiry : enquiries)
        {
            String businessLine = enquiry.findElement(By.cssSelector("div[role=type-label]")).getText();
            String risk = enquiry.findElement(By.cssSelector("div[role=risk]")).getText();
            String price = enquiry.findElement(By.cssSelector("div[role=price]")).getText();
            price = StringUtils.substringAfter(price, "£");
            WebElement viewButton = enquiry.findElement(By.cssSelector("a[role=action]"));
            oneAccountEnquiries.add(new OneAccountEnquiry(businessLine, risk, price, viewButton));
        }
        return oneAccountEnquiries;
    }

    public List<WebElement> getEnquiries()
    {
        return enquiriesSection.findElements(By.cssSelector("div[class=panel panel-default]"));
    }

    public List<WebElement> getPolicies()
    {
        return policiesSection.findElements(By.cssSelector("div[class=panel panel-default]"));
    }
}
