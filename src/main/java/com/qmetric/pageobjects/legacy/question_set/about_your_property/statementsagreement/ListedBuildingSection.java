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

public class ListedBuildingSection extends BasePageObject
{

    @FindBy(id = "listed_building") WebElement listedBuildingDropDown;

    public ListedBuildingSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isListedBuildingDropDownDisplayed()
    {
        try
        {
            return listedBuildingDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getListedBuildingDropDownElementsText()
    {
        return getDropdownElementsText(listedBuildingDropDown);
    }

    public void selectListedBuilding(String listedBuilding) throws Exception
    {
        selectDropDownValueByVisibleText(listedBuildingDropDown, listedBuilding);
    }
}
