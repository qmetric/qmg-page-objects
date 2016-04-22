package com.qmetric.pageobjects.legacy;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 26/04/2013
 */
public class SearchPage extends BasePageObject
{

    @FindBy(css = "table#customerSearchResults > tbody > tr") List<WebElement> searchResultsList;

    public SearchPage(WebDriver driver)
    {
        super(driver);
    }

    public int getNumberOfSearchResults()
    {
        int size = searchResultsList.size();
        if (size == 1)
        {
            if (searchResultsList.get(0).getText().contains("Sorry, no accounts found for"))
            {
                return 0;
            }
        }
        return size;
    }

    public SearchResult getSearchResult(int index)
    {
        SearchResult searchResult = new SearchResult(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(searchResultsList.get(index)), searchResult);
        return searchResult;
    }
}
