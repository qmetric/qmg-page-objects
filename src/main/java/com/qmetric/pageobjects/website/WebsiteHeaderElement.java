package com.qmetric.pageobjects.website;

import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteHeaderElement extends BasePageObject
{

    public WebsiteHeaderElement(WebDriver driver)
    {
        super(driver);
    }

    public String getPhoneNumber()
    {
        WebElement contactPhoneWrapper = findElement(By.id("contact-phone-wrapper"));
        return contactPhoneWrapper.getText();
    }

    public String getActiveText()
    {
        WebElement progressBarContainer = findElement(By.className("nav-progressbar-steps"));
        WebElement activeElement = progressBarContainer.findElement(By.className("active"));
        return activeElement.getText();
    }

    public boolean isHeaderLoaded(String header)
    {
        boolean isPageLoaded = false;
        int retries = 0;
        do
        {
            try
            {
                WebElement progressBarContainer = findElement(By.className("nav-progressbar-steps"));
                WebElement activeElement = progressBarContainer.findElement(By.className("active"));
                if(activeElement.getText().equalsIgnoreCase(header))
                    isPageLoaded = true;
            }
            catch (Exception e)
            {
                LOG.warn("Could not select radio button. Retrying...");
                retries++;
            }
        }
        while (!isPageLoaded && retries < 10);
        return isPageLoaded;
    }

    public List<String> getNavigationValues()
    {
        List<String> values = Lists.newArrayList();
        WebElement progressBarContainer = findElement(By.className("nav-progressbar-steps"));
        List<WebElement> progressBarStepsElements = progressBarContainer.findElements(By.className("nav-progressbar-step"));
        for (WebElement progressBarStepsElement : progressBarStepsElements)
        {
            values.add(progressBarStepsElement.getText());
        }
        return values;
    }
}
