package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.legacy.question_set.aboutyou.statementsagreement.StatementsAgreementModalBox;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 14/03/2013
 */
public class AboutYouSection extends BasePageObject
{

    @FindBy(id = "day-dob1") WebElement dobDayDropDown;

    @FindBy(id = "month-dob1") WebElement dobMonthDropDown;

    @FindBy(id = "year-dob1") WebElement dobYearDropDown;

    @FindBy(id = "day-dob5") WebElement dobDayRenewalDropDown;

    @FindBy(id = "month-dob5") WebElement dobMonthRenewalDropDown;

    @FindBy(id = "year-dob5") WebElement dobYearRenewalDropDown;

    @FindBy(id = "marital_status") WebElement maritalStatusDropDown;

    @FindBy(id = "occupation") WebElement occupationTextBox;

    @FindBy(id = "does_anyone_in_property_smoke_1") WebElement smokerYesRadioButton;

    @FindBy(id = "does_anyone_in_property_smoke_2") WebElement smokerNoRadioButton;

    @FindBy(id = "about_you_statement_1") WebElement statementsYesRadioButton;

    @FindBy(id = "about_you_statement_2") WebElement statementsNoRadioButton;

    public StatementsAgreementModalBox statementsAgreementModalBox;

    public AboutYouSection(WebDriver driver)
    {
        super(driver);
    }

    /**
     * ****                                       Verify elements presence                                     ******
     */

    public boolean areDateOfBirthDropDownsDisplayed()
    {
        try
        {
            return dobDayDropDown.isDisplayed() && dobMonthDropDown.isDisplayed() && dobYearDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isMaritalStatusDropDownDisplayed()
    {
        try
        {
            return maritalStatusDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isOccupationTextBoxDisplayed()
    {
        try
        {
            return occupationTextBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isSmokerRadioButtonDisplayed()
    {
        try
        {
            return smokerYesRadioButton.isDisplayed() && smokerNoRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isAboutYouStatementsRadioButtonDisplayed()
    {
        try
        {
            return statementsYesRadioButton.isDisplayed() && statementsNoRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * ****                                       Get dropdown elements                                       ******
     */

    public List<String> getMaritalStatusDropDownElementsText()
    {
        return getDropdownElementsText(maritalStatusDropDown);
    }

    /**
     * ****                                            User interactions                                       ******
     */

    public void selectDayOfBirth(String dayOfBirth) throws Exception
    {
        waitForElementVisible(10, dobDayDropDown);
        selectDropDownValueByVisibleText(dobDayDropDown, dayOfBirth);
    }

    public void selectMonthOfBirth(String monthOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(dobMonthDropDown, monthOfBirth);
    }

    public void selectYearOfBirth(String yearOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(dobYearDropDown, yearOfBirth);
    }

    public void selectDayOfBirthRenewal(String dayOfBirth) throws Exception
    {
        waitForElementVisible(10, dobDayRenewalDropDown);
        selectDropDownValueByVisibleText(dobDayRenewalDropDown, dayOfBirth);
    }

    public void selectMonthOfBirthRenewal(String monthOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(dobMonthRenewalDropDown, monthOfBirth);
    }

    public void selectYearOfBirthRenewal(String yearOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(dobYearRenewalDropDown, yearOfBirth);
    }

    public void selectMaritalStatus(String maritalStatus) throws Exception
    {
        selectDropDownValueByVisibleText(maritalStatusDropDown, maritalStatus);
    }

    public void enterOccupation(final String occupation)
    {
        enterTextInput(occupationTextBox, occupation);
        new WebDriverWait(driver, 10).pollingEvery(2, TimeUnit.SECONDS).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return occupationTextBox.getAttribute("value").equals(occupation);
            }
        });
        pause(1000);
        occupationTextBox.sendKeys(Keys.ENTER);
    }

    public void enterOccupationAndValidate(final String occupation)
    {
        enterTextInput(occupationTextBox, occupation);
        pause(1000);
        occupationTextBox.sendKeys(Keys.ARROW_DOWN);
        occupationTextBox.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, 10).pollingEvery(1, TimeUnit.SECONDS).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return occupationTextBox.getAttribute("class").contains("validation-passed");
            }
        });
    }

    public void selectSmoker(boolean yes)
    {
        if (yes)
        {
            jsClick(smokerYesRadioButton);
        }
        else
        {
            jsClick(smokerNoRadioButton);
        }
    }

    /**
     * If the value of yes is false, it will initialize StatementsAgreementModalBox
     *
     * @param yes
     */
    public void selectStatements(boolean yes)
    {
        if (yes)
        {
            jsClick(statementsYesRadioButton);
        }
        else
        {
            jsClick(statementsNoRadioButton);
            statementsAgreementModalBox = PageFactory.initElements(driver, StatementsAgreementModalBox.class);
        }
    }
}
