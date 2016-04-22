package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 10/11/2014
 */
public class BackOfficeOrdersPage extends BasePageObject
{
    public BackOfficeOrdersPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickViewPolicyButton(int orderIndex)
    {
        if (orderIndex > 1)
        {
            jsClick(getViewPolicyButtonMoreThanOneRow(orderIndex));
        }
        else
        {
            jsClick(getViewPolicyButton(orderIndex));
        }
    }

    public List<Map<String, String>> getOrderDetails(int orderIndex)
    {
        WebElement orderDetailsContainer = getOrderDetailsContainer(orderIndex);
        HtmlTable ordersTable = new HtmlTable(orderDetailsContainer.findElement(By.cssSelector("table")));
        return ordersTable.getTableBodyColumnTextValues();
    }

    public String getStatus(int orderIndex)
    {
        WebElement orderDetailsContainer = getOrderDetailsContainer(orderIndex);
        WebElement orderStatusElement = orderDetailsContainer.findElement(By.className("orderStatus"));
        return orderStatusElement.getText();
    }

    public String getTotal(int orderIndex)
    {
        WebElement orderDetailsContainer = getOrderDetailsContainer(orderIndex);
        WebElement orderStatusElement = orderDetailsContainer.findElement(By.className("orderTotal"));
        return orderStatusElement.getText();
    }

    public String getPolicyId(int orderIndex)
    {
        return getViewPolicyButton(orderIndex).getAttribute("data-id");
    }

    private WebElement getViewPolicyButtonMoreThanOneRow(int orderIndex)
    {
        HtmlTable ordersTable = new HtmlTable(getOrderDetailsContainer(orderIndex).findElement(By.cssSelector("table")));
        return ordersTable.getAllTableRows().get(orderIndex - 1).findElement(By.cssSelector("button"));
    }

    private WebElement getViewPolicyButton(int orderIndex)
    {
        HtmlTable ordersTable = new HtmlTable(getOrderDetailsContainer(orderIndex).findElement(By.cssSelector("table")));
        List<List<WebElement>> tableColumnRows = ordersTable.getAllTableBodyColumns();
        List<WebElement> tableColumns = tableColumnRows.get(orderIndex - 1);
        for (WebElement column : tableColumns)
        {
            By buttonSelector = By.cssSelector("button");
            if (doesWebElementExist(column, buttonSelector))
            {
                return column.findElement(buttonSelector);
            }
        }
        throw new RuntimeException("View policy button not found on row: " + (orderIndex - 1));
    }

    private WebElement getOrderDetailsContainer(int orderIndex)
    {
        final WebElement ordersContainer = findElement(By.id("orders-list"));
        waitForElementVisible(120, ordersContainer);
        webDriverWaitWithPolling(120, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !ordersContainer.getText().contains("Loading orders");
            }
        });
        List<WebElement> orderDetails = ordersContainer.findElements(By.className("orderDetails"));
        return orderDetails.get(orderIndex - 1);
    }
}
