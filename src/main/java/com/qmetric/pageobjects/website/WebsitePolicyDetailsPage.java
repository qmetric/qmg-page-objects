package com.qmetric.pageobjects.website;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.utilities.URLCheck;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

public class WebsitePolicyDetailsPage extends BasePageObject
{
    @FindBy(css = "button[data-payment-type=CREDIT_CARD]")
    WebElement payAnnualButton;

    @FindBy(css = "button[data-payment-type=DIRECT_DEBIT]")
    WebElement payMonthlyButton;

    //TODO: Create a new agg policy summary page stuff
    //    @FindBy(id = "v2PolicySummaryFormButton")
    @FindBy(css = "button[data-id=confirm-and-buy]")
    WebElement confirmAndBuyButton;

    @FindBy(css = "li[class='monthly l-span12-m l-span6-t']")
    WebElement monthlyPaymentContainer;

    @FindBy(css = "article[data-id=customs-and-extras]")
    WebElement customisePolicyContainer;

    @FindBy(css = "article[data-id=customs-start-date]")
    WebElement policyStartDateContainer;

    @FindBy(css = "article[data-id=customs-voluntary-excess]")
    WebElement voluntaryExcessContainer;

    @FindBy(css = "div[data-id=excesses-section]")
    WebElement excessesContainer;

    @FindBy(className = "excesses-toggles")
    WebElement excessToggleContainer;

    @FindBy(className = "whatscovered-quote")
    WebElement whatsCoveredTable;

    @FindBy(className = "back-quotes")
    WebElement backToQuotes;

    @FindBy(className = "documents")
    WebElement documentsTable;

    @FindBy(css = ".insurer-logo > img")
    WebElement insurerLogo;

    private WebsiteRadioButtonAnswersSection websiteRadioButtonAnswersSection;

    private WebsiteSelectAnswerSection websiteSelectAnswerSection;

    private WebsiteDateAnswerSection websiteDateAnswerSection;

    public WebsitePolicyDetailsPage(final WebDriver driver)
    {
        super(driver);
        websiteRadioButtonAnswersSection = new WebsiteRadioButtonAnswersSection(driver);
        websiteSelectAnswerSection = new WebsiteSelectAnswerSection(driver);
        websiteDateAnswerSection = new WebsiteDateAnswerSection(driver);
    }

    public void waitForPageToLoad()
    {
        waitForElementVisible(10, findElement(By.className("policypage")));
    }

    public boolean customisePolicySectionExists()
    {
        return doesWebElementExist(By.cssSelector("article[data-id=customs-and-extras]"));
    }

    public String payMonthlyPriceElementText()
    {
        WebElement priceElement = findElement(monthlyPaymentContainer, By.className("font-price"));
        return priceElement.getText();
    }

    public void clickPayAnnualButton()
    {
        jsClick(payAnnualButton);
    }

    public void clickPayMonthlyButton()
    {
        jsClick(payMonthlyButton);
    }

    public void clickConfirmAndBuyButton()
    {
        jsClick(confirmAndBuyButton);
    }

    public Map<String, Boolean> getDocumentsLinkState() throws Exception
    {
        Map<String, Boolean> documentsLinkState = Maps.newHashMap();
        List<WebElement> links = documentsTable.findElements(By.cssSelector("a"));
        for (WebElement link : links)
        {
            String docLink = link.getAttribute("href");
            int responseCode = URLCheck.getResponseCodeForDocLink(docLink);
            documentsLinkState.put(docLink, responseCode != 404);
        }
        return documentsLinkState;
    }

    public List<Map<String, String>> getWhatsCovered()
    {
        HtmlTable whatsCovered = new HtmlTable(whatsCoveredTable);
        return whatsCovered.getTableBodyColumnTextValues();
    }

    public void modifyPolicyStartDate(final String day)
    {
        jsClick(policyStartDateContainer.findElement(By.className("set" + day)));
    }

    public void selectPolicyStartDate(final String date) throws Exception
    {
        websiteDateAnswerSection.selectDateFromCalendarPicker(policyStartDateContainer, date.split(" "));
    }

    public void customiseExtras(final Map<String, String> extra)
    {
        WebElement extrasContainer = customisePolicyContainer.findElement(By.cssSelector("section[data-id=extras]"));
        List<WebElement> extrasRows = extrasContainer.findElements(By.cssSelector("div[class='extra policy-extra l-span12-m']"));
        for (WebElement extrasRow : extrasRows)
        {
            String extrasDescription = extrasRow.findElement(By.cssSelector("span[class*='l-span12-m l-span4-ts']")).getText();
            if (extra.get("Extra").equals(extrasDescription))
            {
                String type = extra.get("Type");
                String value = extra.get("Value");
                if (type.equals("Drop down"))
                {
                    websiteSelectAnswerSection.selectExtraForCustomisable(extrasRow, value);
                }
                else if (type.equals("Yes / No"))
                {
                    websiteRadioButtonAnswersSection.selectYesNoAnswerForCustomisable(extrasRow, value);
                }
                break;
            }
        }
    }

    public List<Map<String, String>> getExcessesTable()
    {
        HtmlTable excessesTable = new HtmlTable(excessesContainer.findElement(By.className("excesses")));
        return excessesTable.getTableBodyColumnTextTrimmedValues();
    }

    public void waitForExcessesTableToDisappear()
    {
        webDriverWaitWithPolling(30, 10, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver webDriver)
            {
                return !excessesContainer.findElement(By.className("excesses")).isDisplayed();
            }
        });
    }

    private WebElement getExcessesTableElement()
    {
        return excessesContainer.findElement(By.className("excesses"));
    }

    public boolean getExcessesTableIsDisplayed()
    {
        return getExcessesTableElement().isDisplayed();
    }

    public void showExcesses()
    {
        jsClick(excessToggleContainer.findElement(By.className("excesses-slider-open")));
    }

    public void hideExcesses()
    {
        jsClick(excessToggleContainer.findElement(By.className("excesses-slider-close")));
    }

    public void modifyExcess(final String excess)
    {
        websiteSelectAnswerSection.selectAnswer(voluntaryExcessContainer, excess);
    }

    public List<Map<String, String>> getBreakdownTable() throws Exception
    {
        WebElement priceBreakdownTable = getVisibleElementFromElementsList(By.className("price-break"));
        HtmlTable breakdownTable = new HtmlTable(priceBreakdownTable);
        return breakdownTable.getTableBodyColumnTextValues();
    }

    public String getLogo()
    {
        return getLogoText(insurerLogo).get();
    }

    private Optional<String> getLogoText(final WebElement quoteTitleElement)
    {
        String[] segments = quoteTitleElement.getAttribute("src").split("/");
        if (segments.length > 0) {
            return Optional.of(segments[segments.length - 1]);
        }
        return Optional.absent();
    }

    public ExpectedCondition<WebElement> isPolicyStartDateModifiable()
    {
        return ExpectedConditions.elementToBeClickable(policyStartDateContainer);
    }

    public void clickBackToQuotes()
    {
        jsClick(backToQuotes);
    }
}
