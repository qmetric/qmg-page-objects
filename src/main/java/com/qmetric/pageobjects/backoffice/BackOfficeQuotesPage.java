package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.qmetric.domain.Quote;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 10/11/2014
 */
public class BackOfficeQuotesPage extends BasePageObject
{

    @FindBy(id = "quoteslist")
    WebElement quoteContainer;

    @FindBy(className = "price-guarantee")
    WebElement priceGuaranteeSection;

    @FindBy(id = "zeroed")
    WebElement freeProductContainer;

    @FindBy(id = "whatis")
    WebElement whatIsCovered;

    @FindBy(css = "ul.quote.pricing-table.selected")
    WebElement quoteElement;

    @FindBy(css = "div[class='quote-boltons-list cf']")
    WebElement extrasContainer;

    @FindBy(css = "section[class='quote-customization-wrapper cf']")
    WebElement customisableQuestionsWrapper;

    @FindBy(css = "div.quote-details.selected.cf")
    WebElement details;

    @FindBy(className = "whats-covered")
    WebElement whatsCoveredContainer = findElement(By.className("whats-covered"));

    public BackOfficeQuotesPage(WebDriver driver)
    {
        super(driver);
    }

    public String getCID()
    {
        String whatIsCoveredText = whatIsCovered.getText();
        return whatIsCoveredText.split(" ")[1];
    }

