package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 19/03/2013
 */
public class BuildingsCoverSection extends BasePageObject
{

    @FindBy(id = "property_market_value") WebElement propertyMarketValueInputBox;

    @FindBy(id = "property_rebuild_value") WebElement propertyRebuildValueInputBox;

    @FindBy(id = "buildings_accidental_damage_1") WebElement includeAccidentalDamageCoverYes;

    @FindBy(id = "buildings_accidental_damage_2") WebElement includeAccidentalDamageCoverNo;

    public BuildingsCoverSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isPropertyMarketValueInputBoxDisplayed()
    {
        try
        {
            return propertyMarketValueInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isPropertyRebuildValueInputBoxDisplayed()
    {
        try
        {
            return propertyRebuildValueInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isIncludeAccidentalDamageCoverRadioButtonDisplayed()
    {
        try
        {
            return includeAccidentalDamageCoverYes.isDisplayed() && includeAccidentalDamageCoverNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void enterPropertyMarketValue(String marketValue)
    {
        enterTextInput(propertyMarketValueInputBox, marketValue);
    }

    public void enterPropertRebuildValue(String rebuildValue)
    {
        clearTextInput(propertyRebuildValueInputBox);
        enterTextInput(propertyRebuildValueInputBox, rebuildValue);
    }

    public void selectIncludeAccidentalDamageCover(boolean yes)
    {
        if (yes)
        {
            jsClick(includeAccidentalDamageCoverYes);
        }
        else
        {
            jsClick(includeAccidentalDamageCoverNo);
        }
    }
}
