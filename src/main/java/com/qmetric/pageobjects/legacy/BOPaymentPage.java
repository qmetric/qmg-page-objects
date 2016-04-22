package com.qmetric.pageobjects.legacy;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 03/05/2013
 */
public class BOPaymentPage extends BasePageObject
{

    @FindBy(css = "input#nbx_cardholder_name") WebElement cardHolderName;

    @FindBy(css = "input#nbx_houseno") WebElement houseNumber;

    @FindBy(css = "input#nbx_postcode") WebElement postCode;

    @FindBy(css = "input#cardNo") WebElement cardNumber;

    @FindBy(css = "select#expiryMonth") WebElement expiryMonthDropDown;

    @FindBy(css = "select#expiryYear") WebElement expiryYearDropDown;

    @FindBy(css = "button#payNowButton") WebElement payNowButton;

    @FindBy(css = "input#cvv") WebElement cardVerificationValueInput;

    //Direct debit elements
    @FindBy(css = "input.validate-required-check.inline.checkbox") List<WebElement> directDebitAgreementCheckBoxList;

    @FindBy(css = "input#acctHolderName") WebElement accountHolderNameInputBox;

    @FindBy(css = "input#sortCode1") WebElement sortCodeFirstInputBox;

    @FindBy(css = "input#sortCode2") WebElement sortCodeSecondInputBox;

    @FindBy(css = "input#sortCode3") WebElement sortCodeThirdInputBox;

    @FindBy(css = "input#accountNumber") WebElement accountNumberInputBox;

    @FindBy(css = "div.mtaReturnPremium") WebElement mtaReturnPremiumLabel;

    @FindBy(css = ".paymentSelectorInner span.price") WebElement annualPremium;

    @FindBy(id = "confirmTsandCs") WebElement tsAndCs;

    public BOPaymentPage(WebDriver driver)
    {
        super(driver);
    }

    public void enterCardHolderName(String cardHolderNameText)
    {
        enterTextInput(cardHolderName, cardHolderNameText);
    }

    public void enterHouseNumber(String houseNumberText)
    {
        enterTextInput(houseNumber, houseNumberText);
    }

    public void enterPostCode(String postCodeText)
    {
        enterTextInput(postCode, postCodeText);
    }

    public void enterCardNumber(String cardNumberText)
    {
        enterTextInput(cardNumber, cardNumberText);
    }

    public void selectExpiryMonth(String expiryMonthText) throws Exception
    {
        selectDropDownValueByVisibleText(expiryMonthDropDown, expiryMonthText);
    }

    public void selectExpiryYear(String expiryYearText) throws Exception
    {
        selectDropDownValueByVisibleText(expiryYearDropDown, expiryYearText);
    }

    public void clickPayNowButton()
    {
        jsClick(payNowButton);
    }

    public void enterCardVerificationValue(String cvv)
    {
        enterTextInput(cardVerificationValueInput, cvv);
    }

    void acceptAllDirectDebitAgreements()
    {
        for (WebElement element : directDebitAgreementCheckBoxList)
        {
            jsClick(element);
        }
    }

    public void acceptTsAndCs()
    {
        jsClick(tsAndCs);
    }

    void enterAccountHolderName(String accountHolderName)
    {
        enterTextInput(accountHolderNameInputBox, accountHolderName);
    }

    void enterAccountNumber(String accountNumber)
    {
        enterTextInput(accountNumberInputBox, accountNumber);
    }

    void enterSortCode(String sortCodeFirst, String sortCodeSecond, String sortCodeThird)
    {
        enterTextInput(sortCodeFirstInputBox, sortCodeFirst);
        enterTextInput(sortCodeSecondInputBox, sortCodeSecond);
        enterTextInput(sortCodeThirdInputBox, sortCodeThird);
    }

    public void enterDefaultTestDetails() throws Exception
    {
        waitForPresenceOfCardNumberInputBox();
        enterCardNumber("4670620060330996");
        selectExpiryMonth("07");
        selectExpiryYear("2016");
        enterCardVerificationValue("111");
    }

    public void enterDefaultCloseDirectDebitDetails()
    {
        enterAccountHolderName("Any Name");
        String accountNumber = generateAccountNumber();
        enterAccountNumber(accountNumber);
        LOG.info("Using account number " + accountNumber);
        enterSortCode("20", "00", "00");
        LOG.info("Using sort code 20-00-00");
        acceptAllDirectDebitAgreements();
    }

    public void enterDefaultPCLDirectDebitDetails()
    {
        enterAccountHolderName("Any Name");
        String accountNumber = "02149187";
        enterAccountNumber(accountNumber);
        LOG.info("Using account number " + accountNumber);
        enterSortCode("93", "86", "11");
        LOG.info("Using sort code 93-86-11");
        acceptAllDirectDebitAgreements();
    }

    public void enterFixedDirectDebitDetails()
    {
        enterAccountHolderName("Fixed DD");
        enterAccountNumber("77554477");
        enterSortCode("20", "00", "00");
        LOG.info("Using fixed details, account number  sort code 20-00-00");
        acceptAllDirectDebitAgreements();
    }

    public void waitForPresenceOfCardNumberInputBox()
    {
        new WebDriverWait(driver, 60).pollingEvery(2, TimeUnit.SECONDS).until(new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return cardNumber.isDisplayed();
            }
        });
    }

    public boolean isMtaReturnPremiumLabelDisplayed()
    {
        try
        {
            return mtaReturnPremiumLabel.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public String getAnnualPremium()
    {
        return annualPremium.getText();
    }

    String generateAccountNumber()
    {
        ArrayList<String> numbers = new ArrayList<String>();
        numbers.add("44");
        numbers.add("55");
        numbers.add("66");
        numbers.add("77");
        numbers.add("88");
        numbers.add("99");

        Random rnd = new Random();
        String accountNumber = "44";

        for (int count = 0; count < 2; count++)
        {
            int next = rnd.nextInt(numbers.size());
            accountNumber += numbers.get(next);
            numbers.remove(next);
        }

        return accountNumber += "44";
    }
}
