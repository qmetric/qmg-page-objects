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
public class BankruptSection extends BasePageObject
{

    @FindBy(id = "bankruptcy_type") WebElement bankruptcyTypeDropDown;

    @FindBy(id = "bankruptcy_discharged_1") WebElement bankruptcyDischargedYes;

    @FindBy(id = "bankruptcy_discharged_2") WebElement bankruptcyDischargedNo;

    @FindBy(id = "bankruptcy_discharge_month") WebElement bankruptcyDischargeMonthDropDown;

    @FindBy(id = "bankruptcy_discharge_year") WebElement bankruptcyDischargeYearDropDown;

    public BankruptSection(WebDriver driver)
    {
        super(driver);
    }

    /**
     * ********                                  Verify presence of elements                              ***********
     */

    public boolean isBankruptcyTypeDropDownDisplayed()
    {
        try
        {
            return bankruptcyTypeDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isBankruptcyDischargedRadioButtonDisplayed()
    {
        try
        {
            return bankruptcyDischargedYes.isDisplayed() && bankruptcyDischargedNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isBankruptcyDischargeMonthDropDownDisplayed()
    {
        try
        {
            return bankruptcyDischargeMonthDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isBankruptcyDischargeYearDropDownDisplayed()
    {
        try
        {
            return bankruptcyDischargeYearDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * ********                                      Get dropdown values                                  ***********
     */

    public List<String> getBankruptcyDropDownElementsText()
    {
        return getDropdownElementsText(bankruptcyTypeDropDown);
    }

    /**
     * ********                                    Select dropdown values                                 ***********
     */

    public void selectBankruptcyType(String bankruptcyType) throws Exception
    {
        selectDropDownValueByVisibleText(bankruptcyTypeDropDown, bankruptcyType);
    }

    public void selectBankruptcyDischargeMonth(String bankruptcyDischargeMonth) throws Exception
    {
        selectDropDownValueByVisibleText(bankruptcyDischargeMonthDropDown, bankruptcyDischargeMonth);
    }

    public void selectBankruptcyDischargeYear(String bankruptcyDischargeYear) throws Exception
    {
        selectDropDownValueByVisibleText(bankruptcyDischargeYearDropDown, bankruptcyDischargeYear);
    }

    /**
     * ********                                    Select check-box values                                ***********
     */

    public void selectBankruptDischargedRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(bankruptcyDischargedYes);
        }
        else
        {
            jsClick(bankruptcyDischargedNo);
        }
    }
}
