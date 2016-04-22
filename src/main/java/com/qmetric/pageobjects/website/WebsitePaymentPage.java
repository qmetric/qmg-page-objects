package com.qmetric.pageobjects.website;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class WebsitePaymentPage extends BasePageObject
{
    @FindBy(id = "cardNo")
    WebElement legacyCardNumberTextBox;

    @FindBy(id = "cvv")
    WebElement cvvTextBox;

    @FindBy(id = "payment-button")
    WebElement paymentButton;

    @FindBy(id = "acctHolderName")
    WebElement accountHolderNameTextBox;

    @FindBy(id = "accountNumber")
    WebElement accountNumberTextBox;

    @FindBy(id = "sortCode1")
    WebElement sortCode1TextBox;

    @FindBy(id = "sortCode2")
    WebElement sortCode2TextBox;

    @FindBy(id = "sortCode3")
    WebElement sortCode3TextBox;

    @FindBy(css = "a[class='button element-checkbox clickable']")
    List<WebElement> checkBoxes;

    @FindBy(css = "#confirmTsandCs")
    WebElement checkboxTandC;

    @FindBy(id = "expiryMonth")
    WebElement expiryMonth;

    @FindBy(id = "expiryYear")
    WebElement expiryYear;

    @FindBy(id = "payNowButton")
    WebElement payNowButton;

    @FindBy(css = "input[type='checkbox']")
    List<WebElement> ddCheckboxes;

    @FindBy(css = "span[class='cell-value apr']")
    WebElement aprTextValue;


    public WebsitePaymentPage(final WebDriver driver)
    {
        super(driver);
    }

    public void enterCardNumber(String cardNumber)
    {
        WebElement cardDetailsForm = driver.findElement(By.id("cardDetails"));
        waitForElementVisible(60, cardDetailsForm.findElement(By.id("nbx_card_number")));
        enterTextInput(cardDetailsForm.findElement(By.id("nbx_card_number")), cardNumber);
    }

    public void enterLegacyCardNumber(String cardNumber)
    {
        enterTextInput(legacyCardNumberTextBox, cardNumber);
    }

    public void enterCVV(String cvv)
    {
        enterTextInput(cvvTextBox, cvv);
    }

    public void selectExpiryDates()
    {
        selectDropDownValue("expiryMonth", 7);
        selectDropDownValue("expiryYear", 3);
    }

    public void selectCheckBoxes()
    {
        for (WebElement checkBox : checkBoxes)
        {
            checkBox.click();
        }
    }

    public void clickOnPaymentButton()
    {
        paymentButton.click();
    }

    public void makeAnnualPayment(String cardNumber, String cvv)
    {
        enterCreditCardDetails(cardNumber, cvv);
        selectCheckBoxes();
        clickOnPaymentButton();
    }

    public void makeDirectDebitPayment(Map<String, String> paymentDetails)
    {
        enterCreditCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("CVV"));
        enterDirectDebitDetails(paymentDetails);
        selectCheckBoxes();
        clickOnPaymentButton();
    }

    public void enterDirectDebitDetails(final Map<String, String> paymentDetails)
    {
        enterTextInput(accountHolderNameTextBox, paymentDetails.get("Account Holder"));
        enterSortCode(paymentDetails.get("Sort Code"));
        enterTextInput(accountNumberTextBox, paymentDetails.get("Account Number"));
    }

    private void enterSortCode(String sortCode)
    {
        String[] codes = sortCode.split("-");
        enterTextInput(sortCode1TextBox, codes[0]);
        enterTextInput(sortCode2TextBox, codes[1]);
        enterTextInput(sortCode3TextBox, codes[2]);
    }

    private void enterCreditCardDetails(final String cardNumber, final String cvv)
    {
        enterCardNumber(cardNumber);
        selectExpiryDates();
        enterCVV(cvv);
    }

    private void selectDropDownValue(String id, int index)
    {
        WebElement dropDownContainerElement = findElement(By.id("dk-" + id));
        dropDownContainerElement.click();
        WebElement optionsContainer = dropDownContainerElement.findElement(By.className("dk-select-options"));
        List<WebElement> optionsElement = optionsContainer.findElements(By.cssSelector("li"));
        optionsElement.get(index).click();
    }

    public void waitForCardNumberBoxToBeVisible()
    {
        webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return doesWebElementExist(By.id("nbx_card_number"));
            }
        });
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("nbx_card_number")));
    }

     public void setExpiryDate(String month, String year)
     {
         new Select(expiryMonth).selectByVisibleText(month);
         new Select(expiryYear).selectByVisibleText(year);
     }

     public void checkTermsAndConditions()
     {
         new DynamicElementHandler<Void>()
         {
             @Override
             protected Void handleDynamicElement()
             {
                 waitForElementVisible(30, legacyCardNumberTextBox);
                 checkboxTandC.click();
                 return null;
             }
         }.execute();
     }

     public void clickPayNowButton()
     {
         payNowButton.click();
     }

     public void clickSuccessfulPayment()
     {
         String mainWindowHandle = driver.getWindowHandle();
         driver.switchTo().frame("nbx3dSecureFrame");
         waitForElementPresent(8, By.id("TestButtons"));
         driver.findElement(By.id("TestButtons")).findElements(By.tagName("input")).get(0).click();
         driver.switchTo().window(mainWindowHandle);
     }

     public String getPolicyNumber()
     {
         waitForElementPresent(20, By.id("policySummary"));
         return driver.findElements(By.tagName("dd")).get(2).getText();
     }

    public String getCustomerId()
    {
        waitForElementPresent(20, By.id("policySummary"));
        return driver.findElements(By.tagName("dd")).get(1).getText();
    }

    public String getGrossPremium()
    {
        waitForElementPresent(20, By.id("policySummary"));
        return driver.findElements(By.tagName("dd")).get(4).getText();
    }

     public void selectCheckBoxesForDDPayment()
     {
         for (WebElement checkBox : ddCheckboxes) {
             checkBox.click();
         }
     }

     public String getAPRStatement()
     {
         return aprTextValue.getText();
     }

     public void waitForPageToLoad()
     {
         webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
         {
             @Override
             public boolean apply(WebDriver driver)
             {
                 return doesWebElementExist(By.id("cardDetails"));
             }
         });
         new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("cardDetails")));
     }

    public void clickViewPolicyBtn()
    {
        waitForElementPresent(20, By.className("continue"));
        driver.findElement(By.className("continue")).click();
    }
}
