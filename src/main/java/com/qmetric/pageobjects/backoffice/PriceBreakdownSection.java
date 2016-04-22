package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by vketipisz on 23/04/15.
 */
public class PriceBreakdownSection extends BasePageObject
{

    @FindBy(css = "div > dt") List<WebElement> dtElements;

    public PriceBreakdownSection(WebDriver driver)
    {
       super(driver);
    }


    public String getNetPremiumPrice()
    {
        return dtElements.get(8).getText();
    }



}
