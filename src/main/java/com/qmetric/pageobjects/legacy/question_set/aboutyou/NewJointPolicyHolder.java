package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 14/03/2013
 */
public class NewJointPolicyHolder extends BasePageObject
{

    private static final String ID = "joint_policy_holder";

    private final WebElement jointHolderTitleDropDown;

    private final WebElement jointHolderFirstNameTextBox;

    private final WebElement jointHolderLastNameTextBox;

    private final WebElement jointHolderDobDayDropDown;

    private final WebElement jointHolderDobMonthDropDown;

    private final WebElement jointHolderDobYearDropDown;

    private final WebElement jointHolderOccupationTextBox;

    private final WebElement saveJointPolicyHolderButton;

    public NewJointPolicyHolder(WebDriver driver, WebElement container, int index)
    {
        super(driver);

        jointHolderTitleDropDown = container.findElement(By.id(ID + index + "_title"));
        jointHolderFirstNameTextBox = container.findElement(By.id(ID + index + "_first_name"));
        jointHolderLastNameTextBox = container.findElement(By.id(ID + index + "_last_name"));
        jointHolderDobDayDropDown = container.findElement(By.id("day-dob" + (index + 1)));
        jointHolderDobMonthDropDown = container.findElement(By.id("month-dob" + (index + 1)));
        jointHolderDobYearDropDown = container.findElement(By.id("year-dob" + (index + 1)));
        jointHolderOccupationTextBox = container.findElement(By.id(ID + index + "_occupation"));
        saveJointPolicyHolderButton = container.findElement(By.className("addition"));
    }

    /**
     * ***                                         Verify elements presence                                    ******
     */

    public boolean isJointHolderTitleDropDownDisplayed()
    {
        try
        {
            return jointHolderTitleDropDown.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isFirstNameTextBoxDisplayed()
    {
        try
        {
            return jointHolderFirstNameTextBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isLastNameTextBoxDisplayed()
    {
        try
        {
            return jointHolderLastNameTextBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean areDateOfBirthDropDownsDisplayed()
    {
        try
        {
            return jointHolderDobDayDropDown.isDisplayed() &&
                   jointHolderDobMonthDropDown.isDisplayed() &&
                   jointHolderDobYearDropDown.isDisplayed();
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
            return jointHolderOccupationTextBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isSaveButtonDisplayed()
    {
        try
        {
            return saveJointPolicyHolderButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * *****                                      User interactions                                           *******
     */

    public void selectJointHolderTitle(String title) throws Exception
    {
        selectDropDownValueByVisibleText(jointHolderTitleDropDown, title);
    }

    public void enterJointHolderFirstName(String firstName)
    {
        enterTextInput(jointHolderFirstNameTextBox, firstName);
    }

    public void enterJointHolderLastName(String lastName)
    {
        enterTextInput(jointHolderLastNameTextBox, lastName);
    }

    public void selectJointHolderDayOfBirth(String dayOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(jointHolderDobDayDropDown, dayOfBirth);
    }

    public void selectJointHolderMonthOfBirth(String monthOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(jointHolderDobMonthDropDown, monthOfBirth);
    }

    public void selectJointHolderYearOfBirth(String yearOfBirth) throws Exception
    {
        selectDropDownValueByVisibleText(jointHolderDobYearDropDown, yearOfBirth);
    }

    public void enterJointHolderOccupation(final String occupation)
    {
        enterTextInput(jointHolderOccupationTextBox, occupation);
        new WebDriverWait(driver, 10).pollingEvery(2, TimeUnit.SECONDS).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return jointHolderOccupationTextBox.getAttribute("value").equals(occupation);
            }
        });
        pause(1000);
        jointHolderOccupationTextBox.sendKeys(Keys.ARROW_DOWN);
        jointHolderOccupationTextBox.sendKeys(Keys.RETURN);
    }

    public void savePolicyHolder()
    {
        jsClick(saveJointPolicyHolderButton);
    }
}
