package com.qmetric.pageobjects.website;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.domain.Quote;
import com.qmetric.utilities.URLCheck;
import com.qmetric.domain.WebsiteQuote;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteQuotesPage extends BasePageObject
{
    private SpinnerElement spinnerElement;

    private WebsiteRadioButtonAnswersSection websiteRadioButtonAnswersSection;

    private WebsiteSelectAnswerSection websiteSelectAnswerSection;

    public WebsiteQuotesPage(WebDriver driver)
    {
        super(driver);
        spinnerElement = new SpinnerElement(driver);
        websiteRadioButtonAnswersSection = new WebsiteRadioButtonAnswersSection(driver);
        websiteSelectAnswerSection = new WebsiteSelectAnswerSection(driver);
    }

    private List<WebElement> getQuotes()
    {
        WebElement quotesContainer = findElement(By.className("quotes-content"));
        return findElements(quotesContainer, By.className("quote"));
    }

    public List<String> getMonthlyPriceList()
    {
        List<String> monthlyPriceList = Lists.newArrayList();
        for (WebElement quote : getQuotes())
        {
            WebElement monthlyElement = quote.findElement(By.className("monthly"));
            monthlyPriceList.add(monthlyElement.getText());
        }
        return monthlyPriceList;
    }

    public boolean quotesExist()
    {
        WebElement quotesContainer = findElement(By.className("quotes-content"));
        return doesWebElementExist(quotesContainer, By.className("quote"));
    }

    public void clickOnAmendButton()
    {
        jsClick(findElement(By.cssSelector("button[class='button gray pull-left amend-details']")));
    }

    private WebElement getCustomisableQuestionsContainer() throws Exception
    {
        return new DynamicElementHandler<WebElement>()
        {
            @Override
            public WebElement handleDynamicElement()
            {
                try
                {
                    return getVisibleElementFromElementsList(By.className("custom-content"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void answerCustomisableQuestion(String answer, String questionId) throws Exception
    {
        WebElement questionElement = getCustomisableQuestionsContainer().findElement(By.cssSelector("section[data-question-id='" + questionId + "']"));
        if (isYesNo(answer))
        {
            websiteRadioButtonAnswersSection.selectYesNoAnswerForCustomisable(questionElement, answer);
        }
        else
        {
            websiteSelectAnswerSection.selectAnswerForCustomisable(questionElement, answer);
        }
        spinnerElement.waitForSpinnerToFinish();
    }

    public int numberOfCustomisableQuestions() throws Exception
    {
        return getCustomisableQuestionsContainer().findElements(By.cssSelector("section")).size();
    }

    public boolean customisableQuestionIdExists(String questionId) throws Exception
    {
        return doesWebElementExist(getCustomisableQuestionsContainer(), By.cssSelector("section[data-question-id='" + questionId + "']"));
    }

    private boolean isYesNo(final String answer)
    {
        return answer.equals("Yes") || answer.equals("No");
    }

    public boolean customisePolicySectionNotDisplayed()
    {
        String customisePolicyContent = findElement(By.cssSelector("section[class='extras l-span4 hide-t show-d pad-W']")).getText().trim();
        return StringUtils.isEmpty(customisePolicyContent);
    }

    public List<WebElement> getExtras()
    {
        List<WebElement> extras = new ArrayList<>();
        try
        {
            webDriverWaitWithPolling(60, 2, new Predicate<WebDriver>()
            {
                @Override
                public boolean apply(WebDriver driver)
                {
                    return doesWebElementExist(By.className("extras-content"));
                }
            });
            WebElement extrasContent = getVisibleElementFromElementsList(By.className("extras-content"));
            scrollTo(extrasContent);
            extras = findElements(extrasContent, By.cssSelector("section[data-question-id=extra_single]"));
        }
        catch (Exception e)
        {
            System.out.println("An exception has occured while trying to get extras content container " + e.getMessage());
        }
        return extras;
    }

    public void waitForPageToLoad()
    {
        webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return doesWebElementExist(By.className("quotespage"));
            }
        });
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("quotespage")));
    }

    public String selectRandomQuote()
    {
        List<WebElement> quotes = getQuotes();
        int randomIndex = new Random().nextInt(quotes.size());
        final WebElement quote = quotes.get(randomIndex);
        WebElement selectQuoteButton = quote.findElement(By.cssSelector("button"));
        final WebElement monthlyPriceElement = quote.findElement(By.className("monthly"));
        String randomQuoteMonthlyPrice = monthlyPriceElement.getText().split(" ")[1];
        selectQuoteButton.click();
        return randomQuoteMonthlyPrice;
    }

    public void selectExtras(final List<Map<String, String>> extras)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                List<WebElement> extrasElement = getExtras();
                for (Map<String, String> extrasMap : extras)
                {
                    for (WebElement extraElement : extrasElement)
                    {
                        if (extrasMap.get("Extra").equals(extraElement.findElement(By.className("header")).getText().trim()))
                        {
                            String type = extrasMap.get("Type");
                            String value = extrasMap.get("Value");
                            if (type.equals("Drop down"))
                            {
                                websiteSelectAnswerSection.selectExtraForCustomisable(extraElement, value);
                            }
                            else if (type.equals("Yes / No"))
                            {
                                websiteRadioButtonAnswersSection.selectYesNoAnswerForCustomisable(extraElement, value);
                            }
                            spinnerElement.waitForSpinnerToFinish();
                            break;
                        }
                    }
                }
                return null;
            }
        }.execute();
    }

    public String selectHomeQuote(final String quoteId) throws Exception {
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            WebElement insurerColumn = quote.findElement(By.cssSelector(".insurer > img"));
            if (getQuoteId(insurerColumn).get().equals(quoteId))
            {
                return innerSelectQuote(quote);
            }
        }
        throw new Exception("Could not find quote with name " + quoteId);
    }

    public String selectQuote(final String quoteName) throws Exception
    {
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            if (quote.getText().contains(quoteName))
            {
                return innerSelectQuote(quote);
            }
        }
        throw new Exception("Could not find quote with name " + quoteName);
    }

    private String innerSelectQuote(WebElement quote)
    {
        final WebElement monthlyPriceElement = quote.findElement(By.className("monthly"));
        String monthlyPrice = monthlyPriceElement.getText().split(" ")[1];
        WebElement selectQuoteButton = quote.findElement(By.cssSelector("button"));
        selectQuoteButton.click();
        return monthlyPrice;
    }

    public List<Map<String, String>> getHomeQuoteBenefits(final String quoteName)
    {
        List<Map<String, String>> benefits = Lists.newArrayList();
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            WebElement insurerColumn = quote.findElement(By.cssSelector("li[class='insurer l-span3-t l-span12-m']"));
            List<WebElement> columnDetails = insurerColumn.findElements(By.cssSelector("span"));
            WebElement homeInsuranceElement = columnDetails.get(0);
            if (homeInsuranceElement.getAttribute("class").endsWith(quoteName))
            {
                WebElement benefitsColumn = quote.findElement(By.cssSelector("li[class='benefits l-span3-t l-span12-m']"));
                List<WebElement> benefitLists = benefitsColumn.findElements(By.cssSelector("li[class='l-span6-m l-span12-t']"));
                for (WebElement list : benefitLists)
                {
                    Map<String, String> benefitsMap = Maps.newHashMap();
                    benefitsMap.put("Description", list.findElement(By.className("text1")).getText());
                    benefitsMap.put("Value", list.findElement(By.className("value")).getText());
                    benefits.add(benefitsMap);
                }
                break;
            }
        }
        return benefits;
    }

    public Map<String, Boolean> getPolicyDetailDocumentLinkState() throws Exception
    {
        Map<String, Boolean> policyDetailDocState = Maps.newHashMap();
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            WebElement insurerColumn = quote.findElement(By.cssSelector("li[class='insurer l-span3-t l-span12-m']"));
            WebElement policyDetailElement = insurerColumn.findElement(By.className("policy-details"));
            WebElement policyDocLink = policyDetailElement.findElement(By.cssSelector("a"));
            String docLink = policyDocLink.getAttribute("href");
            int responseCode = URLCheck.getResponseCodeForDocLink(docLink);
            policyDetailDocState.put(docLink, responseCode != 404);
        }
        return policyDetailDocState;
    }

    public Map<String, String> getExtrasValue() throws Exception
    {

        Map<String, String> extraValues = new HashMap<>();
        for (WebElement extrasElement : getExtras())
        {
            String actualExtrasName = extrasElement.findElement(By.className("header")).getText().trim();
            if (actualExtrasName.contains("Home Emergency"))
            {
                extraValues.put(actualExtrasName, getHomeEmergencyValue(extrasElement));
            }
            else if (actualExtrasName.contains("Legal Expenses & ID Theft"))
            {
                extraValues.put(actualExtrasName, getHomeLegalAndTheftValue(extrasElement));
            }
            else if (actualExtrasName.contains("Key Cover"))
            {
                extraValues.put(actualExtrasName, getKeyCoverValue(extrasElement));
            }
        }
        return extraValues;
    }

    public String getHomeEmergencyValue(WebElement extrasElement) throws Exception
    {
        scrollTo(extrasElement);
        return getTextFromYesNoElement(extrasElement);
    }

    public String getHomeLegalAndTheftValue(WebElement extrasElement) throws Exception
    {
        scrollTo(extrasElement);
        return getTextFromYesNoElement(extrasElement);
    }

    public String getKeyCoverValue(WebElement extrasElement) throws Exception
    {
        scrollTo(extrasElement);
        return extrasElement.findElement(By.cssSelector(".dk_toggle.dk_label")).getText().trim();
    }

    private String getTextFromYesNoElement(final WebElement extrasElement)
    {
        if (extrasElement.findElement(By.cssSelector(".add-extra")).getAttribute("class").trim().endsWith("selected"))
        {
            return extrasElement.findElement(By.cssSelector(".add-extra.selected")).getText().trim();
        }
        else
        {
            return extrasElement.findElement(By.cssSelector(".remove-extra.selected")).getText().trim();
        }
    }

    public String storeEnquiryId() throws Exception
    {
        String[] urlValues = driver.getCurrentUrl().split("/");
        return urlValues[urlValues.length - 2];
    }

    public List<Quote> getQuotesToCompare()
    {
        WebElement quotesContainer = findElement(By.className("quotes-content"));
        final List<Quote> actualQuotes = new ArrayList<Quote>();
        return new DynamicElementHandler<List<Quote>>()
        {
            @Override
            public List<Quote> handleDynamicElement()
            {
                waitForElementVisible(60, quotesContainer);
                webDriverWaitWithPolling(30, 2, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(WebDriver webDriver)
                    {
                        return quotesContainer.isDisplayed();
                    }
                });
                List<WebElement> quoteTitleElements = quotesContainer.findElements(By.cssSelector(".insurer > img"));
                List<WebElement> quotePriceElements = quotesContainer.findElements(By.cssSelector(".premium > ul > li:nth-child(1)"));
                int index = 0;
                for (WebElement quoteTitleElement : quoteTitleElements)
                {
                    String quotePrice = quotePriceElements.get(index).getText();
                    Quote quote = new Quote();
                    quote.setProductName(getQuoteId(quoteTitleElement).get());
                    quote.setPrice(quotePrice);
                    actualQuotes.add(quote);
                    index++;
                }
                return actualQuotes;
            }
        }.execute();
    }

    public void waitForQuotesToLoad()
    {
        webDriverWaitWithPolling(120, 2, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return doesWebElementExist(By.className("quotes")) && findElements(By.className("quotes")).size() > 0;
            }
        });
    }

    public String getNoQuotesContent()
    {
        return findElement(By.className("no-quotes")).getText();
    }

    public Optional<String> getheader(String quoteId)
    {
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            WebElement insurerColumn = quote.findElement(By.cssSelector(".insurer > img"));
            if(getQuoteId(insurerColumn).get().equals(quoteId))
            {
                return Optional.of(quote.findElement(By.cssSelector("header")).getText());
            }
        }
        return Optional.absent();
    }

    public String getProductWithLeastPrice()
    {
        final List<WebsiteQuote> actualQuotes = new ArrayList<>();
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            List<WebElement> quoteTitleElements = quote.findElements(By.cssSelector(".insurer > img"));
            List<WebElement> quotePriceElements = quote.findElements(By.cssSelector(".premium > ul > li:nth-child(1)"));
            int index = 0;
            for (WebElement quoteTitleElement : quoteTitleElements)
            {
                String quotePrice = quotePriceElements.get(index).getText();
                WebsiteQuote insurerQuote = new WebsiteQuote();
                insurerQuote.setProductName(getQuoteId(quoteTitleElement).get());
                insurerQuote.setPrice(quotePrice);
                actualQuotes.add(insurerQuote);
            }
        }
        Collections.sort(actualQuotes);
        return actualQuotes.get(0).getProductName();
    }

    public String getRetrieve2yrFixedOfferBtnText()
    {
        hasRetrieve2yrFixedOfferBtn();
        WebElement retrieve2yrFxdOfferBtn = findElement(By.cssSelector(".button.btn-green"));
        return retrieve2yrFxdOfferBtn.getText();
    }

    public void clickRetrieve2yrFixedBtn()
    {
        WebElement retrieve2yrFxdOfferBtn = findElement(By.cssSelector(".button.btn-green"));
        jsClick(retrieve2yrFxdOfferBtn);
    }

    public boolean hasRetrieve2yrFixedOfferBtn()
    {
        return doesWebElementExist(By.cssSelector(".button.btn-green"));
    }

    public Optional<String> getLogo(final String productId)
    {
        List<WebElement> quotes = getQuotes();
        for (WebElement quote : quotes)
        {
            List<WebElement> quoteTitleElements = quote.findElements(By.cssSelector(".insurer > img"));
            for (WebElement quoteTitleElement : quoteTitleElements)
            {
                if(getQuoteId(quoteTitleElement).get().contains(productId))
                {
                    return Optional.of(getLogoText(quoteTitleElement).get());
                }
            }
        }
        return Optional.absent();
    }

    private Optional<String> getLogoText(final WebElement quoteTitleElement)
    {
        String[] segments = quoteTitleElement.getAttribute("src").split("/");
        if (segments.length > 0) {
            return Optional.of(segments[segments.length - 1]);
        }
        return Optional.absent();
    }


    public Optional<String> getQuoteId(WebElement quoteTitleElement)
    {
        if (quoteTitleElement.getAttribute("alt").contains("2 year fixed")) {
            String[] segments = quoteTitleElement.getAttribute("src").split("/");
            if (segments.length > 0) {
                return Optional.of(segments[segments.length - 1].split("_")[1]+"a");
            }
        }
        else
        {
            return Optional.of(quoteTitleElement.getAttribute("alt").replace(" logo", "")+"a");
        }
        return Optional.absent();
    }
}
