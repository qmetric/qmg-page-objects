package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DateUtility;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by pgnanasekaran on 10/06/2015.
 */
public class BackOfficePolicyInfoTab extends BasePageObject
{
    private String logoName;
    private String productDescription;

    public BackOfficePolicyInfoTab(WebDriver driver)
    {
        super(driver);
    }

    private WebElement getPolicyInfoLeftTable()
    {
            WebElement policyInformationSection = findElement(By.id("policy-informations"));
        return policyInformationSection.findElement(By.cssSelector("table[class=\"l-span4 info-panel\"]"));
    }

    private WebElement getPolicyInfoRightTable()
    {
        WebElement policyInformationSection = findElement(By.id("policy-informations"));
        return policyInformationSection.findElement(By.cssSelector("div[class=\"l-span6 info-panel pad-W\"] > table"));
    }

    private WebElement getPolicyInfoCenterTable()
    {
        WebElement policyInformationSection = findElement(By.id("policy-informations"));
        return policyInformationSection.findElement(By.cssSelector("table[class=\"l-span2 info-panel\"]"));
    }

    public String getAutoRenewStatus()
    {
        return getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).getText();
    }

    public String getProductName()
    {
        return getPolicyInfoLeftTable().findElement(By.cssSelector("tr[data-id='product-name'] td:nth-child(2)")).getText();
    }

    public String getPolicyNumber()
    {
        return getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(2) td:nth-child(2)")).getText();
    }

    public String getStatus()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                return getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2)")).getText();
            }
        }.execute();
    }

    public String getBusinessLine()
    {
        return getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(4) td:nth-child(2)")).getText();
    }

    public String getRiskInsured()
    {
        return getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(5) td:nth-child(2)")).getText();
    }

    public String getCancellationDate()
    {
        String dateTimeText = getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) > span")).getText();
        return dateTimeText.substring(3, dateTimeText.indexOf("at ")).trim();
    }

    public String getCancellationTime()
    {
        String dateTimeText = getPolicyInfoLeftTable().findElement(By.cssSelector("tr:nth-child(3) td:nth-child(2) > span")).getText();
        return dateTimeText.substring(dateTimeText.indexOf("at ") + 3, dateTimeText.length()).trim();
    }

    public Optional<String> getCancellationDateTime()
    {
        String dateTime = getCancellationDate() + " " + getCancellationTime();
        return DateUtility.formatDate(dateTime, "dd/MMM/yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss");
    }

    public String getPurchaseDate()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText();
        return dateTimeText.substring(0, dateTimeText.indexOf("at ")).trim();
    }

    public String getPurchaseTime()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText();
        return dateTimeText.substring(dateTimeText.indexOf("at ") + 3, dateTimeText.indexOf("(")).trim();
    }

    public Optional<String> getPurchaseDateTime()
    {
        String dateTime = getPurchaseDate() + " " + getPurchaseTime();
        return DateUtility.formatDate(dateTime, "dd/MMM/yyyy HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss");
    }

    public String getPeriodCover()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        dateTimeText = dateTimeText.replace(" ", "");
        Optional<String> startDate = Optional.of(dateTimeText.substring(0, dateTimeText.indexOf("to")));
        Optional<String> endDate = Optional.of(dateTimeText.substring(startDate.get().length() + 2, dateTimeText.indexOf("(")));
        startDate = DateUtility.formatDate(startDate.get(), "dd/MMM/yyyy", "dd/MM/yyyy");
        endDate = DateUtility.formatDate(endDate.get(), "dd/MMM/yyyy", "dd/MM/yyyy");
        return startDate.get() + " " + "at 00:00:00 to " + endDate.get() + " at 23:59:59";
    }

    public String getRawPeriodCover()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        return dateTimeText.substring(0, dateTimeText.indexOf("(")).trim().replace("to", "-");
    }

    public Optional<String> getRenewalStopMessagePresent()
    {
        webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return doesWebElementExist(By.cssSelector(".warning>span:nth-child(2)"));
            }
        });
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".warning>span:nth-child(2)")));

        try
        {
            return Optional.of(driver.findElement(By.cssSelector(".warning>span:nth-child(2)")).getText());
        }
        catch (NoSuchElementException e)
        {
            return Optional.absent();
        }
    }

    public Optional<String> getRenewalStopMessageAbsent()
    {
        try
        {
            return Optional.of(driver.findElement(By.cssSelector(".warning>span:nth-child(2)")).getText());
        }
        catch (NoSuchElementException e)
        {
            return Optional.absent();
        }
    }

    public String getRenewalStatus()
    {
        return getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).getText();
    }

    public Optional<String> getPurchaseDateTimeWithDateFormat(String dateFormat)
    {
        String dateTime = getPurchaseDate() + " " + getPurchaseTime();
        return DateUtility.formatDate(dateTime, "dd/MMM/yyyy HH:mm:ss", dateFormat);
    }

    public void clickOnRenewalPolicyLink()
    {
        driver.findElement(By.cssSelector(".renewingpolicy")).click();
    }

    public boolean checkRenewalPolicyLink(String renewalPolicyId)
    {
        if (driver.getCurrentUrl().contains(renewalPolicyId))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Optional<String> getCoverStartDate()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        dateTimeText = dateTimeText.replace(" ", "");
        Optional<String> startDate = Optional.of(dateTimeText.substring(0, dateTimeText.indexOf("to")));
        return DateUtility.formatDate(startDate.get(), "dd/MMM/yyyy", "dd/MM/yyyy");
    }

    public Optional<String> getCoverEndDate()
    {
        String dateTimeText = getPolicyInfoRightTable().findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText();
        dateTimeText = dateTimeText.replace(" ", "");
        Optional<String> startDate = Optional.of(dateTimeText.substring(0, dateTimeText.indexOf("to")));
        Optional<String> endDate = Optional.of(dateTimeText.substring(startDate.get().length() + 2, dateTimeText.indexOf("(")));
        return DateUtility.formatDate(endDate.get(), "dd/MMM/yyyy", "dd/MM/yyyy");
    }

    public boolean isRemoveStopFlagAvailable()
    {
        return doesWebElementExist(By.cssSelector(".policy-border-panel>span"));
    }

    public void clickRemoveStopFlagLink()
    {
        driver.findElement(By.cssSelector(".policy-border-panel>span")).click();
    }

    public boolean isRenewalStatusDisplayed()
    {
        return getPolicyInfoRightTable().findElements(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).size() > 0;
    }

    public String getLogo()
    {
        return getPolicyInfoCenterTable().findElement(By.cssSelector("img")).getAttribute("src");
    }
}

