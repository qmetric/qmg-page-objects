package com.qmetric.pageobjects.new_oneaccount;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OneAccountSpinner extends BasePageObject
{
    public OneAccountSpinner(final WebDriver driver)
    {
        super(driver);
    }

    public void waitForSpinnerToFinish()
    {
        By spinnerLocator = By.cssSelector("i[class=\"fa fa-spinner fa-pulse\"]");
        waitForElementNotVisible(120, spinnerLocator);
    }
}
