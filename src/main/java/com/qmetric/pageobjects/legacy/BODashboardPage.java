package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BODashboardPage extends BasePageObject
{
    @FindBy(id = "createNewUserButton") WebElement createNewAccountButton;

    @FindBy(css = "div#loginDetails > .button.red") WebElement logoutButton;

    @FindBy(css = "input#searchTermInput") WebElement searchInputBox;

    @FindBy(css = "button#doSearchButton") WebElement goSearchButton;

    String createAccountButtonId = "createNewUserButton";

    public BODashboardPage(WebDriver driver)
    {
        super(driver);
    }

    public void createNewAccount()
    {
        jsClick(createNewAccountButton);
        waitForElementPresent(10, "div.baseBoxTitle");
    }

    public boolean isCreateNewAccountButtonDisplayed()
    {
        return createNewAccountButton.isDisplayed();
    }

    public void logout()
    {
        jsClick(logoutButton);
    }

    public SearchPage search(String searchTerm)
    {
        enterTextInput(searchInputBox, searchTerm);
        jsClick(goSearchButton);
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        waitForElementPresent(8, "a#searchTab.selected");
        return searchPage;
    }

    public String getCreateAccountButtonId()
    {
        return createAccountButtonId;
    }
}

