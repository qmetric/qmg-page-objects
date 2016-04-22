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

public class WallTypeSection extends BasePageObject
{

    @FindBy(id = "walls_type") WebElement wallsTypeDropDown;

    @FindBy(id = "wall_type_desc") WebElement wallsTypeDescription;

    public WallTypeSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isWallsTypeDropBoxDisplayed()
    {
        try
        {
            return wallsTypeDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isWallsTypeDescriptionDisplayed()
    {
        try
        {
            return wallsTypeDescription.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getWallsTypeDropDownElementsText()
    {
        return getDropdownElementsText(wallsTypeDropDown);
    }

    public void selectWallsType(String wallsType) throws Exception
    {
        selectDropDownValueByVisibleText(wallsTypeDropDown, wallsType);
    }

    public void inputWallsTypeDescription(String description)
    {
        enterTextInput(wallsTypeDescription, description);
    }
}
