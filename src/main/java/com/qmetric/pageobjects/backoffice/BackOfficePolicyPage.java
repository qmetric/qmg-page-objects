package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.pageobjects.website.SpinnerElement;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.FileDownloader;
import com.qmetric.utilities.Retry;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class BackOfficePolicyPage extends BasePageObject
{
    @FindBy(id = "policy-informations")
    WebElement policyInformationSection;

    @FindBy(css = "#policy-informations nav.navigation")
    WebElement navigationTabElement;

    @FindBy(css = "#policy-history nav.navigation")
    WebElement navigationHistoryTabElement;

    @FindBy(css = "div[data-id=policy-documents]")
    WebElement documentsTab;

    @FindBy(css = "button[data-id=reprint]")
    WebElement postDocumentLink;

    @FindBy(id = "new-business-transaction")
    WebElement newBusinessDocsRadioButton;

    @FindBy(id = "mta-transaction")
    WebElement mtaDocsRadioButton;

    @FindBy(id = "cancellation-transaction")
    WebElement cancellationDocsRadioButton;

    WebElement systemMsgBox;

    WebElement msgBoxCloseBtn;

    public final BackOfficeAuthorisedPersonTab backOfficeAuthorisedPersonTab;

    public final BackOfficeCustomerInfoTab backOfficeCustomerInfoTab;

    public final BackOfficePolicyInfoTab backOfficePolicyInfoTab;

    public final BackOfficePolicyHolderInfoTab backOfficePolicyHolderInfoTab;

    public final BackOfficeAuditNotesTab backOfficeAuditNotesTab;

    public BackOfficePolicyPage(WebDriver driver)
    {
        super(driver);
        backOfficeAuthorisedPersonTab = new BackOfficeAuthorisedPersonTab(driver);
        backOfficeCustomerInfoTab = new BackOfficeCustomerInfoTab(driver);
        backOfficePolicyHolderInfoTab = new BackOfficePolicyHolderInfoTab(driver);
        backOfficeAuditNotesTab = new BackOfficeAuditNotesTab(driver);
        backOfficePolicyInfoTab = new BackOfficePolicyInfoTab(driver);
    }

    public void waitForPolicyDetailsToBeDisplayed()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                final WebElement policyDetailSection = findElement(By.className("policy-details"));
                webDriverWaitWithPolling(15, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return policyDetailSection.isDisplayed();
                    }
                });
                return null;
            }
        }.execute();
    }

    public boolean isPolicyActionAvailable(String action) throws Exception
    {
        final WebElement policyActionsSection = findElement(By.id("policy-actions"));
        webDriverWaitWithPolling(15, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return policyActionsSection.isDisplayed();
            }
        });

        List<WebElement> options = policyActionsSection.findElements(By.cssSelector("select > option"));
        for (WebElement option : options)
        {
            if (option.getText().equals(action))
            {
                return true;
            }
        }
        return false;
    }

    public void selectPolicyAction(String action) throws Exception
    {
        final WebElement policyActionsSection = findElement(By.id("policy-actions"));
        webDriverWaitWithPolling(15, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return policyActionsSection.isDisplayed();
            }
        });
        selectDropDownValueByVisibleText(policyActionsSection.findElement(By.cssSelector("select")), action);
        WebElement actionButton = policyActionsSection.findElement(By.cssSelector("button"));
        jsClick(actionButton);
    }

    public void selectMTAType(String amendmentType) throws Exception
    {
        final WebElement modalElement = findElement(By.id("modal-anchor-content"));
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return modalElement.isDisplayed();
            }
        });
        selectDropDownValueByVisibleText(modalElement.findElement(By.cssSelector("select")), amendmentType);
        WebElement amendButton = modalElement.findElement(By.cssSelector("a[data-action=amend-policy]"));
        jsClick(amendButton);
    }

    public List<String> getDocumentNames()
    {
        return new DynamicElementHandler<List<String>>()
        {
            @Override
            public List<String> handleDynamicElement()
            {
                waitForElementVisible(30, documentsTab.findElement(By.cssSelector("a")));
                List<WebElement> docLinks = documentsTab.findElements(By.cssSelector("a"));
                List<String> actualDocNames = new LinkedList<>();

                for (WebElement docLink : docLinks)
                {
                    actualDocNames.add(docLink.getText().replace("nb_", ""));
                }
                return actualDocNames;
            }
        }.execute();
    }

    public List<String> getDocumentNamesWithPolling(int expectedDocSize)
    {
        new Retry<BackOfficePolicyPage>().setLoopSleep(5000).setMaxRetry(10).waitWhile(this, policyPage ->
        {
            refresh();
            SpinnerElement spinnerElement = new SpinnerElement(driver);
            spinnerElement.waitForSpinnerToFinish();
            return expectedDocSize != getDocumentNames().size();
        });
        return getDocumentNames();
    }

    public void clickOnReferralTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='referral-info']")));
        WebElement referralTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='referral-info']"));
        jsClick(referralTabLink);
    }

    public void clickOnPolicyHolderInfoTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='policyholder-info']")));
        WebElement policyHolderInfoTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='policyholder-info']"));
        jsClick(policyHolderInfoTabLink);
    }

    public void clickOnAuditNotesTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='audit-notes']")));
        WebElement policyHolderInfoTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='audit-notes']"));
        jsClick(policyHolderInfoTabLink);
    }

    public void clickOnAuthorisedPersonsTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='authorised-persons']")));
        WebElement authorisedPersonsTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='authorised-persons']"));
        jsClick(authorisedPersonsTabLink);
    }

    public void clickOnCustomerInformationTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='customer-info']")));
        WebElement referralTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='customer-info']"));
        jsClick(referralTabLink);
    }

    public List<Map<String, String>> getTable()
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                WebElement table = policyInformationSection.findElement(By.cssSelector("table"));
                HtmlTable htmlTable = new HtmlTable(table);
                return htmlTable.getTableBodyColumnTextValues();
            }
        }.execute();
    }

    public List<String> downloadDocuments(final List<String> documentsList) throws Exception
    {
        List<String> fileLocations = new LinkedList<String>();
        FileDownloader fileDownloader = new FileDownloader();
        int index = 0;
        int max_index = documentsList.size();
        List<WebElement> downloadLinks = documentsTab.findElements(By.cssSelector("a"));
        for (WebElement downloadLink : downloadLinks)
        {
            if (downloadLink.getText().contains(documentsList.get(index)))
            {
                fileLocations.add(fileDownloader.downloadFile(downloadLink));
                index++;
                if (index == max_index)
                {
                    break;
                }
                else
                {
                    continue;
                }
            }
        }
        return fileLocations;
    }

    public void clickOnPolicyInformationTab()
    {
        waitForElementVisible(30, navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='policy-info']")));
        WebElement referralTabLink = navigationTabElement.findElement(By.cssSelector("li a[data-triggered-event='policy-info']"));
        jsClick(referralTabLink);
    }

    public boolean isPolicyEnabled(final String action)
    {
        final WebElement policyActionsSection = findElement(By.id("policy-actions"));
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return policyActionsSection.isDisplayed();
            }
        });

        List<WebElement> options = policyActionsSection.findElements(By.cssSelector("select > option"));
        for (WebElement option : options)
        {
            if (option.getText().equals(action) && option.isEnabled())
            {
                return true;
            }
        }
        return false;
    }

    public void postDocuments()
    {
        waitForElementVisible(30, postDocumentLink);
        jsClick(postDocumentLink);
    }

    public void closeSystemMessage()
    {
        TimeHelper.waitInSeconds(10);
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                try
                {
                    systemMsgBox = getVisibleElementFromElementsList(By.className("mBoxContainer "));
                    msgBoxCloseBtn = systemMsgBox.findElement(By.cssSelector("div.mBoxClose"));
                    jsClick(msgBoxCloseBtn);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void selectNewBusinessTab()
    {
        WebElement newBusinessTabLink = navigationHistoryTabElement.findElement(By.cssSelector("li a[data-triggered-event='new-business']"));
        jsClick(newBusinessTabLink);
    }

    public boolean validateDocumentOptionSelection(final String option)
    {
        switch (option)
        {
            case "New Business":
            {
                return ((newBusinessDocsRadioButton.isSelected()) && (!cancellationDocsRadioButton.isEnabled()) && (!mtaDocsRadioButton.isEnabled()));
            }
            case "MTA":
            {
                return ((newBusinessDocsRadioButton.isEnabled()) && (mtaDocsRadioButton.isSelected()) && (!cancellationDocsRadioButton.isEnabled()));
            }
            case "Cancellation":
            {
                return ((!newBusinessDocsRadioButton.isEnabled()) && (!mtaDocsRadioButton.isEnabled()) && (!cancellationDocsRadioButton.isEnabled()));
            }
        }
        return false;
    }

    public String getErrorMessage()
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

    public String getLogoFileName()
    {
        String logoUrl = backOfficePolicyInfoTab.getLogo();
        String[] segments = logoUrl.split("/");
        if(segments.length > 0)
        {
            return segments[segments.length - 1];
        }
        return logoUrl;
    }
}
