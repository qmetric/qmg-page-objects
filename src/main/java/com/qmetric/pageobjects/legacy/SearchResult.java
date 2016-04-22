package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/04/2013
 */
public class SearchResult extends BasePageObject
{

    @FindBy(css = "td") List<WebElement> searchResultElements;

    public SearchResult(WebDriver driver)
    {
        super(driver);
    }

    public String getCustomerId()
    {
        return searchResultElements.get(0).getText();
    }

    public String getName()
    {
        return searchResultElements.get(1).getText();
    }

    public String getRegisteredEmail()
    {
        return searchResultElements.get(2).getText();
    }

    public String getProspectEmail()
    {
        return searchResultElements.get(3).getText();
    }

    public String getPolicyNumbers()
    {
        return searchResultElements.get(4).getText();
    }

    public String getPrimaryPhone()
    {
        return searchResultElements.get(5).getText();
    }

    public String getSecondaryPhone()
    {
        return searchResultElements.get(6).getText();
    }

    public void clickOnSearchResult()
    {
        waitForElementVisible(20, searchResultElements.get(0));
        jsClick(searchResultElements.get(0));
    }
}
