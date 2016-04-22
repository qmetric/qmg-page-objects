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
public class NewPersonalItem extends BasePageObject
{

    private static final String ID = "specified_items";

    private final WebElement specifiedItemsDropDown;

    private final WebElement specifiedItemsDescriptionInputBox;

    private final WebElement specifiedItemsValueInputBox;

    private final WebElement specifiedItemsCoverTypeDropDown;

    private final WebElement saveButton;

    public NewPersonalItem(WebDriver driver, WebElement container, int index)
    {
        super(driver);
        String locator = ID + index;
        specifiedItemsDropDown = container.findElement(By.id(locator));
        specifiedItemsDescriptionInputBox = container.findElement(By.id(locator + "_description"));
        specifiedItemsValueInputBox = container.findElement(By.id(locator + "_value"));
        specifiedItemsCoverTypeDropDown = container.findElement(By.id(locator + "_cover_type"));
        saveButton = container.findElement(By.className("secondary"));
    }

    public boolean isSpecifiedItemsDropDownDisplayed()
    {
        try
        {
            return specifiedItemsDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isSpecifiedItemDescriptionInputBoxDisplayed()
    {
        try
        {
            return specifiedItemsDescriptionInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isSpecifiedItemsValueInputBox()
    {
        try
        {
            return specifiedItemsValueInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isSpecifiedItemsCoverTypeDropDown()
    {
        try
        {
            return specifiedItemsCoverTypeDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void selectSpecifiedItem(String specifiedItem) throws Exception
    {
        selectDropDownValueByVisibleText(specifiedItemsDropDown, specifiedItem);
    }

    public void enterSpecifiedItemDescription(String description)
    {
        specifiedItemsDescriptionInputBox.sendKeys(description);
    }

    public void enterSpecifiedItemValue(String valueOfItem)
    {
        specifiedItemsValueInputBox.sendKeys(valueOfItem);
    }

    public void selectSpecifiedItemsCoverType(String itemsCoverType) throws Exception
    {
        selectDropDownValueByVisibleText(specifiedItemsCoverTypeDropDown, itemsCoverType);
    }

    public void savePersonalItem()
    {
        jsClick(saveButton);
        pause(2000); // adding pause to increase stability for now
    }
}
