package com.qmetric.pageobjects.website.one_account;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 16/04/2014
 */
public class YourPolicies extends BasePageObject
{
    @FindBy(id = "policies-content")
    WebElement policiesContent;

    private DateTime startDate;
    private DateTime expiryDate;

    public YourPolicies(WebDriver driver)
    {
        super(driver);
    }

    public boolean isDisplayed()
    {
        try
        {
            return policiesContent.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void waitToBeDisplayed()
    {
        waitForElementVisible(5, policiesContent);
        waitForContentOpensCompletely(10);
    }

    public int getNumberOfHomePolicies()
    {
        return policiesContent.findElements(By.cssSelector("div.home-policies-content > div")).size();
    }

    public int getNumberOfHomeLegalExtrasPolicies()
    {
        return policiesContent.findElements(By.cssSelector("div.home-legal-extras-policies-content > div")).size();
    }

    public int getNumberOfHomeHECExtrasPolicies()
    {
        return policiesContent.findElements(By.cssSelector("div.home-hec-extras-policies-content > div")).size();
    }

    public int getTotalNumberOfPolicies()
    {
        return policiesContent.findElements(By.cssSelector("article.all-policies > div > div")).size();
    }

    public PolicyElement getHomePolicyElement(int index)
    {
        SearchContext searchContext = policiesContent.findElements(By.cssSelector("div.home-policies-content > div")).get(index);
        PolicyElement policyElement = PageFactory.initElements(driver, PolicyElement.class);
        PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), policyElement);
        policyElement.setIndex(index);
        return policyElement;
    }

    public List<PolicyElement> getPolicyElements()
    {
        List<PolicyElement> policyElements = Lists.newArrayList();
        List<WebElement> policyWebElements = policiesContent.findElements(By.cssSelector("article > div"));
        int policiesAmount = policyWebElements.size();
        for (int i = 0; i < policiesAmount; i++)
        {
            SearchContext searchContext = policyWebElements.get(i);
            PolicyElement policyElement = PageFactory.initElements(driver, PolicyElement.class);
            PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), policyElement);
            policyElement.setIndex(i);
            policyElements.add(policyElement);
        }
        return policyElements;
    }

    public PolicyElement getPolicyElement(String insuranceType)
    {
        PolicyElement policyElement = PageFactory.initElements(driver, PolicyElement.class);
        List<WebElement> policyWebElements = policiesContent.findElements(By.cssSelector("article > div"));
        int index = 0;
        for (WebElement policyWebElement : policyWebElements)
        {
            WebElement policyHeading = policyWebElement.findElement(By.tagName("h4"));
            if (policyHeading.getText().equals(insuranceType))
            {
                SearchContext searchContext = policyWebElement;
                PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), policyElement);
                policyElement.setIndex(index);
                return policyElement;
            }
            index++;
        }
        return policyElement;
    }

    public boolean checkPolicyType(String insuranceType)
    {
        List<WebElement> policyWebElements = policiesContent.findElements(By.cssSelector((".policy.l-span12-m > .content > .data > span")));
        for (WebElement policyWebElement : policyWebElements)
        {
            String policyHeading = policyWebElement.getText();
            if(policyHeading.startsWith(insuranceType))
            {
                return true;
            }
        }
        return false;
    }

    public DateTime getPolicyStartDate() throws ParseException
    {
        List<WebElement> policyWebElements = policiesContent.findElements(By.cssSelector((".policy.l-span12-m > .content > .data > span")));
        for (WebElement policyWebElement : policyWebElements)
        {
            String policyHeading = policyWebElement.getText();
            if(policyHeading.startsWith("Start date:"))
            {
                String[] dateContent = policyHeading.split(":");
                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMM yyyy");
                startDate = formatter.parseDateTime(dateContent[1].trim());
             }
        }
        return startDate;
    }

    public DateTime getPolicyExpiryDate() throws ParseException
    {
        int daysToExpiry = 0;
        List<WebElement> policyWebElements = policiesContent.findElements(By.cssSelector((".policy.l-span12-m > .content > .data > span")));
        for (WebElement policyWebElement : policyWebElements)
        {
            String policyHeading = policyWebElement.getText();
            if(policyHeading.endsWith("days until expiry"))
            {
                String[] dateContent = policyHeading.split(" ");
                daysToExpiry = Integer.parseInt(dateContent[0].trim());
            }
        }
        startDate = getPolicyStartDate();
        expiryDate = startDate.plusDays(daysToExpiry);
        return expiryDate;
    }

    public PolicyElement getHomeLegalExtrasPolicyElement(int index)
    {
        SearchContext searchContext = policiesContent.findElements(By.cssSelector("div.home-legal-extras-policies-content > div")).get(index);
        PolicyElement policyElement = PageFactory.initElements(driver, PolicyElement.class);
        PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), policyElement);
        policyElement.setIndex(index);
        return policyElement;
    }

    public PolicyElement getHomeHECExtrasPolicyElement(int index)
    {
        SearchContext searchContext = policiesContent.findElements(By.cssSelector("div.home-hec-extras-policies-content > div")).get(index);
        PolicyElement policyElement = PageFactory.initElements(driver, PolicyElement.class);
        PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), policyElement);
        policyElement.setIndex(index);
        return policyElement;
    }

    private void waitForContentOpensCompletely(int timeoutInSeconds)
    {
        new WebDriverWait(driver, timeoutInSeconds).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !policiesContent.getAttribute("style").contains("overflow");
            }
        });
    }
}
