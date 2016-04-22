package com.qmetric.pageobjects.backoffice;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.Retry;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BackOfficeSearchPage extends BasePageObject
{
    By resolTableLocator = By.cssSelector("section[role=resols-list] table");

    By enquiryTableLocator = By.cssSelector("section[role=enquiries-list] table");

    By policyTableLocator = By.cssSelector("section[role=policies-list] table");

    @FindBy (css = "div[class=tip-text]")
    WebElement toolTip;

    public BackOfficeSearchPage(final WebDriver driver)
    {
        super(driver);
    }

    private List<Map<String, WebElement>> searchResultTable;

    public void search(String query)
    {
        WebElement searchTextBox = driver.findElement(By.id("search-input"));
        clearTextInput(searchTextBox);
        if(query.contains("-"))
        {
            query = query.substring(query.lastIndexOf("-")+1, query.length());
        }
        enterTextInput(searchTextBox, query);
        clickSearchButton();
    }

    public boolean getEmptySearchResult()
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                final By searchResultLocator = By.cssSelector(".label.alert.radius");
                waitForElementPresent(5, searchResultLocator);
                webDriverWaitWithPolling(120, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        WebElement outputElement = findElement(searchResultLocator);
                        return !outputElement.getText().contains("Loading");
                    }
                });
                WebElement resultElement = findElement(searchResultLocator);
                return resultElement.getText().contains("Sorry, no results found.");
            }
        }.execute();
    }

    public List<Map<String, String>> getSearchResults()
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                final By searchTableLocator = By.cssSelector("section.results table");
                if(!doesWebElementExist(searchTableLocator))
                {
                    return Lists.newArrayList();
                }
                webDriverWaitWithPolling(120, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        WebElement outputElement = findElement(searchTableLocator);
                        return !outputElement.getText().contains("Loading");
                    }
                });
                WebElement resultsElement = findElement(searchTableLocator);
                return new HtmlTable(resultsElement).getTableBodyColumnTextValues();
            }
        }.execute();
    }

    public List<Map<String, String>> getSearchResultsWithPolling(final int expectedSearchResultsSize)
    {
        new Retry<BackOfficeSearchPage>().setLoopSleep(10000).setMaxRetry(10).waitWhile(this, searchPage ->
        {
            new DynamicElementHandler<Void>()
            {
                @Override protected Void handleDynamicElement()
                {
                    clickSearchButton();
                    return null;
                }
            }.execute();
            return expectedSearchResultsSize != getSearchResults().size();
        });
        return getSearchResults();
    }

    public List<Map<String, String>> getEnquirySearchResultsWithExpectedMinimumRowSize(final int expectedSearchResultsSize)
    {
        new Retry<BackOfficeSearchPage>().setLoopSleep(10000).setMaxRetry(10).waitWhile(this, searchPage ->
        {
            new DynamicElementHandler<Void>()
            {
                @Override protected Void handleDynamicElement()
                {
                    clickSearchButton();
                    return null;
                }
            }.execute();
            return expectedSearchResultsSize > getEnquirySearchResults().size();
        });
        return getEnquirySearchResults();
    }

    public List<Map<String, String>> getPolicySearchResultsWithExpectedMinimumRowSize(final int expectedSearchResultsSize)
    {
        new Retry<BackOfficeSearchPage>().setLoopSleep(10000).setMaxRetry(10).waitWhile(this, searchPage ->
        {
            new DynamicElementHandler<Void>()
            {
                @Override protected Void handleDynamicElement()
                {
                    clickSearchButton();
                    return null;
                }
            }.execute();
            return expectedSearchResultsSize > getPolicySearchResults().size();
        });
        return getPolicySearchResults();
    }

    public List<Map<String, String>> getPolicySearchResultsWithPolling(final int expectedSearchResultsSize)
    {
        new Retry<BackOfficeSearchPage>().setLoopSleep(10000).setMaxRetry(10).waitWhile(this, searchPage ->
                {
                    new DynamicElementHandler<Void>()
                    {
                        @Override protected Void handleDynamicElement()
                        {
                            clickSearchButton();
                            return null;
                        }
                    }.execute();
                    final List<Map<String, String>> searchResults = getPolicySearchResults();
                    return expectedSearchResultsSize != searchResults.size();
                });
        return getPolicySearchResults();
    }

    public List<Map<String, String>> getPolicySearchResultsWithPolling(final int expectedSearchResultsSize, String columnName, String columnValue)
    {
        new Retry<BackOfficeSearchPage>().setLoopSleep(10000).setMaxRetry(10).waitWhile(this, searchPage ->
        {
            new DynamicElementHandler<Void>()
            {
                @Override protected Void handleDynamicElement()
                {
                    clickSearchButton();
                    return null;
                }
            }.execute();
            final List<Map<String, String>> searchResults = getPolicySearchResults();
            for(Map<String, String> searchResult : searchResults)
            {
                if(!searchResult.get(columnName).equals(columnValue))
                {
                    return true;
                }
            }
            return expectedSearchResultsSize != searchResults.size();
        });
        return getPolicySearchResults();
    }

    public List<Map<String, String>> getResolSearchResults()
    {
        return getTextValues(resolTableLocator);
    }

    public List<Map<String, String>> getPolicySearchResults()
    {
        return getTextValues(policyTableLocator);
    }

    public List<Map<String, String>> getEnquirySearchResults()
    {
        return getTextValues(enquiryTableLocator);
    }

    public List<Map<String, WebElement>> getPolicyWebElementSearchResults()
    {
        return getWebElements(policyTableLocator);
    }

    public List<Map<String, WebElement>> getEnquiryWebElementSearchResults()
    {
        return getWebElements(enquiryTableLocator);
    }

    public List<Map<String, WebElement>> getResolWebElementSearchResults()
    {
        return getWebElements(resolTableLocator);
    }

    public List<Map<String,String>> getPolicyRowsByEmail(String email)
    {
        return getPolicySearchResults().stream().filter(policyRow -> policyRow.get("Email").equalsIgnoreCase(email)).collect(Collectors.toList());
    }

    public void selectPolicyByBusinessLine(String businessLine)
    {
        List<Map<String, WebElement>> customerRows = getPolicyWebElementSearchResults().stream().filter(policyRow -> policyRow.get("Business line").getText().equals(businessLine)).collect(Collectors.toList());
        customerRows.get(0).get("").findElement(By.tagName("a")).click();
    }

    public void selectEnquiryByBusinessLine(final String businessLine)
    {
        List<Map<String, WebElement>> customerRows = getEnquiryWebElementSearchResults().stream().filter(policyRow -> policyRow.get("Business line").getText().equals(businessLine)).collect(Collectors.toList());
        customerRows.get(0).get("").findElement(By.tagName("a")).click();
    }

    public Map<String, WebElement> selectEnquiryByBusinessLineAndReturnRow(final String businessLine)
    {
        return getEnquiryWebElementSearchResults().stream().filter(policyRow -> policyRow.get("Business line").getText().equals(businessLine)).findFirst().get();
    }

    public void clickOnViewPolicyButton()
    {
        searchResultTable = getEnquiryWebElementSearchResults();
        clickOnButton("Actions", "View");
    }

    public void clickOnAmendButton()
    {
        searchResultTable = getEnquiryWebElementSearchResults();
        clickOnButton("", "Amend");
    }

    public void clickOnResolAmendButton()
    {
        searchResultTable = getResolWebElementSearchResults();
        clickOnButton("", "Amend");
    }

    public void clickOnRenewButton()
    {
        searchResultTable = getEnquiryWebElementSearchResults();
        clickOnButton("", "Renew");
    }

    public void clickSearchButton()
    {
        WebElement searchButton = driver.findElement(By.id("search-button"));
        searchButton.click();
    }

    private void clickOnButton(String identifierColumnHeader, String identifierColumnName)
    {
        for (Map<String, WebElement> columnElement : searchResultTable)
        {
            WebElement identifierColumnElement = columnElement.get(identifierColumnHeader);
            if (identifierColumnElement.getText().equals(identifierColumnName))
            {
                columnElement.get(identifierColumnHeader).findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    private List<Map<String, String>> getTextValues(final By elementLocator)
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                if(!doesWebElementExist(elementLocator))
                {
                    return Lists.newArrayList();
                }
                final WebElement table = findElement(elementLocator);
                webDriverWaitWithPolling(120, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return !table.getText().contains("Loading");
                    }
                });
                return new HtmlTable(table).getTableBodyColumnTextValues();
            }
        }.execute();
    }

    private List<Map<String, WebElement>> getWebElements(final By elementLocator)
    {
        return new DynamicElementHandler<List<Map<String, WebElement>>>()
        {
            @Override
            public List<Map<String, WebElement>> handleDynamicElement()
            {
                if(!doesWebElementExist(elementLocator))
                {
                    return Lists.newArrayList();
                }
                final WebElement outputElement = findElement(elementLocator);
                webDriverWaitWithPolling(120, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return !outputElement.getText().contains("Loading");
                    }
                });
                return new HtmlTable(outputElement).getTableBodyColumnWebElementValues();
            }
        }.execute();
    }

    public void mouseOverRow(int i)
    {
        WebElement firstRow = getPolicyWebElementSearchResults().get(i-1).get("");
        Actions action = new Actions(driver);
        action.moveToElement(firstRow).build().perform();
    }

    public String getToolTipValue()
    {
        waitForElementVisible(20, toolTip);
        return toolTip.getText();
    }

    public void checkIfEnquiryPresent(String businessLine)
    {
        List<Map<String, WebElement>> customerRows = getEnquiryWebElementSearchResults().stream().filter(policyRow -> policyRow.get("Business line").getText().equals(businessLine)).collect(Collectors.toList());
        int retry_count = 0;

        while(customerRows.size()<1 && retry_count!=10)
        {
            customerRows = getEnquiryWebElementSearchResults().stream().filter(policyRow -> policyRow.get("Business line").getText().equals(businessLine)).collect(Collectors.toList());
            TimeHelper.waitHalfASecond();
            retry_count++;
        }

    }

    public void searchOperator(final String query)
    {
        WebElement searchTextBox = driver.findElement(By.id("search-input"));
        clearTextInput(searchTextBox);
        enterTextInput(searchTextBox, query);
        clickSearchButton();
    }
}