    public void clickOnShowFreeProductsButton()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                final WebElement freeProductButton = freeProductContainer.findElement(By.cssSelector("button"));
                jsClick(freeProductButton);
                return null;
            }
        }.execute();
    }

    public void selectRandomQuote()
    {
        List<Quote> quotes = getQuotes();
        List<Quote> nonDeclinedQuotes = quotes.stream().filter(q ->
                                                                !q.getPrice().equals("Quote declined")
                                                                && !q.getPrice().equals("Error"))
                                                                .collect(Collectors.toList());
        if(nonDeclinedQuotes.isEmpty())
        {
            throw new RuntimeException("All quotes declined, unable to select a random quote!");
        }
        int randomIndex = new Random().nextInt(nonDeclinedQuotes.size());
        addQuotes(nonDeclinedQuotes.get(randomIndex));
        addToBasket();
    }

    public String getLogoFileName(String productName)
    {
        String logoUrl = getLogoImageUrl(productName);
        String[] segments = logoUrl.split("/");
        if(segments.length > 0)
        {
            return segments[segments.length - 1];
        }
        return logoUrl;
    }


    public String getLogoImageUrl(final String productName)
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                waitForElementVisible(60, quoteContainer);
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return quoteContainer.isDisplayed();
                    }
                });
                List<WebElement> quoteElements = quoteContainer.findElements(By.className("pricing-table"));
                for (WebElement quoteElement : quoteElements)
                {
                    WebElement titleElement = quoteElement.findElement(By.className("title"));
                    if(titleElement.getText().equals(productName))
                    {
                        WebElement imageElement =quoteElement.findElement(By.cssSelector("img"));
                        return imageElement.getAttribute("src");
                    }
                }
                return "";
            }
        }.execute();
    }

    public List<Quote> getQuotes(final int expectedSize)
    {
        final List<Quote> actualQuotes = new ArrayList<Quote>();
        new DynamicElementHandler()
        {
            @Override
            public Void handleDynamicElement()
            {
                final WebElement quoteContainer = getQuoteContainer(expectedSize);
                actualQuotes.clear();
                getActualQuotes(quoteContainer, actualQuotes);
                return null;
            }
            @Override
            public boolean terminatingCondition()
            {
                return actualQuotes.size() == expectedSize;
            }
        }.execute();
        return actualQuotes;
    }

    public void addExtra(final String mainProduct, final String productType) throws Exception
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                List<WebElement> extrasGroups = extrasContainer.findElements(By.className("quote-boltons-group"));
                for (WebElement extrasGroup : extrasGroups)
                {
                    WebElement header = extrasGroup.findElement(By.className("quote-boltons-group-header"));
                    if (header.getText().equals(mainProduct))
                    {
                        List<WebElement> extrasItems = extrasGroup.findElements(By.cssSelector("div[class='bolton-item cf l-span12']"));
                        for (WebElement extrasItem : extrasItems)
                        {
                            WebElement itemHeader = extrasItem.findElement(By.cssSelector("header"));
                            if (itemHeader.getText().equals(productType))
                            {
                                WebElement selectButton = extrasItem.findElement(By.cssSelector("a[data-action=add-to-basket]"));
                                jsClick(selectButton);
                                break;
                            }
                        }
                        break;
                    }
                }
                return null;
            }
        }.execute();
    }

    public void answerCustomisableQuestions(final String question, final String answer) throws Exception
    {
        WebElement customisableQuestionsContainer = customisableQuestionsWrapper.findElement(By.className("custom-questions"));
        List<WebElement> questionElements = customisableQuestionsContainer.findElements(By.cssSelector("div.l-span6"));
        for (WebElement questionElement : questionElements)
        {
            if (questionElement.findElement(By.cssSelector("label")).getText().equals(question))
            {
                if (doesWebElementExist(questionElement, By.cssSelector("select")))
                {
                    try
                    {
                        selectDropDownValueByVisibleText(questionElement.findElement(By.cssSelector("select")), answer);
                        TimeHelper.waitInSeconds(5);
                        waitForCloakToDisappear();
                    }
                    catch (Exception e)
                    {
                        LOG.warn("Failed to select a drop down value for customisable question");
                    }
                }
                else
                {
                    for (WebElement button : questionElement.findElements(By.cssSelector("button")))
                    {
                        if (button.getText().equals(answer))
                        {
                            jsClick(button);
                            TimeHelper.waitInSeconds(5);
                            waitForCloakToDisappear();
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    private WebElement getQuoteContainer(final int expectedSize)
    {
        waitForElementVisible(60, quoteContainer);
        webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return quoteContainer.findElements(By.className("pricing-table")).size() >= expectedSize;
            }
        });
        return quoteContainer;
    }

    private void getActualQuotes(WebElement quoteContainer, List<Quote> actualQuotes)
    {
        List<WebElement> quoteElements = quoteContainer.findElements(By.className("pricing-table"));
        for (WebElement quoteElement : quoteElements)
        {
            WebElement titleElement = quoteElement.findElement(By.className("title"));
            WebElement priceElement = quoteElement.findElement(By.className("price-amount"));
            Quote quote = new Quote();
            quote.setProductName(titleElement.getText());
            quote.setPrice(priceElement.getText());
            actualQuotes.add(quote);
        }
    }

    public List<Quote> getPriceGuaranteeQuotesToCompare()
    {
        priceGuaranteeTableVisibility();
        final List<Quote> actualPGQuotes = new ArrayList<Quote>();
        WebElement priceGuaranteeTable = priceGuaranteeSection.findElement(By.cssSelector("table.grid"));
        return new DynamicElementHandler<List<Quote>>()
        {
            @Override
            public List<Quote> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(priceGuaranteeSection, By.cssSelector("table.grid")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {
                        if (priceGuaranteeTable.isDisplayed())
                        {
                            return true;
                        }
                        return false;
                    }
                });
                List<Map<String, WebElement>> pgTable = new HtmlTable(priceGuaranteeTable).getTableBodyColumnWebElementValues();
                int lastIndex = pgTable.size();
                for(String productName : pgTable.get(lastIndex-1).keySet())
                {
                    if(productName.equals("Days left")||productName.equals(""))
                    {
                        continue;
                    }
                    else
                    {
                        Quote quote = new Quote();
                        quote.setProductName(productName);
                        if (pgTable.get(lastIndex - 1).get(productName).getText().equals(" "))
                        {
                            quote.setPrice("Quote declined");
                        }
                        else
                        {
                            quote.setPrice(pgTable.get(lastIndex - 1).get(productName).getText());
                        }
                        actualPGQuotes.add(quote);
                    }
                }
                return actualPGQuotes;
            }
        }.execute();
    }

    public List<Map<String, String>> getPriceGuaranteeQuotes()
    {
        priceGuaranteeTableVisibility();
        WebElement priceGuaranteeTable = priceGuaranteeSection.findElement(By.cssSelector("table.grid"));
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(priceGuaranteeSection, By.cssSelector("table.grid")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {

                        if (priceGuaranteeTable.isDisplayed())
                        {
                            return true;
                        }
                        return false;
                    }
                });
                return new HtmlTable(priceGuaranteeTable).getTableBodyColumnTextValues();
            }
        }.execute();
    }

    public List<Map<String, WebElement>> getPriceGuaranteeQuotesTable()
    {
        priceGuaranteeTableVisibility();
        WebElement priceGuaranteeTable = priceGuaranteeSection.findElement(By.cssSelector("table.grid"));
        return new DynamicElementHandler<List<Map<String,WebElement>>>()
        {
            @Override
            public List<Map<String, WebElement>> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(priceGuaranteeSection, By.cssSelector("table.grid")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {

                        if (priceGuaranteeTable.isDisplayed())
                        {
                            return true;
                        }
                        return false;
                    }
                });
                return new HtmlTable(priceGuaranteeTable).getTableBodyColumnWebElementValues();
            }
        }.execute();
    }

    public List<Quote> getQuotes()
    {
        final List<Quote> actualQuotes = new ArrayList<Quote>();
        return new DynamicElementHandler<List<Quote>>()
        {
            @Override
            public List<Quote> handleDynamicElement()
            {
                waitForElementVisible(60, quoteContainer);
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return quoteContainer.isDisplayed();
                    }
                });
                List<WebElement> quoteElements = quoteContainer.findElements(By.className("pricing-table"));
                for (WebElement quoteElement : quoteElements)
                {
                    WebElement titleElement = quoteElement.findElement(By.className("title"));
                    WebElement priceElement = quoteElement.findElement(By.className("price-amount"));
                    Quote quote = new Quote();
                    quote.setProductName(titleElement.getText());
                    quote.setPrice(priceElement.getText());
                    actualQuotes.add(quote);
                }
                return actualQuotes;
            }
        }.execute();
    }

    public void addQuotes(final Quote quote)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(20, findElement(quoteContainer, By.className("pricing-table")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {
                        List<WebElement> quoteElements = quoteContainer.findElements(By.className("pricing-table"));
                        for (WebElement quoteElement : quoteElements)
                        {
                            String title = quoteElement.findElement(By.className("title")).getText();
                            if (title.equals(quote.getProductName()))
                            {
                                WebElement selectButton = quoteElement.findElement(By.className("cta-button"));
                                WebElement selectButtonLink = selectButton.findElement(By.cssSelector("a"));
                                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("cta-button")));
                                jsClick(selectButtonLink);
                                if (quote.isProductDataAvailable())
                                {
                                    try
                                    {
                                        addToBasket();
                                    }
                                    catch(Exception e)
                                    {
                                        return true;
                                    }
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
                return null;
            }
        }.execute();
    }

    public String storeMonthlyPrice()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                WebElement monthlyPrice = quoteElement.findElement(By.className("price-monthly"));
                String monthlyText = monthlyPrice.getText();
                monthlyText = monthlyText.replaceAll("\\s","");
                return monthlyText.split(":")[1];
            }
        }.execute();
    }

    public void addToBasket()
    {
        waitForElementVisible(30, details);
        waitForElementVisible(5, findElement(By.cssSelector("span[data-action=\"to-the-basket\"]")));
        WebElement addToBasketButton = details.findElement(By.cssSelector("span[data-action=\"to-the-basket\"]"));
        jsClick(addToBasketButton);
    }

    public void selectQuote(final Quote quote)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(20, findElement(quoteContainer, By.className("pricing-table")));
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {
                        List<WebElement> quoteElements = quoteContainer.findElements(By.className("pricing-table"));
                        for (WebElement quoteElement : quoteElements)
                        {
                            String title = quoteElement.findElement(By.className("title")).getText();
                            if (title.equals(quote.getProductName()))
                            {
                                WebElement selectButton = quoteElement.findElement(By.className("cta-button"));
                                WebElement selectButtonLink = selectButton.findElement(By.cssSelector("a"));
                                new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.className("cta-button")));
                                jsClick(selectButtonLink);
                                return true;
                            }
                        }
                        return false;
                    }
                });
                return null;
            }
        }.execute();
    }

    public void addMTAQuote()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(20, findElement(quoteContainer, By.className("pricing-table")));
                WebElement details = findElement(By.cssSelector("div.quote-details.selected.cf"));
                waitForElementVisible(60, details);
                new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-action=\"to-the-basket\"]")));
                WebElement addToBasketButton = details.findElement(By.cssSelector("span[data-action=\"to-the-basket\"]"));
                jsClick(addToBasketButton);
                return null;
            }
        }.execute();
    }

    public void waitForCloakToDisappear()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                By cloak = By.id("cloack");
                new WebDriverWait(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(cloak));
                return null;
            }
        }.execute();
    }

    public void amendQuoteDetails()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, whatsCoveredContainer);
                WebElement amendQuoteButton = whatsCoveredContainer.findElement(By.cssSelector("div[class='btn btn-rounded btn-green quote-list-amend ']"));
                jsClick(amendQuoteButton);
                return null;
            }
        }.execute();
    }

    public List<Map<String, String>> getQuoteExcesses()
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                waitForElementVisible(20, findElement(By.className("qdc-excesses-table")));
                WebElement excessesTable = findElement(By.className("qdc-excesses-table"));
                return new HtmlTable(excessesTable).getTableBodyColumnTextTrimmedValues();
            }
        }.execute();
    }

    public void clickOnShowHidePriceGuarantee()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(20, findElement(priceGuaranteeSection, By.cssSelector("a[data-action=hide-show-price-guarantee]")));
                WebElement showHidePriceGuaranteeLink = priceGuaranteeSection.findElement(By.cssSelector("a[data-action=hide-show-price-guarantee]"));
                TimeHelper.waitInSeconds(1);
                showHidePriceGuaranteeLink.click();
                return null;
            }
        }.execute();
    }

    public boolean priceGuaranteeTableVisibility()
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                return priceGuaranteeSection.isDisplayed();
            }
        }.execute();
    }

    public void loadPriceGuaranteeEnquiry(final int rowIndex)
    {

        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement priceGuaranteeTable = priceGuaranteeSection.findElement(By.cssSelector("table.grid"));
                HtmlTable priceGuaranteeHtmlTable = new HtmlTable(priceGuaranteeTable);
                List<WebElement> tableRows = priceGuaranteeHtmlTable.getTableBodyRows(0);
                scrollTo(tableRows.get(rowIndex).findElement(By.cssSelector("button")));
                WebElement loadEnquiryButton = tableRows.get(rowIndex).findElement(By.cssSelector("button"));
                jsClickWithoutWait(loadEnquiryButton);
                return null;
            }
        }.execute();
    }

    public boolean isQuoteCurrentInsurer(String currentInsurer)
    {
        waitForElementVisible(60, quoteContainer);
        webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return quoteContainer.isDisplayed();
            }
        });
        List<WebElement> quoteElements = quoteContainer.findElements(By.className("quote-wrapper"));
        for (WebElement quoteElement : quoteElements)
        {
            if(quoteElement.findElement(By.cssSelector("ul > li.title")).getText().contains(currentInsurer))
            {
                if (quoteElement.findElement(By.cssSelector("header > h3")).getText().equalsIgnoreCase("Current insurer"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String getRenewalLabelInPGTable(final String insurer)
    {
        return getPriceGuaranteeQuotesTable().get(0).get(insurer).findElement(By.className("auto-renewal-quote")).getText();
    }

    public int numberOfCustomisableQuestions() throws Exception
    {
        return getCustomisableQuestionsContainer().size();
    }

    public boolean customisableQuestionIdExists(final String questionId) throws Exception
    {
        for(WebElement question : getCustomisableQuestionsContainer())
        {
            if(question.getText().contains(questionId))
            {
                return true;
            }
        }
        return false;
    }

    private List<WebElement> getCustomisableQuestionsContainer() throws Exception
    {
        WebElement customisableQuestionsContainer = customisableQuestionsWrapper.findElement(By.className("custom-questions"));
        List<WebElement> questionElements = customisableQuestionsContainer.findElements(By.cssSelector("div.l-span6"));
        return questionElements;
    }

    public  List<Map<String, String>> getExtras()
    {
        List<Map<String, String>> boExtras = new ArrayList<Map<String, String>>();
        return new DynamicElementHandler< List<Map<String, String>>>()
        {
            @Override
            public  List<Map<String, String>> handleDynamicElement()
            {
                List<WebElement> extrasGroups = extrasContainer.findElements(By.className("quote-boltons-group"));
                for (WebElement extrasGroup : extrasGroups)
                {
                    WebElement header = extrasGroup.findElement(By.className("quote-boltons-group-header"));
                    List<WebElement> extrasItems = extrasGroup.findElements(By.cssSelector("div[class='bolton-item cf l-span12']"));
                    for (WebElement extrasItem : extrasItems)
                    {
                        Map<String, String> extrasMap = new LinkedHashMap<String, String>();
                        WebElement itemHeader = extrasItem.findElement(By.cssSelector("header"));
                        List<WebElement> prices = extrasItem.findElements(By.className("bolton-item-price"));
                        extrasMap.put("extras-group", header.getText());
                        extrasMap.put("extra", itemHeader.getText());
                        for (WebElement extrasPrice : prices)
                        {
                            if(extrasPrice.getText().contains("annual"))
                            {
                                extrasMap.put("annual price", extrasPrice.getText());
                            }
                            else
                            {
                                extrasMap.put("monthly price", extrasPrice.getText());
                            }
                        }
                        boExtras.add(extrasMap);
                    }
                }
                return boExtras;
            }
        }.execute();
    }

    public boolean isPriceSame(String product, String renewalPrice)
    {
        waitForElementVisible(60, quoteContainer);
        webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return quoteContainer.isDisplayed();
            }
        });
        List<WebElement> quoteElements = quoteContainer.findElements(By.className("quote-wrapper"));
        for (WebElement quoteElement : quoteElements)
        {
            if(quoteElement.findElement(By.cssSelector("ul > li.title")).getText().contains(product))
            {
                if (quoteElement.findElement(By.className("price-amount")).getText().replace("£ ", "").trim().equalsIgnoreCase(renewalPrice.replace("£ ", "").replace("£","")))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<String> getPrice(final String productName)
    {
        String productPrice = "";
        waitForElementVisible(60, quoteContainer);
        webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return quoteContainer.isDisplayed();
            }
        });
        List<WebElement> quoteElements = quoteContainer.findElements(By.className("quote-wrapper"));
        for (WebElement quoteElement : quoteElements) {
            if (quoteElement.findElement(By.cssSelector("ul > li.title")).getText().contains(productName)) {
                return Optional.of(quoteElement.findElement(By.className("price-amount")).getText().replace("£ ", "").trim());
            }
        }
        return Optional.absent();
    }

    public Optional<String> getEOWExcessValue()
    {
        for(Map excesses : getQuoteExcesses())
        {
            if(excesses.get("").equals("Escape of Water Excess"))
            {
                if(excesses.get("Contents")!=null)
                {
                    if (excesses.get("Contents").equals(excesses.get("Buildings"))) {
                        return Optional.of((String) excesses.get("Buildings"));
                    }
                }
                else
                {
                    return Optional.of((String) excesses.get("Buildings"));
                }
            }
        }
        return Optional.absent();
    }

    public Optional<String>  getContentsSubsidenceExcessValue()
    {
        for(Map excesses : getQuoteExcesses())
        {
            if(excesses.get("").equals("Subsidence Excess"))
            {
                return Optional.of((String) excesses.get("Contents"));
            }
        }
        return Optional.absent();
    }

    public Optional<String> getBuildingsSubsidenceExcessValue()
    {
        for(Map excesses : getQuoteExcesses())
        {
            if(excesses.get("").equals("Subsidence Excess"))
            {
                return Optional.of((String) excesses.get("Buildings"));
            }
        }
        return Optional.absent();
    }

    public Optional<String> getBuildingsADExcessValue()
    {
        for(Map excesses : getQuoteExcesses())
        {
            if(excesses.get("").equals("Accidental Damage Excess"))
            {
                return Optional.of((String) excesses.get("Buildings"));
            }
        }
        return Optional.absent();
    }

    public boolean isPanelDecline()
    {
        boolean panelDecline = false;
        List<Quote> quotes = getQuotes();
        for(int i = 0 ; i < quotes.size() ; i++)
        {
            if(quotes.get(i).getPrice().equalsIgnoreCase("Quote declined"))
            {
                panelDecline = true;
            }
            else
            {
                panelDecline = false;
                return panelDecline;
            }
        }
        return panelDecline;
    }
}
