package com.qmetric.pageobjects.backoffice;

import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class BackOfficeMainPage extends BasePageObject
{
    @FindBy(id = "category-selection")
    WebElement categorySelectionSection;

    @FindBy(css = "#business-lines a")
    List<WebElement> categoryLinks;

    @FindBy(css = "#breadcrumb li")
    List<WebElement> breadcrumbLinks;

    public BackOfficeMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void waitForPageToLoad()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, categorySelectionSection);
                for (WebElement categoryLink : categoryLinks) {
                    waitForElementVisible(10, categoryLink);
                }
                return null;
            }
        }.execute();
    }

    public BasketSection getBasketSection()
    {
        return PageFactory.initElements(driver, BasketSection.class);
    }

    public String getBasketMaxDiscountValue()
    {
        return getBasketSection().getMaxDiscountValue();
    }

    public List<String> getCategories()
    {
        List<String> categories = new ArrayList<String>();
        for (WebElement categoryLink : categoryLinks)
        {
            categories.add(categoryLink.getText());
        }
        return categories;
    }

    public void selectCategory(final String category)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                for (WebElement categoryLink : categoryLinks)
                {
                   if (categoryLink.getText().equals(category))
                   {
                       new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(categoryLink));
                       TimeHelper.waitInSeconds(1);
                       jsClick(categoryLink);
                       break;
                   }
                }
                return null;
            }
        }.execute();
    }

    public List<String> getBreadcrumbTexts()
    {
        List<String> breadcrumbTexts = Lists.newArrayList();
        for (WebElement breadcrumbLink : breadcrumbLinks)
        {
            breadcrumbTexts.add(breadcrumbLink.getText());
        }
        return breadcrumbTexts;
    }

    public void openSearchPage()
    {
        WebElement searchLink = findElement(By.className("search-menu-item"));
        WebElement searchAnchor = searchLink.findElement(By.cssSelector("a"));
        jsClick(searchAnchor);
    }

    public void openSalesPage()
    {
        WebElement salesLink = findElement(By.className("sales-menu-item"));
        WebElement salesAnchor = salesLink.findElement(By.cssSelector("a"));
        salesAnchor.click();
    }

    public void clickMenuItem(String menuName)
    {
        WebElement topMenu = findElement(By.id("top-menu"));
        WebElement menuItem = topMenu.findElement(By.linkText(menuName));
        menuItem.click();
    }

    public void openOrdersPage()
    {
        WebElement ordersLink = findElement(By.className("orders-menu-item"));
        WebElement orderAnchor = ordersLink.findElement(By.cssSelector("a"));
        orderAnchor.click();
    }

    public void openCustomerServicePage()
    {
        WebElement customerServiceLink = findElement(By.className("customer-service-menu-item"));
        WebElement customerServiceAnchor = customerServiceLink.findElement(By.cssSelector("a"));
        customerServiceAnchor.click();
    }
}
