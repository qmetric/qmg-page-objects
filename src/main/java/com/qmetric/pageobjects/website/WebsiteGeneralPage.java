package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.browser.utility.Browser;
import com.qmetric.shared.SharedData;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class WebsiteGeneralPage extends BasePageObject
{
    public WebsiteGeneralPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isNoQuotesPageDisplayed()
    {
        WebElement noQuotesPage = findElement(By.className("noquotespage"));
        WebElement header = noQuotesPage.findElement(By.cssSelector("header h3"));
        return driver.getCurrentUrl().contains("no-quotes") && header.getText().equals("Sorry, we are unable to quote");
    }

    public boolean isEnquiryPurchasedPageDisplayed()
    {
        WebElement errorPage = findElement(By.className("errorpage"));
        WebElement pageContainer = errorPage.findElement(By.cssSelector("article"));
        return pageContainer.getText().contains("Sorry, this enquiry has been purchased.");
    }

    public String getErrorMessage()
    {
        return findElement(By.className("message-view")).getText().trim();
    }

    public void deleteCookies()
    {
        if (SharedData.BROWSER.equals(Browser.INTERNET_EXPLORER))
        {
            try
            {
                Process p = Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
                p.waitFor();
                driver.manage().deleteAllCookies();
                TimeHelper.waitInSeconds(10);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            driver.manage().deleteAllCookies();
        }
    }
}
