package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 18/03/2013
 */

/**
 * This page object cannot be initialized by a PageFactory because its locators are dynamic
 */
public class NewClaim extends BasePageObject
{

    private WebElement valueOfClaimInputBox;

    private WebElement typeOfClaimDropDown;

    private WebElement causeOfClaimBuildingsDropDown;

    private WebElement causeOfClaimContentsDropDown;

    private WebElement causeOfClaimContentsDescriptionInputBox;

    private WebElement claimContentsOccurAtPropertyYes;

    private WebElement claimContentsOccurAtPropertyNo;

    private WebElement causeOfClaimBuildingsDescriptionInputBox;

    private WebElement claimBuildingsOccurAtPropertyYes;

    private WebElement claimBuildingsOccurAtPropertyNo;

    private WebElement monthOfClaimDropDown;

    private WebElement yearOfClaimDropDown;

    private WebElement claimSettledRadioButtonYes;

    private WebElement claimSettledRadioButtonNo;

    private WebElement saveClaimButton;

    public NewClaim(WebDriver driver)
    {
        super(driver);
    }

    public NewClaim(WebDriver driver, WebElement container, int index)
    {
        super(driver);
        String locator = "claim" + index;
        valueOfClaimInputBox = container.findElement(By.id(locator + "_valueOfClaim"));
        typeOfClaimDropDown = container.findElement(By.id(locator + "_typeOfClaim"));
        causeOfClaimContentsDropDown = container.findElement(By.id(locator + "_causeOfClaim_Contents"));
        causeOfClaimContentsDescriptionInputBox = container.findElement(By.id(locator + "_causeDescription_Contents"));
        claimContentsOccurAtPropertyYes = container.findElement(By.id(locator + "_claimAtProperty_Contents_1"));
        claimContentsOccurAtPropertyNo = container.findElement(By.id(locator + "_claimAtProperty_Contents_2"));
        causeOfClaimBuildingsDropDown = container.findElement(By.id(locator + "_causeOfClaim_Buildings"));
        causeOfClaimBuildingsDescriptionInputBox = container.findElement(By.id(locator + "_causeDescription_Buildings"));
        claimBuildingsOccurAtPropertyYes = container.findElement(By.id(locator + "_claimAtProperty_Buildings_1"));
        claimBuildingsOccurAtPropertyNo = container.findElement(By.id(locator + "_claimAtProperty_Buildings_1"));
        monthOfClaimDropDown = container.findElement(By.id(locator + "_monthOfClaim"));
        yearOfClaimDropDown = container.findElement(By.id(locator + "_yearOfClaim"));
        claimSettledRadioButtonYes = container.findElement(By.id(locator + "_settled_1"));
        claimSettledRadioButtonNo = container.findElement(By.id(locator + "_settled_2"));
        saveClaimButton = container.findElement(By.className("secondary"));
    }

    /**
     * Verify presence of elements                                           *
     */

    public boolean isValueOfClaimInputBoxDisplayed()
    {
        try
        {
            return valueOfClaimInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isTypeOfClaimDropDownDisplayed()
    {
        try
        {
            return typeOfClaimDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isCauseOfClaimBuildingsDropDownDisplayed()
    {
        try
        {
            return causeOfClaimBuildingsDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isCauseOfClaimContentsDropDownDisplayed()
    {
        try
        {
            return causeOfClaimContentsDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isCauseOfClaimContentsDescriptionInputBoxDisplayed()
    {
        try
        {
            return causeOfClaimContentsDescriptionInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isClaimContentsOccurAtPropertyRadioButtonDisplayed()
    {
        try
        {
            return claimContentsOccurAtPropertyYes.isDisplayed() && claimContentsOccurAtPropertyNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isCauseOfClaimBuildingsDescriptionInputBoxDisplayed()
    {
        try
        {
            return causeOfClaimBuildingsDescriptionInputBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isClaimBuildingsOccurAtPropertyRadioButtonDisplayed()
    {
        try
        {
            return claimBuildingsOccurAtPropertyYes.isDisplayed() && claimBuildingsOccurAtPropertyNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isMonthOfClaimDisplayed()
    {
        try
        {
            return monthOfClaimDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isYearOfClaimDisplayed()
    {
        try
        {
            return yearOfClaimDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isClaimSettledRadioButtonDisplayed()
    {
        try
        {
            return claimSettledRadioButtonYes.isDisplayed() && claimSettledRadioButtonNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isSaveButtonDisplayed()
    {
        try
        {
            return saveClaimButton.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * User interactions                                                *
     */

    public void enterValueOfClaim(String valueOfClaim)
    {
        enterTextInput(valueOfClaimInputBox, valueOfClaim);
    }

    public void selectTypeOfClaim(String typeOfClaim) throws Exception
    {
        selectDropDownValueByVisibleText(typeOfClaimDropDown, typeOfClaim);
    }

    public void selectBuildingsCauseOfClaim(String causeOfClaim) throws Exception
    {
        selectDropDownValueByVisibleText(causeOfClaimBuildingsDropDown, causeOfClaim);
    }

    public void selectContentsCauseOfClaim(String causeOfClaim) throws Exception
    {
        selectDropDownValueByVisibleText(causeOfClaimContentsDropDown, causeOfClaim);
    }

    public void enterContentsCauseOfClaimDescription(String causeOfClaimDescription)
    {
        enterTextInput(causeOfClaimContentsDescriptionInputBox, causeOfClaimDescription);
    }

    public void enterBuildingsCauseOfClaimDescription(String causeOfClaimDescription)
    {
        enterTextInput(causeOfClaimBuildingsDescriptionInputBox, causeOfClaimDescription);
    }

    public void selectClaimContentsOccurAtProperty(boolean yes)
    {
        if (yes)
        {
            jsClick(claimContentsOccurAtPropertyYes);
        }
        else
        {
            jsClick(claimContentsOccurAtPropertyNo);
        }
    }

    public void selectClaimBuildingsOccurAtProperty(boolean yes)
    {
        if (yes)
        {
            jsClick(claimBuildingsOccurAtPropertyYes);
        }
        else
        {
            jsClick(claimBuildingsOccurAtPropertyNo);
        }
    }

    public void selectMonthOfClaim(String monthOfClaim) throws Exception
    {
        selectDropDownValueByVisibleText(monthOfClaimDropDown, monthOfClaim);
    }

    public void selectYearOfClaim(String yearOfClaim) throws Exception
    {
        selectDropDownValueByVisibleText(yearOfClaimDropDown, yearOfClaim);
    }

    public void selectClaimSettled(boolean yes)
    {
        if (yes)
        {
            jsClick(claimSettledRadioButtonYes);
        }
        else
        {
            jsClick(claimSettledRadioButtonNo);
        }
    }

    public void saveClaim()
    {
        jsClick(saveClaimButton);
    }
}
