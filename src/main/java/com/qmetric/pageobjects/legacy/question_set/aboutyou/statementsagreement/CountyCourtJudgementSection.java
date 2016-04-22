package com.qmetric.pageobjects.legacy.question_set.aboutyou.statementsagreement;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 15/03/2013
 */
public class CountyCourtJudgementSection extends BasePageObject
{

    @FindBy(id = "number_of_ccjs") WebElement numberOfCCJsDropDown;

    @FindBy(id = "ccj_value") WebElement cCJValueDropDown;

    public CountyCourtJudgementSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isNumberOfCCJsDropDownDisplayed()
    {
        try
        {
            return numberOfCCJsDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isCCJValueDropDownDisplayed()
    {
        try
        {
            return cCJValueDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getNumberOfCCJsDropDownElementsText()
    {
        return getDropdownElementsText(numberOfCCJsDropDown);
    }

    public List<String> getCCJValueDropDownElementsText()
    {
        return getDropdownElementsText(cCJValueDropDown);
    }

    public void selectNumberOfCCJs(String numberOfCCJs) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfCCJsDropDown, numberOfCCJs);
    }

    public void selectTCCJValue(String cCJValue) throws Exception
    {
        selectDropDownValueByVisibleText(cCJValueDropDown, cCJValue);
    }
}
