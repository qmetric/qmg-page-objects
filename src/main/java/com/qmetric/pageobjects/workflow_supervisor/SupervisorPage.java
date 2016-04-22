package com.qmetric.pageobjects.workflow_supervisor;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupervisorPage extends BasePageObject
{
    @FindBy(css = "ul.nav.nav-tabs.ng-scope")
    WebElement mainTabNavigation;

    @FindBy(id = "closedTab")
    WebElement closedTab;

    public SupervisorPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickOnMainTab(final String tabName)
    {
        WebElement tabLink = mainTabNavigation.findElement(By.linkText(tabName));
        jsClick(tabLink);
    }

    public boolean enquiryRowExists(final String enquiryId)
    {
        webDriverWaitWithPolling(120, 5, new Predicate<WebDriver>()
        {
            @Override public boolean apply(final org.openqa.selenium.WebDriver driver)
            {
                return doesWebElementExist(By.cssSelector("tr[class='" + enquiryId + "']"));
            }
        });
        return true;
    }

    public Map<String, String> getEnquiryRow(final String enquiryId)
    {
        return new DynamicElementHandler<Map<String, String>>()
        {
            @Override
            public Map<String, String> handleDynamicElement()
            {
                Map<String, String> enquiryRowMap = new HashMap<>();
                List<WebElement> tableHeaders = findElements(By.tagName("th"));
                List<String> filteredTableHeaders = getFilteredValues(tableHeaders);
                WebElement enquiryRow = findElement(By.cssSelector("tr[class='" + enquiryId + "']"));
                List<WebElement> enquiryRowData = enquiryRow.findElements(By.tagName("td"));
                List<String> filteredRowData = getFilteredValues(enquiryRowData);
                for (int i = 0; i < filteredTableHeaders.size(); i++)
                {
                    enquiryRowMap.put(filteredTableHeaders.get(i), filteredRowData.get(i));
                }
                return enquiryRowMap;
            }

        }.execute();
    }

    private List<String> getFilteredValues(final List<WebElement> unfilteredValues)
    {
        List<String> filteredValues = Lists.newArrayList();
        for (WebElement unfilteredValue : unfilteredValues)
        {
            String className = unfilteredValue.getAttribute("class");
            if (StringUtils.isEmpty(className) || !className.equals("ng-hide"))
            {
                filteredValues.add(unfilteredValue.getText());
            }
        }
        return filteredValues;
    }

    public void clickOnClosedTaskTab()
    {
        jsClick(closedTab.findElement(By.tagName("a")));
    }
}
