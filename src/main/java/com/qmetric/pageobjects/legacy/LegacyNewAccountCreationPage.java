package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.shared.SharedData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

/**
 * Created by pgnanasekaran on 15/09/2015.
 */
public class LegacyNewAccountCreationPage extends BasePageObject
{

    @FindBy( id = "registeredEmail")
    WebElement emailId;

    @FindBy( id = "password")
    WebElement password;

    @FindBy( id = "firstName")
    WebElement fName;

    @FindBy( id = "lastName")
    WebElement lName;

    @FindBy( id = "title")
    WebElement title;

    @FindBy( id = "primaryPhoneNumber")
    WebElement pPhNo;

    @FindBy( id = "secondaryPhoneNumber")
    WebElement sPhNo;

    @FindBy( id = "marketingEnabled")
    WebElement agreementChkBx;

    @FindBy( className = "enquirySubmit.primary")
    WebElement nextPageBtn;


    public LegacyNewAccountCreationPage(WebDriver driver)
    {
        super(driver);
    }

    public void createAccount() throws Exception
    {
        enterEmailId();
        enterPassword();
        selectTitle();
        enterFirstName();
        enterLastName();
        enterPhoneNumber();
        enterSecondaryPhoneNumber();
        selectAgreementCheckBox();
        clickContinue();
    }

    private void enterPassword()
    {
        password.sendKeys("P@55w0r9");
        SharedData.tempPass = "P@55w0r9";
    }

    private void enterEmailId()
    {
        String emailID = "qa-test-user+" + (int)(new Random().nextDouble() * 1000000000) + "@com.qmetric.co.uk";
        emailId.sendKeys(emailID);
        SharedData.email = emailID;
    }

    private void selectTitle() throws Exception
    {
        selectDropDownValueByVisibleText(title, "Mr");
    }

    private void enterFirstName()
    {
        fName.sendKeys("jerry");
    }

    private void enterLastName()
    {
        lName.sendKeys("tom");
        SharedData.policyHolderName = "Mr Jerry Tom";
    }

    private void enterPhoneNumber()
    {
        pPhNo.sendKeys("1234567890");
    }
    private void enterSecondaryPhoneNumber()
    {
        sPhNo.sendKeys("1234567890");
    }

    private void selectAgreementCheckBox()
    {
        jsClick(agreementChkBx);
    }

    private void clickContinue() throws Exception
    {
        getVisibleElementFromElementsList(By.className("continue ")).click();
    }

}
