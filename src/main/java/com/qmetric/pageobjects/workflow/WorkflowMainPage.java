package com.qmetric.pageobjects.workflow;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 31/07/2014
 */
public class WorkflowMainPage extends BasePageObject
{
    @FindBy(css = "button#workflow-get-task")
    WebElement getTaskButton;

    public WorkflowMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickGetTaskButton()
    {
        LOG.info("Click get task");
        getTaskButton.click();
        waitForElementNotVisible(5, "#workflow-get-task");
    }
}
