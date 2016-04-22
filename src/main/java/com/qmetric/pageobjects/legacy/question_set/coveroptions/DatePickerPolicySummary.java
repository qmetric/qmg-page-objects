package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 09/05/2013
 */
public class DatePickerPolicySummary extends DatePicker
{

    @FindBy(css = "input#policyStartDate") WebElement startDateInputBox;

    public DatePickerPolicySummary(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public void clickOnStartDateInputBox()
    {
        waitForElementVisible(5, startDateInputBox);
        pause(2000);
        boolean datePickerVisible = false;
        while (!datePickerVisible)
        {
            try
            {
                startDateInputBox.click();
                waitForElementVisible(5, "div.slider");
                datePickerVisible = true;
            }
            catch (TimeoutException ignore)
            {
            }
        }
    }

    @Override
    public void clickOnStartDateInputBoxBackOffice()
    {
        waitForElementVisible(5, startDateInputBox);
        pause(2000);
        startDateInputBox.click();
    }
}
