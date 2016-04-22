package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 01/12/2014
 */

public class BackofficeCancellationPopUp extends BasePageObject
{
    @FindBy(css = "li.progress-step[data-id='step1']")
    WebElement categorySelectionLink;

    @FindBy(css = "li.progress-step[data-id='step2']")
    WebElement policyCancellationLink;

    @FindBy(css = "select.cancel-reason[data-id='cancel-reason']")
    WebElement cancelReasonDropDown;

    @FindBy(css = "input[data-id='cancelThisPolicy']")
    List<WebElement> cancelThisPolicyCheckBoxes;

    @FindBy(css = ".policy-action-modal button[data-id='next-step']")
    WebElement calculateCancellationButton;

    @FindBy(css = "span[data-id='cancel-to-inception'] > div > button[data-value='yes']")
    WebElement cancelToInceptionYesButton;

    @FindBy(css = "span[data-id='cancel-to-inception'] > div > button[data-value='no']")
    WebElement cancelToInceptionNoButton;

    @FindBy(css = "button[data-data='CHEQUE']")
    WebElement refundMethodChequeButton;

    @FindBy(css = "button[data-data='CARD']")
    WebElement refundMethodCardButton;

    @FindBy(css = "input[data-id='amount-refunded-choice']")
    WebElement confirmNetbanxCancellationCheckBox;

    @FindBy(css = "input[data-id='amount-refunded-ref']")
    WebElement netbanxReferenceInputBox;

    @FindBy(css = "input[data-id='really-cancel']")
    WebElement confirmCancellationCheckBox;

    @FindBy(css = "button[data-id='confirm']")
    WebElement cancelThePoliciesButton;

    @FindBy(css = "input.width-2")
    WebElement cancellationDate;

    @FindBy(css = "div[data-id='step1-content']")
    WebElement categorySelectionTab;

    @FindBy(css = "div[data-id='step2-content']")
    WebElement cancellationTab;

    private final String defaultNetbanxReference = "a1234567-b12c-34aa-1cde-1234567891aa";

    public BackofficeCancellationPopUp(WebDriver driver)
    {
        super(driver);
        TimeHelper.waitInSeconds(2);
    }

    public void selectCancelReason(String cancelReason) throws Exception
    {
        selectDropDownValueByVisibleText(cancelReasonDropDown, cancelReason);
    }

    public void clickCancelThisPolicyCheckBox()
    {
        for (WebElement cancelThisPolicyCheckBox : cancelThisPolicyCheckBoxes)
        {
            cancelThisPolicyCheckBox.click();
        }
    }

    public void clickCancelToInceptionYesButton()
    {
        cancelToInceptionYesButton.click();
    }

    public void clickCancelToInceptionNoButton()
    {
        cancelToInceptionNoButton.click();
    }

    private void chooseCancellationDate(int days)
    {
        String date = new DateTime().now().minusDays(days).toString("yyyy-MM-dd");
        enterTextInput(cancellationDate, date);
        cancellationDate.sendKeys(Keys.ENTER);
        clickOnCancellationPopUp();
    }

    public void clickOnCancellationPopUp()
    {
        TimeHelper.waitInSeconds(1);
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                try
                {
                    WebElement systemMsgBox = getVisibleElementFromElementsList(By.className("mBoxContainer "));
                    jsClick(systemMsgBox);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void clickCalculateCancellationButton()
    {
        calculateCancellationButton.click();
    }

    public void chooseChequeAsRefundMethod()
    {
        refundMethodChequeButton.click();
    }

    public void chooseCardAsRefundMethod()
    {
        refundMethodCardButton.click();
        clickOnConfirmNetbanxCancellationCheckBox();
        enterNetbanxReference();
    }

    public void clickOnConfirmNetbanxCancellationCheckBox()
    {
        confirmNetbanxCancellationCheckBox.click();
    }

    public void clickConfirmCancellationCheckBox()
    {
        confirmCancellationCheckBox.click();
    }

    public void clickCancelThePoliciesButton()
    {
        cancelThePoliciesButton.click();
    }

    public void waitForPopupNotVisible()
    {
        waitForElementNotVisible(60, "#modal-anchor");
    }

    public void enterNetbanxReference()
    {
        enterTextInput(netbanxReferenceInputBox, defaultNetbanxReference);
    }

    public void enterCancelInputs(Map<String, String> option) throws Throwable
    {
        selectCancelReason(option.get("CancellationReason"));
        if (option.get("CancelToInception").equals("true"))
        {
            clickCancelToInceptionYesButton();
        }
        else
        {
            clickCancelToInceptionNoButton();
            if (option.get("Cancellationdate").equals("today"))
            {
                chooseCancellationDate(0);
            }
            else
            {
                String date = option.get("Cancellationdate");
                String numberOfdays = date.substring(date.indexOf("-") + 1, date.length());
                chooseCancellationDate(Integer.parseInt(numberOfdays));
            }
        }
        clickCancelThisPolicyCheckBox();
        clickCalculateCancellationButton();
    }

    public void waitForCategorySelectionTabToDisappear()
    {
       waitForElementNotVisible(30, categorySelectionTab);
    }

    public void waitForCancellationTabToAppear()
    {
        waitForElementVisible(30, cancellationTab);
    }
}
