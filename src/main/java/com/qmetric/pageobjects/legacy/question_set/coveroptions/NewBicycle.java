package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 20/03/2013
 */

/**
 * This page object does not follow the PageFactory pattern because there is no way to use FindBy annotations with dynamic locators
 */
public class NewBicycle extends BasePageObject
{

    private static final String ID = "pedal_cycle";

    private final WebElement bicycleDescriptionInputBox;

    private final WebElement bicycleValueInputBox;

    private final WebElement saveButton;

    public NewBicycle(WebDriver driver, WebElement container, int index)
    {
        super(driver);
        bicycleDescriptionInputBox = container.findElement(By.id(ID + index + "_description"));
        bicycleValueInputBox = container.findElement(By.id(ID + index + "_value"));
        saveButton = container.findElement(By.className("secondary"));
    }

    public boolean isBicycleDescriptionInputBoxDisplayed()
    {
        try
        {
            return bicycleDescriptionInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isBicycleValueInputBoxDisplayed()
    {
        try
        {
            return bicycleValueInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void enterBicycleDescription(String description)
    {
        bicycleDescriptionInputBox.sendKeys(description);
    }

    public void enterBicycleValue(String value)
    {
        bicycleValueInputBox.sendKeys(value);
    }

    public void saveBicycle()
    {
        jsClick(saveButton);
        pause(2000);
    }
}
