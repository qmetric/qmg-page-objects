package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebsitePurchaseConfirmationPage extends BasePageObject
{
    @FindBy(css = "div.confirmationpage")
    WebElement confirmationPageContainer;

    @FindBy(css = "a[href*='one-account']")
    WebElement oneAccountLink;

    public WebsitePurchaseConfirmationPage(final WebDriver driver)
    {
        super(driver);
    }

    public void waitForConfirmationPageToLoad()
    {
        waitForElementVisible(30, confirmationPageContainer);
    }

    public boolean ncdProofTextVisible()
    {
        return motorWebsiteConfirmationSections().get(1).getText().contains("No claims discount proof");
    }

    private List<WebElement> motorWebsiteConfirmationSections()
    {
        final By by = By.cssSelector("section[class='motor-section motor-confirmation']");
        webDriverWaitWithPolling(60, 3, predicateDriver -> !findElements(by).isEmpty());
        return findElements(by);
    }

    public void navigateToOneAccount()
    {
        waitForElementVisible(30, oneAccountLink);
        oneAccountLink.click();
    }
}
