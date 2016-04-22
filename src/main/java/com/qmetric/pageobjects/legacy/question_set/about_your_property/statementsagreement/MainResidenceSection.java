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

public class MainResidenceSection extends BasePageObject
{

    @FindBy(id = "property_use") WebElement propertyUseDropDown;

    @FindBy(id = "tenancy_agreement_1") WebElement minimum6MonthsTenancyAgreementYes;

    @FindBy(id = "tenancy_agreement_2") WebElement minimum6MonthsTenancyAgreementNo;

    @FindBy(id = "tenant_type") WebElement tenantTypeDropDown;

    @FindBy(id = "number_of_tenants") WebElement numberOfTenantsDropDown;

    @FindBy(id = "number_of_families") WebElement numberOfFamiliesDropDown;

    @FindBy(id = "property_has_bedsits_1") WebElement propertyBedsitsYes;

    @FindBy(id = "property_has_bedsits_2") WebElement propertyBedsitsNo;

    @FindBy(id = "number_of_bedsits") WebElement numberOfBedsitsDropDown;

    public MainResidenceSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isPropertyUseDropDownDisplayed()
    {
        try
        {
            return propertyUseDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isMinimum6MonthsTenancyRadioButtonDisplayed()
    {
        try
        {
            return minimum6MonthsTenancyAgreementYes.isDisplayed() && minimum6MonthsTenancyAgreementNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isTenantTypeDropDownDisplayed()
    {
        try
        {
            return tenantTypeDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfTenantsDropDownDisplayed()
    {
        try
        {
            return numberOfTenantsDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfFamiliesDropDownDisplayed()
    {
        try
        {
            return numberOfFamiliesDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isPropertyBedsitsRadioButtonDisplayed()
    {
        try
        {
            return propertyBedsitsYes.isDisplayed() && propertyBedsitsNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isNumberOfBedsitsDropDownDisplayed()
    {
        try
        {
            return numberOfBedsitsDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getPropertyUseDropDownElementsText()
    {
        return getDropdownElementsText(propertyUseDropDown);
    }

    public List<String> getTenantTypeDropDownElementsText()
    {
        return getDropdownElementsText(tenantTypeDropDown);
    }

    public List<String> getNumberOfTenantsDropDownElementsText()
    {
        return getDropdownElementsText(numberOfTenantsDropDown);
    }

    public List<String> getNumberOfFamiliesDropDownElementsText()
    {
        return getDropdownElementsText(numberOfFamiliesDropDown);
    }

    public List<String> getNumberOfBedsitsDropDownElementsText()
    {
        return getDropdownElementsText(numberOfBedsitsDropDown);
    }

    public void selectPropertyUse(String propertyUse) throws Exception
    {
        selectDropDownValueByVisibleText(propertyUseDropDown, propertyUse);
    }

    public void selectTenantType(String tenantType) throws Exception
    {
        selectDropDownValueByVisibleText(tenantTypeDropDown, tenantType);
    }

    public void selectNumberOfTenants(String numberOfTenants) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfTenantsDropDown, numberOfTenants);
    }

    public void selectNumberOfFamilies(String numberOfFamilies) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfFamiliesDropDown, numberOfFamilies);
    }

    public void selectNumberOfBedsits(String numberOfBedsits) throws Exception
    {
        selectDropDownValueByVisibleText(numberOfBedsitsDropDown, numberOfBedsits);
    }

    public void selectMinimum6MonthsTenancyAgreementRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(minimum6MonthsTenancyAgreementYes);
        }
        else
        {
            jsClick(minimum6MonthsTenancyAgreementNo);
        }
    }

    public void selectPropertyHasBedsitsRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(propertyBedsitsYes);
        }
        else
        {
            jsClick(propertyBedsitsNo);
        }
    }
}
