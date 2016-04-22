package com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 08/03/2013
 */

public class SubsidenceSection extends BasePageObject
{
    @FindBy(id = "subsidence_visible") WebElement subsidenceVisibleDateDropDown;

    @FindBy(id = "property_underpinned_1") WebElement propertyUnderpinnedYes;

    @FindBy(id = "property_underpinned_2") WebElement propertyUnderpinnedNo;

    @FindBy(id = "subsidence_value") WebElement subsidenceValueInputBox;

    @FindBy(id = "subsidence_complete_1") WebElement subsidenceCompleteYes;

    @FindBy(id = "subsidence_complete_2") WebElement subsidenceCompleteNo;

    public SubsidenceSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isSubsidenceVisibleDateDropDownDisplayed()
    {
        try
        {
            return subsidenceVisibleDateDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isPropertyUnderpinnedRadioButtonDisplayed()
    {
        try
        {
            return propertyUnderpinnedYes.isDisplayed() && propertyUnderpinnedNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isSubsidenceValueInputBoxDisplayed()
    {
        try
        {
            return subsidenceValueInputBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isSubsidenceCompleteRadioButtonDisplayed()
    {
        try
        {
            return subsidenceCompleteYes.isDisplayed() && subsidenceCompleteNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getSubsidenceVisibleDateDropDownElementsText()
    {
        return getDropdownElementsText(subsidenceVisibleDateDropDown);
    }

    public void selectSubsidenceValue(String subsidenceValue) throws Exception
    {
        subsidenceValueInputBox.sendKeys(subsidenceValue);
    }

    public void selectSubsidenceVisibleDate(String subsidenceVisibleDate) throws Exception
    {
        selectDropDownValueByVisibleText(subsidenceVisibleDateDropDown, subsidenceVisibleDate);
    }

    public void selectPropertyUnderpinnedRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(propertyUnderpinnedYes);
        }
        else
        {
            jsClick(propertyUnderpinnedNo);
        }
    }

    public void selectSubsidenceCompleteRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(subsidenceCompleteYes);
        }
        else
        {
            jsClick(subsidenceCompleteNo);
        }
    }
}
