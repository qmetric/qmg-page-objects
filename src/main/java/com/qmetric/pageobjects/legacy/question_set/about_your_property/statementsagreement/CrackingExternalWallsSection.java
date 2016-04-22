package com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 08/03/2013
 */

public class CrackingExternalWallsSection extends BasePageObject
{

    @FindBy(id = "cracking_repair_work_1") WebElement crackingRepairWorkYes;

    @FindBy(id = "cracking_repair_work_2") WebElement crackingRepairWorkNo;

    public CrackingExternalWallsSection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isCrackingRepairWorkRadioButtonDisplayed()
    {
        try
        {
            return crackingRepairWorkYes.isDisplayed() && crackingRepairWorkNo.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void selectCrackingRepairWorkRadioButton(boolean yes)
    {
        if (yes)
        {
            jsClick(crackingRepairWorkYes);
        }
        else
        {
            jsClick(crackingRepairWorkNo);
        }
    }
}
