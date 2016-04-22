package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BackOfficeCustomerInfoTab extends BasePageObject
{
    @FindBy(id = "new-customer-email")
    WebElement newCustomerEmailTextBox;

    @FindBy(css = "a[data-type=updateEmail]")
    WebElement updateButton;

    @FindBy(id = "update-email-message")
    WebElement updateMessageElement;

    @FindBy(id = "policy-informations")
    WebElement policyInformationSection;

    public BackOfficeCustomerInfoTab(WebDriver driver)
    {
        super(driver);
    }

    public void updateEmailAddress(String emailAddress)
    {
        enterTextInput(newCustomerEmailTextBox, emailAddress);
        jsClick(updateButton);
    }

    public void waitForEmailAddressUpdatedText()
    {
        webDriverWaitWithPolling(30, 1, new Predicate<WebDriver>()
        {
            @Override public boolean apply(final WebDriver driver)
            {
                return updateMessageElement.getText().equals("Email updated successfully");
            }
        });
    }

    public String getCID()
    {
        WebElement cIdElement = policyInformationSection.findElement(By.cssSelector("div > div.content.tabbed-content > div > p"));
        return cIdElement.getText().split(":")[1].trim();
    }
}
