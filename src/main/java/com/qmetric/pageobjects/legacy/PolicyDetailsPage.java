package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 09/05/2013
 */

public class PolicyDetailsPage extends BasePageObject
{

    @FindBy(css = "a#cancelButton")
    private WebElement cancelPolicyButton;

    @FindBy(css = "h1.error")
    private WebElement unableToRenewDueToRenewalStopMessageElement;

    @FindBy(css = "button.black[title*='MTA']")
    private WebElement mtaButton;

    @FindBy(css = "button.black[title*='renewal']")
    private WebElement renewalButton;

    @FindBy(css = "a.pdf-link[href*='schedule']")
    private WebElement scheduleLink;

    @FindBy(css = "a.pdf-link[href*='wording']")
    private WebElement wordingLink;

    @FindBy(css = "a.pdf-link[href*='welcome']")
    private WebElement welcomeLink;

    @FindBy(css = "a.pdf-link[href*='yourquestions']")
    private WebElement qAndALink;

    @FindBy(css = "a.pdf-link[href*='keyfacts']")
    private WebElement keyFactsLink;

    @FindBy(id = "manualLoanSetupButton")
    private WebElement manualLoanSetupButton;

    @FindBy(id = "convertToAnnualButton")
    private WebElement convertToAnnualButton;

    @FindBy(css = "#policyAnswers > dt:nth-child(54)")
    private WebElement totalPaidAnnual;

    @FindBy(css = "#policyAnswers > dt:nth-child(62)")
    private WebElement totalPaidAnnualRenewal;

    @FindBy(css = "#policyAnswers > dt:nth-child(89)")
    private WebElement totalPaidMonthly;

    @FindBy(css = "#policyAnswers > dt:nth-child(88)")
    private WebElement totalPaidMonthlyRenewal;

    @FindBy(css = "p.renewalIntro > strong")
    private WebElement renewalPrice;

    @FindBy(id = "policyHolder.firstName")
    private WebElement MTAFirstName;

    public PolicyDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    public PolicyCancellationPage clickCancellationButton()
    {
        jsClick(cancelPolicyButton);
        waitForElementPresent(10, "form#policyCancellationBean");
        waitForElementVisible(10, "form#policyCancellationBean");
        return PageFactory.initElements(driver, PolicyCancellationPage.class);
    }

    public boolean isUnableToRenewMessageDisplayed()
    {
        try
        {
            return unableToRenewDueToRenewalStopMessageElement.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickRenewalButton()
    {
        jsClick(renewalButton);
    }

    public boolean isMTAButtonDisplayed()
    {
        try
        {
            return mtaButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void clickMTAButton()
    {
        jsClick(mtaButton);
    }

    public boolean isScheduleGenerated()
    {
        try
        {
            return scheduleLink.getText().equals("download");
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Schedule not generated");
            return false;
        }
    }

    public boolean isWordingGenerated()
    {
        try
        {
            return wordingLink.getText().equals("download");
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Wording not generated");
            return false;
        }
    }

    public boolean isWelcomeGenerated()
    {
        try
        {
            return welcomeLink.getText().equals("download");
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Welcome letter not generated");
            return false;
        }
    }

    public boolean isQAndAGenerated()
    {
        try
        {
            return qAndALink.getText().equals("download");
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Q & A not generated");
            return false;
        }
    }

    public boolean isKeyFactsGenerated()
    {
        try
        {
            return keyFactsLink.getText().equals("download");
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Key facts not generated");
            return false;
        }
    }

    public boolean isManualLoanSetupButtonVisible()
    {
        try
        {
            return manualLoanSetupButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Direct debit is setup");
            return false;
        }
    }

    public boolean isConvertToAnnualButtonVisible()
    {
        try
        {
            return convertToAnnualButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            LOG.info("Direct debit is setup");
            return false;
        }
    }

    public String getTotalPaidAnnual()
    {
        return totalPaidAnnual.getText();
    }

    public String getTotalPaidAnnualRenewal()
    {
        return totalPaidAnnualRenewal.getText();
    }

    public String getTotalPaidMonthly()
    {
        return totalPaidMonthly.getText();
    }

    public WebElement getRenewalButton()
    {
        return renewalButton;
    }

    public String getRenewalPrice()
    {
        return renewalPrice.getText().trim();
    }

    public void clickSubmitNonRatingChange()
    {
        jsClick(driver.findElement(By.id("policyUpdateBean")).findElement(By.cssSelector("button.action")));
    }

    public void modifyNonRatingAnswerFirstName(final String value)
    {
        MTAFirstName.sendKeys(value);
    }
}
