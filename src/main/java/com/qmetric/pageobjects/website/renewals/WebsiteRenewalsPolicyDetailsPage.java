package com.qmetric.pageobjects.website.renewals;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.shared.DataPatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class WebsiteRenewalsPolicyDetailsPage extends BasePageObject
{

    @FindBy(className = "policypage") WebElement policyPageContainer;

    @FindBy(css = ".policypage section.motor-section") List<WebElement> detailsSections;

    @FindBy(css = ".insurer-logo > img") WebElement insurerLogo;

    public WebsiteRenewalsPolicyDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    public void isLoaded()
    {
        waitForElementVisible(30, policyPageContainer);
    }

    public List<Map<String, String>> getDetails(final String heading) throws Exception
    {
        for (WebElement detailsSection : detailsSections)
        {
            String actualHeading = detailsSection.findElement(By.tagName("h3")).getText();
            if (heading.equals(actualHeading))
            {
                HtmlTable detailsTable = new HtmlTable(detailsSection.findElement(By.tagName("table")));
                List<Map<String, WebElement>> elementsTable = detailsTable.getTableBodyColumnWebElementValues();
                List<Map<String, String>> detailsMapList = Lists.newArrayList();
                for (Map<String, WebElement> elementMap : elementsTable)
                {
                    Map<String, String> detailsMap = Maps.newHashMap();
                    for (String key : elementMap.keySet())
                    {
                        WebElement element = elementMap.get(key);
                        if (doesWebElementExist(element, By.tagName("span")))
                        {
                            WebElement firstSpanElement = element.findElements(By.tagName("span")).get(0);
                            detailsMap.put(key, DataPatcher.patchSharedData(firstSpanElement.getText()));
                        }
                        else
                        {
                            detailsMap.put(key, DataPatcher.patchSharedData(element.getText()));
                        }
                    }
                    detailsMapList.add(detailsMap);
                }
                return detailsMapList;
            }
        }
        throw new Exception("Unable to find section with heading " + heading);
    }

    public void clickOnViewStatement(final String about)
    {
        WebElement sliderLink = findElement(By.linkText("View statements " + about));
        jsClick(sliderLink);
    }

    public List<Map<String, String>> getStatements(final String about)
    {
        int index = 0;
        List<WebElement> sliders = findElements(By.cssSelector("div[data-type=slider] + a"));
        for (WebElement slider : sliders)
        {
            if (slider.getText().endsWith(about))
            {
                break;
            }
            index++;
        }
        WebElement statements = findElements(By.cssSelector("div[data-type=slider] table")).get(index);
        HtmlTable statementsTable = new HtmlTable(statements);
        return statementsTable.getTableBodyColumnTextValues();
    }

    public void clickOnContinueButton()
    {
        jsClick(findElement(By.cssSelector("button[data-action=continue]")));
    }

    public String getErrorMessage()
    {
        WebElement confirmationSection = findElement(By.className("confirm"));
        return confirmationSection.findElement(By.cssSelector("span.error.validation")).getText();
    }

    public void clickOnConfirmCheckBox()
    {
        WebElement confirmationSection = findElement(By.className("confirm"));
        jsClick(confirmationSection.findElement(By.cssSelector("a.button.element-checkbox")));
    }

    public void clickOnAmendDetailsButton()
    {
        WebElement amendBtn = findElement(By.cssSelector("button[data-action=amend]"));
        jsClick(amendBtn);
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

    public List<Map<String, String>> getClaims()
    {

        List<Map<String, String>> detailsMapList = Lists.newArrayList();
        for (WebElement detailsSection : detailsSections)
        {
            String actualHeading = detailsSection.findElement(By.tagName("h3")).getText();
            if ("Your Insurance History".equals(actualHeading))
            {
                Map<String, String> detailsMap = Maps.newHashMap();
                if (doesWebElementExist(By.className("claims")))
                {
                    WebElement claimsTable = detailsSection.findElement(By.className("claims"));
                    for (WebElement claims : claimsTable.findElements(By.tagName("span")))
                    {
                        if(claims.getText().contains("£"))
                        {
                            detailsMap.put("claimAmount", DataPatcher.patchSharedData(claims.getText()));
                        }
                        else
                        {
                            detailsMap.put("claimCause", DataPatcher.patchSharedData(claims.getText()));
                        }
                    }
                    detailsMapList.add(detailsMap);
                }
            }
        }
        return detailsMapList;
    }
}
