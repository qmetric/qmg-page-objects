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

public class RoofTypeSection extends BasePageObject
{
    @FindBy(id = "roof_type") WebElement roofTypeDropDown;

    @FindBy(id = "roof_type_majority_1") WebElement moreThan50OfRoofYes;

    @FindBy(id = "roof_type_majority_2") WebElement moreThan50OfRoofNo;

    @FindBy(id = "roof_type_thatch") WebElement roofTypeOfThatchDropDown;

    public RoofTypeSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isRoofTypeDropDownDisplayed()
    {
        try
        {
            return roofTypeDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isMoreThan50PercOfTheRoofRadioButtonDisplayed()
    {
        try
        {
            return moreThan50OfRoofYes.isDisplayed() && moreThan50OfRoofNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isRoofTypeOfThatchDropDownDisplayed()
    {
        try
        {
            return roofTypeOfThatchDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getRoofTypeDropDownElementsText()
    {
        return getDropdownElementsText(roofTypeDropDown);
    }

    public List<String> getRoofTypeOfThatchDropDownElementsText()
    {
        return getDropdownElementsText(roofTypeOfThatchDropDown);
    }

    public void selectRoofType(String roofType) throws Exception
    {
        selectDropDownValueByVisibleText(roofTypeDropDown, roofType);
    }

    public void selectRoofTypeOfThatch(String typeOfThatch) throws Exception
    {
        selectDropDownValueByVisibleText(roofTypeOfThatchDropDown, typeOfThatch);
    }

    public void selectRoofMoreThan50PercentRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(moreThan50OfRoofYes);
        }
        else
        {
            jsClick(moreThan50OfRoofNo);
        }
    }
}
