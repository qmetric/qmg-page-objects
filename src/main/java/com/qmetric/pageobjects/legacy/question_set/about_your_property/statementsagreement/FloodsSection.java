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

public class FloodsSection extends BasePageObject
{

    @FindBy(id = "flooding_source") WebElement floodingSourceDropDown;

    @FindBy(id = "flooding_distance_watercourse") WebElement floodingDistanceWatercourseDropDown;

    @FindBy(id = "flooding_height_watercourse_1") WebElement floodingHeightWatercourseHeightYes;

    @FindBy(id = "flooding_height_watercourse_2") WebElement floodingHeightWatercourseHeightNo;

    public FloodsSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isFloodingSourceDropDownDisplayed()
    {
        try
        {
            return floodingSourceDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isFloodingDistanceWatercourseDropDownDisplayed()
    {
        try
        {
            return floodingDistanceWatercourseDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isFloodingHeightWatercourseRadioButtonDisplayed()
    {
        try
        {
            return floodingHeightWatercourseHeightYes.isDisplayed() && floodingHeightWatercourseHeightNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getFloodingSourceDropDownElementsText()
    {
        return getDropdownElementsText(floodingSourceDropDown);
    }

    public List<String> getFloodingDistanceDropDownElementsText()
    {
        return getDropdownElementsText(floodingDistanceWatercourseDropDown);
    }

    public void selectFloodingSource(String floodingSource) throws Exception
    {
        selectDropDownValueByVisibleText(floodingSourceDropDown, floodingSource);
    }

    public void selectFloodingDistanceFromWatercourse(String distance) throws Exception
    {
        selectDropDownValueByVisibleText(floodingDistanceWatercourseDropDown, distance);
    }

    public void selectGroundOfPropertyAboveTheFloodingSourceRadioButton(String yes)
    {
        if (yes.equals("Yes"))
        {
            jsClick(floodingHeightWatercourseHeightYes);
        }
        else
        {
            jsClick(floodingHeightWatercourseHeightNo);
        }
    }
}
