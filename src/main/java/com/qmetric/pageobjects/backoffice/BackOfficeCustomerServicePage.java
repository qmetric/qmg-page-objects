package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by pgnanasekaran on 20/07/2015.
 */
public class BackOfficeCustomerServicePage extends BasePageObject
{
    @FindBy(className = "category-link")
    WebElement changeEmailBtn;

    @FindBy(css = "div[data-role='old-email']")
    WebElement oldEmailTextBoxSection;

    @FindBy(css = "div[data-role='new-email']")
    WebElement newEmailTextBoxSection;

    @FindBy(id = "change-email-button")
    WebElement updateEmailBtn;

    @FindBy(id = "confirm-email-change")
    WebElement confirmUpdateChkBox;

    @FindBy(css = ".mBoxContent")
    WebElement systemMsgBox;

    public BackOfficeCustomerServicePage(final WebDriver driver)
    {
        super(driver);
    }

    public void clickChangeEmailBtn()
    {
        changeEmailBtn.click();
    }

    public void enterOldEmailId(final String oldEmailId)
    {
        oldEmailTextBoxSection.findElement(By.cssSelector("input[data-id='input']")).sendKeys(oldEmailId);
    }

    public void enterNewEmailId(final String newEmailId)
    {
        newEmailTextBoxSection.findElement(By.cssSelector("input[data-id='input']")).sendKeys(newEmailId);
    }

    public void clickOnLookup(final String whichLookup)
    {
        if(whichLookup.equals("oldEmail"))
        {
            oldEmailTextBoxSection.findElement(By.cssSelector("button[data-id='lookup']")).click();
        }
        else
        {
            newEmailTextBoxSection.findElement(By.cssSelector("button[data-id='lookup']")).click();
        }
    }

    public void selectFirstRowRosser(String whichRosser)
    {
        TimeHelper.waitInSeconds(2);
        List<WebElement> elementsOfTable;
        if(whichRosser.equals("oldEmail"))
        {
            elementsOfTable = oldEmailTextBoxSection.findElement(By.cssSelector("div[data-id='rosser-data']")).findElements(By.cssSelector("table tbody tr"));
        }
        else
        {
            elementsOfTable = newEmailTextBoxSection.findElement(By.cssSelector("div[data-id='rosser-data']")).findElements(By.cssSelector("table tbody tr"));
        }
        if (elementsOfTable.size() != 0)
        {
            elementsOfTable.get(0).click();
        }
    }

    public void clickUpdateEmailBtn()
    {
        updateEmailBtn.click();
    }

    public void confirmEmailUpdate()
    {
        confirmUpdateChkBox.click();
    }

    public String checkConfirmationMessage()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                waitForElementVisible(30, systemMsgBox);
                return systemMsgBox.getText();
            }
        }.execute();
    }
}
