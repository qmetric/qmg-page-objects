package com.qmetric.pageobjects.gmail;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 03/03/2014
 */
public class GmailSearchPage extends BasePageObject
{

    private static final String SEARCH_BOX_INPUT_LOCATOR = "input#gbqfq.gbqfif";
    private static final String SEARCH_BUTTON_LOCATOR = "button#gbqfb.gbqfb";
    private static final String EMAIL_INSTANCE_LOCATOR = "[role='main'] div.Cp table tbody tr:not(.TD)";

    @FindBy(css = SEARCH_BOX_INPUT_LOCATOR) WebElement searchBoxInput;

    @FindBy(css = SEARCH_BUTTON_LOCATOR) WebElement searchButton;

    @FindBy(css = EMAIL_INSTANCE_LOCATOR)
    List<WebElement> emailList;

    public GmailSearchPage(WebDriver driver) {
        super(driver);
        waitForElementPresent(10, SEARCH_BOX_INPUT_LOCATOR);
    }

    public void enterSearchTerm(String searchTerm) {
        enterTextInput(searchBoxInput, searchTerm);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void search(String searchTerm) {
        enterSearchTerm(searchTerm);
        clickSearchButton();
    }

    public int getNumberOfEmails() {
        return emailList.size();
    }

    public void waitForEmail(String searchTerm) {
        webDriverWaitWithPolling(15, 3, isAtLeastOneEmailDisplayed(searchTerm));
    }

    private Predicate<WebDriver> isAtLeastOneEmailDisplayed(final String searchTerm) {
        return new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver driver) {
                try {
                    search(searchTerm);
                    Thread.sleep(2000);
                } catch (InterruptedException ignore) {
                }
                return (getNumberOfEmails() > 0);
            }
        };
    }

    public String getEmailContent()
    {
        emailList.get(0).click();
        waitForElementPresent(10,"img[alt='Policy Expert Insurance']");
        return driver.getPageSource();
    }



}
