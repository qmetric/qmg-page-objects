package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpinnerElement extends BasePageObject
{
    public SpinnerElement(final WebDriver driver)
    {
        super(driver);
    }

    public void waitForSpinnerToFinish()
    {
        By spinnerLocator = By.id("spinner-container");
        waitForElementNotVisible(120, spinnerLocator);
    }
}
