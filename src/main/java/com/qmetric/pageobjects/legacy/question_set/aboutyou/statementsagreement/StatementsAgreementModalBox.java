package com.qmetric.pageobjects.legacy.question_set.aboutyou.statementsagreement;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 15/03/2013
 */
public class StatementsAgreementModalBox extends BasePageObject
{

    @FindBy(id = "business_purposes_1") WebElement notUsedForBusinessPurposesAgree;

    @FindBy(id = "business_purposes_2") WebElement notUsedForBusinessPurposesDisagree;

    @FindBy(id = "occupants_declared_bankrupt_1") WebElement noOccupantsDeclaredBankruptAgree;

    @FindBy(id = "occupants_declared_bankrupt_2") WebElement noOccupantsDeclaredBankruptDisagree;

    @FindBy(id = "ccj_served_1") WebElement notServedCountyCourtJudgementAgree;

    @FindBy(id = "ccj_served_2") WebElement notServedCountyCourtJudgementDisagree;

    @FindBy(id = "occupants_insurance_declined_1") WebElement notDeclinedHomeInsuranceAgree;

    @FindBy(id = "occupants_insurance_declined_2") WebElement notDeclinedHomeInsuranceDisagree;

    @FindBy(id = "occupants_insurance_cancelled_1") WebElement notCancelledHomeInsuranceAgree;

    @FindBy(id = "occupants_insurance_declined_2") WebElement notDCancelledHomeInsuranceDisagree;

    @FindBy(id = "occupants_previously_convicted_1") WebElement noOccupantPreviouslyConvictedAgree;

    @FindBy(id = "occupants_previously_convicted_2") WebElement noOccupantPreviouslyConvictedDisagree;

    @FindBy(id = "confirmSOFDetails") WebElement confirmButton;

    public BusinessPurposesSection businessPurposesSection;

    public BankruptSection bankruptSection;

    public CountyCourtJudgementSection countyCourtJudgementSection;

    public StatementsAgreementModalBox(WebDriver driver)
    {
        super(driver);
    }

    /**
     * ********                                  Verify presence of elements                              ***********
     */

    public boolean isBusinessPurposeRadioButtonDisplayed()
    {
        try
        {
            return notUsedForBusinessPurposesAgree.isDisplayed() && notUsedForBusinessPurposesDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isOccupantsBankruptRadioButtonDisplayed()
    {
        try
        {
            return noOccupantsDeclaredBankruptAgree.isDisplayed() && noOccupantsDeclaredBankruptDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isCountyCourtJudgementRadioButtonDisplayed()
    {
        try
        {
            return notServedCountyCourtJudgementAgree.isDisplayed() && notServedCountyCourtJudgementDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isDeclinedHomeInsuranceRadioButtonDisplayed()
    {
        try
        {
            return notDeclinedHomeInsuranceAgree.isDisplayed() && notDeclinedHomeInsuranceDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isCancelledHomeInsuranceRadioButtonDisplayed()
    {
        try
        {
            return notCancelledHomeInsuranceAgree.isDisplayed() && notDCancelledHomeInsuranceDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isOccupantsConvictedRadioButtonDisplayed()
    {
        try
        {
            return noOccupantPreviouslyConvictedAgree.isDisplayed() && noOccupantPreviouslyConvictedDisagree.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /***********                                        Select checkboxes                                  ************/

    /**
     * If agree is false it will initialize BusinessPurposesSection
     *
     * @param agree
     */
    public void selectBusinessPurposesRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notUsedForBusinessPurposesAgree);
        }
        else
        {
            jsClick(notUsedForBusinessPurposesDisagree);
            businessPurposesSection = PageFactory.initElements(driver, BusinessPurposesSection.class);
        }
    }

    /**
     * If agree is false it will initialize BankruptSection
     *
     * @param agree
     */
    public void selectOccupantsBankruptRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(noOccupantsDeclaredBankruptAgree);
        }
        else
        {
            jsClick(noOccupantsDeclaredBankruptDisagree);
            bankruptSection = PageFactory.initElements(driver, BankruptSection.class);
        }
    }

    /**
     * If agree is false it will initialize Count
     *
     * @param agree
     */
    public void selectCountyCourtJudgementRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notServedCountyCourtJudgementAgree);
        }
        else
        {
            jsClick(notServedCountyCourtJudgementDisagree);
            countyCourtJudgementSection = PageFactory.initElements(driver, CountyCourtJudgementSection.class);
        }
    }

    public void selectNotDeclinedHomeInsuranceRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notDeclinedHomeInsuranceAgree);
        }
        else
        {
            jsClick(notDeclinedHomeInsuranceDisagree);
        }
    }

    public void selectNotCancelledHomeInsuranceRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(notCancelledHomeInsuranceAgree);
        }
        else
        {
            jsClick(notDCancelledHomeInsuranceDisagree);
        }
    }

    public void selectNoOccupantsConvictedRadioButton(boolean agree)
    {
        if (agree)
        {
            jsClick(noOccupantPreviouslyConvictedAgree);
        }
        else
        {
            jsClick(noOccupantPreviouslyConvictedDisagree);
        }
    }

    /**
     * ********                                        General actions                                    ***********
     */

    public void closeStatementsForm()
    {
        final WebElement statementsFormTable = driver.findElement(By.className("modal-box"));
        waitForElementVisible(10, statementsFormTable);
        WebElement cancelLink = statementsFormTable.findElement(By.className("modal-close"));
        jsClick(cancelLink);
        waitForElementNotVisible(10, statementsFormTable);
    }

    public void confirmStatements()
    {
        jsClick(confirmButton);
    }

    public boolean isDisplayed()
    {
        final WebElement statementsFormTable = driver.findElement(By.className("modal-box"));
        return statementsFormTable.isDisplayed();
    }
}
