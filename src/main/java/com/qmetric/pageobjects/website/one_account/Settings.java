package com.qmetric.pageobjects.website.one_account;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/06/2014
 */
public class Settings extends BasePageObject
{
    @FindBy(css = "input#marketing")
    WebElement marketingPreferenceCheckBox;

    @FindBy(id = "old-password")
    WebElement currentPasswordInputBox;

    @FindBy(id = "new-password")
    WebElement newPasswordInputBox;

    @FindBy(id = "change-password")
    WebElement updatePasswordButton;

    @FindBy(css = ".text")
    WebElement passwordUpdateText;

    public Settings(WebDriver driver)
    {
        super(driver);
    }

    public void clickMarketingPreferenceCheckBox()
    {
        LOG.info("Change settings - clicking marketing preference check box");
        marketingPreferenceCheckBox.click();
    }

    public void insertCurrentPassword(String currentPassword)
    {
        LOG.info("Change settings - inserting current password: " + currentPassword);
        enterTextInput(currentPasswordInputBox, currentPassword);
    }

    public void insertNewPassword(String newPassword)
    {
        LOG.info("Change settings - inserting new password " + newPassword);
        enterTextInput(newPasswordInputBox, newPassword);
    }

    public void clickUpdatePasswordButton()
    {
        LOG.info("Change settings - clicking on update password button");
        updatePasswordButton.click();
    }

    public String getPasswordUpdatedText()
    {
        LOG.info("Get password updated text");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(passwordUpdateText));
        return passwordUpdateText.getText();
    }
}
