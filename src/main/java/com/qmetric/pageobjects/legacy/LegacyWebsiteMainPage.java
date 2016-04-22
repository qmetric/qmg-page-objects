package com.qmetric.pageobjects.legacy;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.PaymentDetails;
import com.qmetric.shared.SharedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by pgnanasekaran on 15/09/2015.
 */
public class LegacyWebsiteMainPage extends BasePageObject
{
    @FindBy(id = "button-home-1")
    WebElement compareQuotesButton;

    LegacyNewAccountCreationPage legacyNewAccountCreationPage;

    public LegacyWebsiteMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void waitForPageToLoad()
    {
        waitForElementPresent(120, ".nav-list");
    }

    public void clickOnCompareQuotes()
    {
        jsClick(compareQuotesButton);
    }

    public boolean isQuestionSetPage()
    {
        return driver.getTitle().equalsIgnoreCase("Get a quote");
    }

    public void createNewAccount() throws Exception
    {
        legacyNewAccountCreationPage = PageFactory.initElements(driver, LegacyNewAccountCreationPage.class);
        legacyNewAccountCreationPage.createAccount();
    }

    public boolean isQuotesPanelPage()
    {
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equalsIgnoreCase("Quote panel");
            }
        });
        return driver.getTitle().equalsIgnoreCase("Quote panel");
    }

    public boolean isPolicySummaryPage()
    {
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equalsIgnoreCase("Policy summary");
            }
        });
        return driver.getTitle().equalsIgnoreCase("Policy summary");
    }

    public void makePaymentByCard() throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.continueToNextPage();
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equals("Payment");
            }
        });
        enterCardDetails();
        netbanxAuthentication();
        SharedData.policyNumber = getPolicyNumber();
    }

    public void makePaymentByCardWebsite() throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.continueToNextPageAggFlow();
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equals("Payment");
            }
        });
        enterCardDetails();
        netbanxAuthentication();
        SharedData.policyNumber = getPolicyNumber();
    }

    private void enterCardDetails() throws Exception
    {
        LegacyWebsitePaymentPage legacyWebsitePaymentPage = PageFactory.initElements(driver, LegacyWebsitePaymentPage.class);
        legacyWebsitePaymentPage.waitForPresenceOfCardNumberInputBox();
        legacyWebsitePaymentPage.enterCardNumber(new PaymentDetails().getCardNumber());
        legacyWebsitePaymentPage.selectExpiryMonth(new PaymentDetails().getExpiryMonth());
        legacyWebsitePaymentPage.selectExpiryYear(new PaymentDetails().getExpiryYear());
        legacyWebsitePaymentPage.enterCardVerificationValue(new PaymentDetails().getSecurityCode());
        legacyWebsitePaymentPage.acceptTsAndCs();
        legacyWebsitePaymentPage.clickPayNowButton();

    }

    private void netbanxAuthentication()
    {
        NetbanxConfirmationPage netbanxConfirmationPage = PageFactory.initElements(driver, NetbanxConfirmationPage.class);
        netbanxConfirmationPage.switchToNetbanxFrame();
        netbanxConfirmationPage.clickOnAuthenticationSuccessfulButton();
        netbanxConfirmationPage.switchToMainFrame();
    }

    public String getPolicyNumber()
    {
        NetbanxPurchaseFinishedDetails netbanxPurchaseFinishedDetails = PageFactory.initElements(driver, NetbanxPurchaseFinishedDetails.class);
        String policyNumber = netbanxPurchaseFinishedDetails.getPolicyNumberWebSite();
        driver.switchTo().defaultContent();
        return policyNumber;
    }

    public void amendEnquiry()
    {
        YourQuotesPage yourQuotesPage = PageFactory.initElements(driver, YourQuotesPage.class);
        yourQuotesPage.waitForQuotesPage();
        yourQuotesPage.amendEnquiry();
    }

    public void modifyStartDatePSPage(final String prevStartDate, final String startDate) throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.modifyStartDate(prevStartDate, startDate);
    }

    public void makePaymentByDirectDebitWebsite(final String directDebitProvider) throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.clickPayByDirectDebit();
        policySummaryPage.continueToNextPageAggFlow();
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equals("Payment");
            }
        });
        enterDirectDebitDetails(directDebitProvider);
        netbanxAuthentication();
        SharedData.policyNumber = getPolicyNumber();
    }

    public void makePaymentByDirectDebit(final String directDebitProvider) throws Exception
    {
        PolicySummaryPage policySummaryPage = PageFactory.initElements(driver, PolicySummaryPage.class);
        policySummaryPage.clickPayByDirectDebit();
        policySummaryPage.continueToNextPage();
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return driver.getTitle().equals("Payment");
            }
        });
        enterDirectDebitDetails(directDebitProvider);
        netbanxAuthentication();
        SharedData.policyNumber = getPolicyNumber();
    }

    private void enterDirectDebitDetails(String directDebitProvider) throws Exception
    {
        LegacyWebsitePaymentPage legacyWebsitePaymentPage = PageFactory.initElements(driver, LegacyWebsitePaymentPage.class);
        legacyWebsitePaymentPage.waitForPresenceOfCardNumberInputBox();
        legacyWebsitePaymentPage.enterCardNumber(new PaymentDetails().getCardNumber());
        legacyWebsitePaymentPage.selectExpiryMonth(new PaymentDetails().getExpiryMonth());
        legacyWebsitePaymentPage.selectExpiryYear(new PaymentDetails().getExpiryYear());
        legacyWebsitePaymentPage.enterCardVerificationValue(new PaymentDetails().getSecurityCode());
        if(directDebitProvider.equalsIgnoreCase("PCL"))
            legacyWebsitePaymentPage.enterDefaultPCLDirectDebitDetails();
        else
            legacyWebsitePaymentPage.enterDefaultCloseDirectDebitDetails();;
        legacyWebsitePaymentPage.clickPayNowButton();
    }
}
