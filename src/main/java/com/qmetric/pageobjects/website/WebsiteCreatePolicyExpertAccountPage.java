package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by vketipisz on 17/04/15.
 */
public class WebsiteCreatePolicyExpertAccountPage extends BasePageObject
{

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button.enquirySubmit")
    private List<WebElement> submitButtons;

    @FindBy(id = "loginPassword")
    private WebElement loginPasswordField;

    public WebsiteCreatePolicyExpertAccountPage(WebDriver driver)
    {
        super(driver);
    }

    public void enterPassword(String password)
    {
        waitForElementPresent(8,"#password");
        passwordField.sendKeys(password);
    }

    public void login(String password)
    {
        waitForElementPresent(8,"#loginPassword");
        loginPasswordField.sendKeys(password);
    }

    public void clickContinue()
    {
        //submitButtons.get(1).click();
        for( WebElement continueButton : submitButtons)
        {
            if(continueButton.isDisplayed())
            {
                jsClick(continueButton);
                break;
            }
        }
    }


}
