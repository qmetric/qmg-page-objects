package com.qmetric.pageobjects.legacy;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class BOAccountDetailsPage extends BasePageObject
{

    @FindBy(id = "accountDetailsTab") WebElement accountDetailsTabButton;

    @FindBy(id = "policyTab") WebElement policyTab;

    @FindBy(className = "reset-password") WebElement passwordBox;

    @FindBy(css = "span#customerId") WebElement customerId;

    @FindBy(css = "button#btnUserAccountEdit") WebElement editAccountButton;

    @FindBy(css = "button#btnDeleteAccount.red") WebElement deleteAccountButton;

    @FindBy(css = "button#btnSuspendAccount") WebElement suspendAccountButton;

    @FindBy(id = "resetPasswordButton") WebElement resetPasswordlink;

    @FindBy(className = "generate-password") WebElement temporaryPasswordlink;

    @FindBy(className = "user-password") WebElement generatedPasswordText;

    @FindBy(css = "#btnEnquiry.button") WebElement newEnquiry;

    @FindBy(css = "#enquiriesTable.table-sortable") WebElement enquiriesTable;

    @FindBy(css = "#enquiriesTable.table-sortable tbody > tr") List<WebElement> enquirySummaryList;

    @FindBy(css = "#policiesTable.table-sortable") WebElement policiesTable;

    @FindBy(css = "#policiesTable.table-sortable tbody#policyRows > tr") List<WebElement> policyRows;

    @FindBy(css = "#policiesTable.table-sortable tbody > tr") List<WebElement> policySummaryList;

    @FindBy(css = "button#convertBtn") WebElement convertProspectButton;

    @FindBy(linkText = "Logout") WebElement logOutButton;

    @FindBy(id = "title") WebElement customerTitle;

    @FindBy(id = "firstName") WebElement customerFirstName;

    @FindBy(id = "lastName") WebElement customerLastName;

    @FindBy(id = "registeredEmail") WebElement customerRegisteredEmail;

    @FindBy(id = "prospectEmail") WebElement customerProspectEmail;

    @FindBy(id = "marketingEnabled") WebElement customerMarketing;

    @FindBy(id = "primaryPhoneNumber") WebElement customerPrimaryPhoneNumber;

    @FindBy(id = "secondaryPhoneNumber") WebElement customerSecondaryPhoneNumber;

    @FindBy(id = "a-p-title") WebElement customerAuthorisedPersonTitle;

    @FindBy(id = "a-p-firstname") WebElement customerAuthorisedPersonFirstName;

    @FindBy(id = "a-p-lastname") WebElement customerAuthorisedPersonLastName;

    @FindBy(id = "updateBtn") WebElement updateButton;

    @FindBy(id = "startMtaButton") WebElement startMtaButton;

    @FindBy(css = ".changeNonRating.action") WebElement startNonRatingMTAButton;

    public BOAccountDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    public void convertProspect()
    {
        jsClick(convertProspectButton);
    }

    public YourQuotesPage viewFirstEnquiry()
    {
        waitForElementVisible(10, enquiriesTable);
        new DynamicElementHandler<Void>()
        {
            public Void handleDynamicElement()
            {
                WebElement button = enquirySummaryList.get(0).findElement(By.tagName("a"));
                waitForElementVisible(10, button);
                jsClick(button);
                return null;
            }
        }.execute();
        return PageFactory.initElements(driver, YourQuotesPage.class);
    }

    public EnquiryQuestionSetPage clickNewEnquiry()
    {
        waitForElementVisible(10, newEnquiry);
        jsClick(newEnquiry);
        waitForElementPresent(8, "a#enquiryTab.selected");
        return PageFactory.initElements(driver, EnquiryQuestionSetPage.class);
    }

    public PolicyDetailsPage clickViewPolicy(String policyNumber)
    {
        pause(2000);
        for (int i = 0; i < getNumberOfPolicies(); i++)
        {
            waitForElementVisible(5, policySummaryList.get(i));
            String currentPolicyNumber = getPolicySummary(i).getPolicyNumber();
            if (currentPolicyNumber.equals(policyNumber))
            {
                return clickViewPolicy(i);
            }
        }
        return null;
    }

    public PolicyDetailsPage clickViewPolicy(int index)
    {
        WebElement viewPolicyLink = policySummaryList.get(index).findElement(By.cssSelector("td > a"));
        jsClick(viewPolicyLink);
        //viewPolicyLink.click();
        return PageFactory.initElements(driver, PolicyDetailsPage.class);
    }

    public int getNumberOfPolicies()
    {
        return policyRows.size();
    }

    public PolicySummary getPolicySummary(int index)
    {
        PolicySummary policySummary = new PolicySummary(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(policySummaryList.get(index)), policySummary);
        return policySummary;
    }

    public void waitForRenewalStop()
    {
        webDriverWaitWithPolling(200, 8, renewalStopSectionIsDisplayed());
    }

    Predicate<WebDriver> renewalStopSectionIsDisplayed()
    {
        return new Predicate<WebDriver>()
        {
            @Override 
            public boolean apply(WebDriver driver)
            {
                clickAccountDetailsTabButton();
                try
                {
                    RenewalStopSection renewalStopSection = PageFactory.initElements(driver, RenewalStopSection.class);
                    renewalStopSection.waitForRenewalStopDisplayed();
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        };
    }

    public void clickAccountDetailsTabButton()
    {
        jsClick(accountDetailsTabButton);
    }

    public String getTemporaryPass()
    {
        clickAccountDetailsTabButton();
        jsClick(resetPasswordlink);

        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(200, driver.findElement(By.className("reset-password")));
                WebElement resetPasswordBox = driver.findElement(By.className("reset-password"));
                waitForElementVisible(200, resetPasswordBox.findElement(By.cssSelector(".set-password-link.generate-password")));
                WebElement generateTempPswd =  resetPasswordBox.findElement(By.cssSelector(".set-password-link.generate-password"));
                TimeHelper.waitInSeconds(2);
                jsClick(generateTempPswd);
                return null;
            }
        }.execute();
        TimeHelper.waitInSeconds(2);
        String temp_pass =  generatedPasswordText.getText();
        jsClick(driver.findElement(By.cssSelector("#popup4")).findElement(By.className("action")));
        return temp_pass;
    }

    public void suspendAccount()
    {
        jsClick(editAccountButton);
        waitForElementPresent(5, "button#btnSuspendAccount");
        jsClick(suspendAccountButton);
    }

    public void startMta()
    {
        jsClick(startMtaButton);
    }

    public void startNonRatingMta()
    {
        jsClick(startNonRatingMTAButton);
    }
}


