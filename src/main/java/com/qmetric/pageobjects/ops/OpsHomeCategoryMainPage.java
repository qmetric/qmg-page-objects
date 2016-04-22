package com.qmetric.pageobjects.ops;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pgnanasekaran on 18/06/2015.
 */
public class OpsHomeCategoryMainPage extends BasePageObject
{
    @FindBy(id = "businessLineHeading")
    WebElement businessLineHeading;

    @FindBy(id = "latestFormDef")
    WebElement formDefinitionButton;

    @FindBy(className = "ng-binding")
    List<WebElement> categoryLinks;

    public OpsHomeCategoryMainPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean hasPageLoaded()
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                waitForElementVisible(30, businessLineHeading);
                return (businessLineHeading.getText().equals("Insurance Category - Home"));
            }
        }.execute();
    }

    public void clickFormDefinitionButton()
    {
        formDefinitionButton.click();
        TimeHelper.waitInSeconds(1);
    }
}
