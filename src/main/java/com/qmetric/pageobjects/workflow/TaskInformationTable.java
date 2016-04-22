package com.qmetric.pageobjects.workflow;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 04/08/2014
 */
public class TaskInformationTable extends BasePageObject
{
    @FindBy(css = "section#workflow-task table")
    WebElement taskInformationTable;

    public TaskInformationTable(WebDriver driver)
    {
        super(driver);
    }

    public String getCustomerEmail()
    {
        return taskInformationTable.findElements(By.cssSelector("tr td:nth-child(2)")).get(0).getText();
    }

    public String getActionsAmount()
    {
        return taskInformationTable.findElements(By.cssSelector("tr td:nth-child(2)")).get(1).getText();
    }
}
