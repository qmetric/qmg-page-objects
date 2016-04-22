package com.qmetric.pageobjects.ops;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class OpsMainPage extends BasePageObject
{
    @FindBy(css = ".brand.ng-binding")
    WebElement brandingName;

    @FindBy(css = "li:nth-child(7) > a")
    WebElement login_button;

    @FindBy(css = "a[class=\"ng-binding\"]")
    List<WebElement> categoryLinks;

    @FindBy(css = ".span12>h1")
    WebElement heading;

    public OpsMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void waitForPageToLoad()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, brandingName);
                return null;
            }
        }.execute();
    }

    public void clickLoginButton()
    {
        login_button.isDisplayed();
        login_button.click();
        TimeHelper.waitInSeconds(1);
    }

    public void selectCategory(final String category)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                for (WebElement categoryLink : categoryLinks)
                {
                    if (categoryLink.getText().equalsIgnoreCase(category))
                    {
                        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(categoryLink));
                        jsClick(categoryLink);
                        TimeHelper.waitInSeconds(1);
                        break;
                    }
                }
                return null;
            }
        }.execute();
    }

    public void waitForCategoriesToLoad()
    {
        new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                waitForElementVisible(30, heading);
                return (heading.getText().equalsIgnoreCase("Insurance Categories"));
            }
        }.execute();
    }
}
