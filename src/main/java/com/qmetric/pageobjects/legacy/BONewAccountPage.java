package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BONewAccountPage extends BasePageObject
{
    @FindBy(id = "createTitle") WebElement titleDropDown;

    @FindBy(id = "createFirstName") WebElement firstNameTextBox;

    @FindBy(id = "createLastName") WebElement lastNameTextBox;

    @FindBy(id = "createEmail") WebElement emailTextBox;

    @FindBy(id = "createPrimaryPhoneNumber") WebElement phoneNumberTextBox;

    @FindBy(id = "createReferrer") WebElement referrerDropDown;

    @FindBy(id = "btnCreateUserAccountSubmit") WebElement createUserButton;

    @FindBy(id = "disableEmail") WebElement noEmailButton;

    @FindBy(id = "condition1") WebElement condition1Radio;

    @FindBy(id = "condition2") WebElement condition2Radio;

    @FindBy(id = "condition3") WebElement condition3Radio;

    @FindBy(id = "condition4") WebElement condition4Radio;

    @FindBy(id = "accept") WebElement acceptConditionsButton;

    public BONewAccountPage(WebDriver driver)
    {
        super(driver);
    }

    public void fillDetailsAndCreate(UserForm userForm) throws Exception
    {
        fillOutDetails(userForm);
        agreeToTerms();
    }

    public void fillDetailsAndCreate(UserForm userForm, String referrerType) throws Exception
    {
        fillOutDetails(userForm, referrerType);
        agreeToTerms();
    }

    void fillOutDetails(UserForm userForm) throws Exception
    {
        selectTitle(userForm.getTitle());
        enterFirstName(userForm.getFirstName());
        enterLastName(userForm.getLastName());
        if (StringUtils.isBlank(userForm.getEmail()))
        {
            clickNoEmailButton();
        }
        else
        {
            enterEmail(userForm.getEmail());
        }
        enterPhoneNumber(userForm.getPhoneNumber());
        selectReferrerByIndex(1);

        clickCreateUserButton();
    }

    void fillOutDetails(UserForm userForm, String referrerType) throws Exception
    {
        selectTitle(userForm.getTitle());
        enterFirstName(userForm.getFirstName());
        enterLastName(userForm.getLastName());
        if (StringUtils.isBlank(userForm.getEmail()))
        {
            clickNoEmailButton();
        }
        else
        {
            enterEmail(userForm.getEmail());
        }
        enterPhoneNumber(userForm.getPhoneNumber());
        selectReferrerByText(referrerType);

        clickCreateUserButton();
    }

    void selectTitle(String title) throws Exception
    {
        selectDropDownValueByVisibleText(titleDropDown, title);
    }

    void enterFirstName(String firstName)
    {
        enterTextInput(firstNameTextBox, firstName);
    }

    void enterLastName(String lastName)
    {
        enterTextInput(lastNameTextBox, lastName);
    }

    void enterEmail(String email)
    {
        enterTextInput(emailTextBox, email);
    }

    void enterPhoneNumber(String phoneNumber)
    {
        enterTextInput(phoneNumberTextBox, phoneNumber);
    }

    void selectReferrerByIndex(int referrerIndex)
    {
        selectDropDownValueByIndex(referrerDropDown, referrerIndex);
    }

    public void selectReferrerByText(String referrer) throws Exception
    {
        selectDropDownValueByVisibleText(referrerDropDown, referrer);
    }

    void clickCreateUserButton()
    {
        jsClick(createUserButton);
    }

    void clickNoEmailButton()
    {
        jsClick(noEmailButton);
    }

    void agreeToTerms()
    {
        jsClick(condition1Radio);
        jsClick(condition2Radio);
        jsClick(condition3Radio);
        jsClick(condition4Radio);
        jsClick(acceptConditionsButton);
    }
}
