package com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 01/05/2013
 */
public class BOStatementsAgreementBox extends StatementsAgreementModalBox
{

    @FindBy(css = "button > span.continue") WebElement confirmButton;

    public BOStatementsAgreementBox(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public void closeStatementsForm()
    {
        try
        {
            final WebElement statementsFormTable = driver.findElement(By.cssSelector("div#property_overlay_overlay.modal-window"));
            waitForElementVisible(10, statementsFormTable);
            WebElement cancelLink = statementsFormTable.findElement(By.cssSelector("a.modal-close-button"));
            jsClick(cancelLink);
            waitForElementNotVisible(10, statementsFormTable);
        }
        catch (Exception ignored)
        {
        }
    }

    @Override
    public void confirmStatements()
    {
        jsClick(confirmButton);
    }

    @Override
    public boolean isDisplayed()
    {
        try
        {
            WebElement statementsFormTable = driver.findElement(By.cssSelector("a.modal-close-button"));
            return statementsFormTable.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
