package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.PaymentDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by pgnanasekaran on 16/09/2015.
 */
public class LegacyBackOfficeMainPage extends BasePageObject
{
    public LegacyBackOfficeMainPage(WebDriver driver)
    {
        super(driver);
    }

    public String policyNumber;

    public String customerId;

    public String makePaymentByCard() throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.continueToNextPage();
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("netbanx"));
        enterCardDetails();
        return getPolicyNumber();
    }

    private void enterCardDetails() throws Exception
    {
        BOPaymentPage paymentPage = PageFactory.initElements(driver, BOPaymentPage.class);
        paymentPage.waitForPresenceOfCardNumberInputBox();
        paymentPage.enterCardNumber(new PaymentDetails().getCardNumber());
        paymentPage.selectExpiryMonth(new PaymentDetails().getExpiryMonth());
        paymentPage.selectExpiryYear(new PaymentDetails().getExpiryYear());
        paymentPage.enterCardVerificationValue(new PaymentDetails().getSecurityCode());
        paymentPage.clickPayNowButton();
    }

    public String getPolicyNumber()
    {
        NetbanxPurchaseFinishedDetails netbanxPurchaseFinishedDetails = PageFactory.initElements(driver, NetbanxPurchaseFinishedDetails.class);
        policyNumber = netbanxPurchaseFinishedDetails.getPolicyNumber();
        customerId = netbanxPurchaseFinishedDetails.getCustomerId();
        return netbanxPurchaseFinishedDetails.getPolicyNumber();
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void selectAmendEnquiry()
    {
        YourQuotesPage yourQuotesPage = PageFactory.initElements(driver, YourQuotesPage.class);
        yourQuotesPage.waitForQuotesPage();
        yourQuotesPage.amendEnquiryBackoffice();
    }

    public void modifyStartDatePSPage(final String prevStartDate, final String startDate) throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.modifyStartDateBackOffice(prevStartDate, startDate);
    }

    public String makePaymentByDirectDebit(String directDebitProvider) throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.clickPayByDirectDebit();
        policySummaryPage.continueToNextPage();
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("netbanx"));
        enterDirectDebitDetails(directDebitProvider);
        return getPolicyNumber();
    }

    private void enterDirectDebitDetails(String directDebitProvider) throws Exception
    {
        BOPaymentPage paymentPage = PageFactory.initElements(driver, BOPaymentPage.class);
        paymentPage.waitForPresenceOfCardNumberInputBox();
        paymentPage.enterCardNumber(new PaymentDetails().getCardNumber());
        paymentPage.selectExpiryMonth(new PaymentDetails().getExpiryMonth());
        paymentPage.selectExpiryYear(new PaymentDetails().getExpiryYear());
        paymentPage.enterCardVerificationValue(new PaymentDetails().getSecurityCode());
        if(directDebitProvider.equalsIgnoreCase("PCL"))
        {
            paymentPage.enterDefaultPCLDirectDebitDetails();
        }
        else
        {
            paymentPage.enterDefaultCloseDirectDebitDetails();
        }
        paymentPage.clickPayNowButton();
    }
}
