package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 31/07/2014
 */
public class TopNavigationMenuSection extends BasePageObject
{
    @FindBy(css = "a[data-menu-id=workflow]")
    WebElement workflowLink;

    @FindBy(css = "a[data-menu-id=Start]")
    WebElement newCustomerLink;

    //todo - add other links (New Customer, Sales, Orders, Search)

    public TopNavigationMenuSection(WebDriver driver)
    {
        super(driver);
    }

    public void clickWorkflowLink()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                LOG.info("Click on workflow link");
                new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(workflowLink));
                TimeHelper.waitInSeconds(1);
                jsClick(workflowLink);
                return null;
            }
        }.execute();
    }

    public void clickNewCustomerLink()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                LOG.info("Click on new customer link");
                new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(newCustomerLink));
                TimeHelper.waitInSeconds(1);
                jsClick(newCustomerLink);
                return null;
            }
        }.execute();
    }
}
