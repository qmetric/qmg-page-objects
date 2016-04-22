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
public class BusinessPurposesSection extends BasePageObject
{

    @FindBy(id = "type_of_business") WebElement typeOfBusinessDropDown;

    @FindBy(id = "number_of_employees") WebElement numberOfEmployeesDropDown;

    @FindBy(id = "number_of_daily_visitors") WebElement numberOfDailyVisitorsDropDown;

    @FindBy(id = "number_of_paying_guests") WebElement numberOfPayingGuestsDropDown;

    @FindBy(id = "number_of_children") WebElement numberOfChildrenDropDown;

    public BusinessPurposesSection(WebDriver driver)
    {
        super(driver);
    }

    /**
     * ********                                  Verify presence of elements                              ***********
     */

    public boolean isTypeOfBusinessDropDownDisplayed()
    {
        try
        {
            return typeOfBusinessDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfEmployeesDropDownDisplayed()
    {
        try
        {
            return numberOfEmployeesDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfDailyVisitorsDropDownDisplayed()
    {
        try
        {
            return numberOfDailyVisitorsDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfPayingGuestsDropDownDisplayed()
    {
        try
        {
            return numberOfPayingGuestsDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfChildrenDropDownDisplayed()
    {
        try
        {
            return numberOfChildrenDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * ********                                      Get dropdown values                                  ***********
     */

    public List<String> getTypeOfBusinessDropDownElementsText()
    {
        return getDropdownElementsText(typeOfBusinessDropDown);
    }

    /**
     * ********                                    Select dropdown values                                 ***********
     */

    public void selectTypeOfBusiness(String typeOfBusiness) throws Exception
    {
        selectDropDownValueByVisibleText(typeOfBusinessDropDown, typeOfBusiness);
    }

    public void selectNumberOfEmployees(String numberOfEmployees) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfEmployeesDropDown, numberOfEmployees);
    }

    public void selectNumberOfDailyVisitors(String numberOfDailyVisitors) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfDailyVisitorsDropDown, numberOfDailyVisitors);
    }

    public void selectNumberOfPayingGuests(String numberOfPayingGuests) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfPayingGuestsDropDown, numberOfPayingGuests);
    }

    public void selectNumberOfChildren(String numberOfChildren) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfChildrenDropDown, numberOfChildren);
    }
}
