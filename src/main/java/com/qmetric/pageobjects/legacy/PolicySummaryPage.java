package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.legacy.question_set.coveroptions.DatePickerPolicySummary;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PolicySummaryPage extends BasePageObject
{
    @FindBy(id = "auto_renewal_yes")
    private WebElement autoRenewalYes;

    @FindBy(id = "auto_renewal_no")
    private WebElement autoRenewalNo;

    @FindBy(css = "button[data-action-url*='LEGAL_EXPENSES_AND_ID_THEFT']")
    private WebElement addLegalExpensesButton;

    @FindBy(css = "#v2PolicySummaryFormButton")
    private WebElement nextPageButton;

    @FindBy(id = "checkDetailsOverlay")
    private WebElement checkYourDetails;

    @FindBy(id = "submitPolicySummaryPage")
    //@FindBy(css = "div.modal-box #submitPolicySummaryPage")
    private WebElement checkDetailsNextPageButton;

    @FindBy(id = "contentWrapper")
    private WebElement contentWrapper;

    @FindBy(css = "#newPaymentSelector span.price")
    private WebElement premium;

    @FindBy(css = "input#confirmDetailsCheckbox.required.mgnRgt.chk")
    private WebElement renewalDetailsConfirmationCheckBox;

    @FindBy(css = "input[value='CHEQUE']")
    private WebElement refundType;

    @FindBy(css = "a.paymentTypeMonthly")
    private WebElement directDebitBox;

    @FindBy(css = "a.paymentTypeAnnual")
    private WebElement cardPaymentBox;

    @FindBy(css = "div#checkDetailsOverview")
    private WebElement buyPolicyConfirmationBox;

    @FindBy(css = "#sidePSWrapper span")
    private WebElement convertProspectToolMsg;

    @FindBy(css = "button[data-title='PolicyDetails']")
    private WebElement confirmAndBuyButton;

    @FindBy(css = "div.modal-footer > button")
    private WebElement modalConfirmAndBuyButton;

    @FindBy(id ="commissionDiscount")
    private WebElement discountCommision;

    @FindBy(id = "applyDiscount")
    private WebElement applyDiscount;

    @FindBy(id = "confirmDetailsCheckbox")
    private WebElement confirmationChkBox;

    @FindBy(id = "mta-purchase-form")
    private WebElement MTAReturned;

    @FindBy(id="mtaCompletionSubmit")
    private WebElement confirmRefundButton;

    public PolicySummaryPage(WebDriver driver)
    {
        super(driver);
        pause(3000);
    }

    public boolean isAutoRenewalQuestionDisplayed()
    {
        try
        {
            return autoRenewalYes.isDisplayed() && autoRenewalNo.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void setAutoRenewal(boolean isAutoRenewal)
    {
        if (isAutoRenewalQuestionDisplayed())
        {
            pause(2500);
            if (isAutoRenewal)
            {
                jsClick(autoRenewalYes);
            }
            else
            {
                jsClick(autoRenewalNo);
            }
        }
    }

    public WebElement getContentWrapper()
    {
        return contentWrapper;
    }

    public void continueToNextPage()
    {
        waitForElementPresent(5, "#v2PolicySummaryFormButton");
        waitForElementClickable(10, "#v2PolicySummaryFormButton");
        jsClick(nextPageButton);
        waitForElementVisible(10, buyPolicyConfirmationBox);
        jsClick(checkDetailsNextPageButton);
    }

//    public void continueToNextPageMTA()
//    {
//        waitForElementPresent(5, "#v2PolicySummaryFormButton");
//        waitForElementClickable(10, "#v2PolicySummaryFormButton");
//        jsClick(nextPageButton);
//        waitForElementVisible(10, buyPolicyConfirmationBox);
//        jsClick(checkDetailsNextPageButton);
//    }

    public void continueToNextPageAggFlow()
    {
        waitForElementPresent(5, "#v2PolicySummaryFormButton");
        waitForElementClickable(10, "#v2PolicySummaryFormButton");
        jsClick(nextPageButton);
//        waitForElementVisible(10, buyPolicyConfirmationBox);
//        jsClick(checkDetailsNextPageButton);
    }

    public String getPremium()
    {
        return premium.getText();
    }

    public boolean isRenewalDetailsConfirmationCheckBoxDisplayed()
    {
        try
        {
            return renewalDetailsConfirmationCheckBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickRenewalDetailsConfirmationCheckBox()
    {
        jsClick(renewalDetailsConfirmationCheckBox);
    }

    public void clickBuyPolicyNow()
    {
        checkDetailsNextPageButton.click();
    }

    public boolean isBuyNowButtonDisplayed()
    {
        try
        {
            return checkDetailsNextPageButton.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void clickPayByDirectDebit()
    {
        jsClick(directDebitBox);
        pause(2000);
    }

    public void clickPayByCard()
    {
        jsClick(cardPaymentBox);
        pause(2000);
    }

    public void clickAddLegalExpensesButton()
    {
        waitForElementPresent(8, ".optionalPolicyWording.summaryCover");
        jsClick(addLegalExpensesButton);
        pause(2500);
    }

    public boolean isConvertProspectToolMsgDisplayed()
    {
        try
        {
            return convertProspectToolMsg.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void clickFirstConfirmAndBuy()
    {
        waitForElementPresent(5, "#v2PolicySummaryFormButton");
        waitForElementClickable(10, "#v2PolicySummaryFormButton");
        WebElement nextPage = driver.findElement(By.id("v2PolicySummaryFormButton"));
        jsClick(nextPage);
    }

    public void clickSecondConfirmAndBuy()
    {
        waitForElementPresent(8, "button[data-title='PolicyDetails']");
        confirmAndBuyButton.click();
    }

    public void modifyStartDate(final String prevStartDate, final String startDate) throws Exception
    {
        DatePickerPolicySummary datePicker = PageFactory.initElements(driver, DatePickerPolicySummary.class);
        datePicker.clickOnStartDateInputBox();
        datePicker.selectDate(prevStartDate, startDate);
    }

    public void applyDiscount(String discount)
    {
        discountCommision.sendKeys(discount);
        jsClick(applyDiscount);
    }

    public void modifyStartDateBackOffice(final String prevStartDate, final String startDate) throws Exception
    {
        DatePickerPolicySummary datePicker = PageFactory.initElements(driver, DatePickerPolicySummary.class);
        datePicker.clickOnStartDateInputBoxBackOffice();
        datePicker.selectDate(prevStartDate, startDate);
    }

    public void selectConfirmDetailsChkBox()
    {
        jsClick(confirmationChkBox);
    }

    public boolean isMTAReturned()
    {
        return MTAReturned.isDisplayed();
    }

    public void setRefundType()
    {
        jsClick(refundType);
        jsClick(confirmRefundButton);
    }
}
