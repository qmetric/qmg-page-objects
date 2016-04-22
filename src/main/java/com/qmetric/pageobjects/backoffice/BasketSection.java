package com.qmetric.pageobjects.backoffice;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.domain.Quote;
import com.qmetric.utilities.DynamicElementHandler;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 10/11/2014
 */
public class BasketSection extends BasePageObject
{
    public BasketSection(WebDriver driver)
    {
        super(driver);
    }

    public List<Quote> getQuotesFromBasket(final int expectedSize)
    {
        final List<Quote> actualQuotes = new ArrayList<Quote>();
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                actualQuotes.clear();

                final WebElement basketContainer = findElement(By.id("basket-summary"));
                if (!basketContainer.isDisplayed())
                {
                    clickOnBasketLink();
                }
                waitForElementVisible(30, basketContainer);
                webDriverWaitWithPolling(30, 1, new Predicate<WebDriver>()
                {
                    @Override
                    public boolean apply(final WebDriver driver)
                    {
                        return basketContainer.findElements(By.className("basket-product")).size() + basketContainer.findElements(By.className("basket-extra")).size() ==
                               expectedSize;
                    }
                });
                List<WebElement> productsElements = basketContainer.findElements(By.className("basket-product"));
                for (WebElement productElement : productsElements)
                {
                    addQuote(productElement, actualQuotes);
                }

                List<WebElement> extrasElements = basketContainer.findElements(By.className("basket-extra"));
                for (WebElement extrasElement : extrasElements)
                {
                    addQuote(extrasElement, actualQuotes);
                }
                return null;
            }
        }.execute();
        return actualQuotes;
    }

    private void addQuote(final WebElement productElement, final List<Quote> actualQuotes)
    {
        WebElement titleElement = findElement(productElement, By.className("title"));
        WebElement priceElement = findElement(productElement, By.className("price"));
        waitForElementVisible(15, titleElement);
        waitForElementVisible(15, priceElement);
        Quote quote = new Quote();
        quote.setProductName(titleElement.getText());
        quote.setPrice(priceElement.getText());
        actualQuotes.add(quote);
    }

    public String getMessageOnBasket(final String expectedMessage)
    {
        webDriverWaitWithPolling(15, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(final WebDriver driver)
            {
                return findElement(By.id("basket")).getText().contains(expectedMessage);
            }
        });
        WebElement basketElement = findElement(By.id("basket"));
        return basketElement.getText();
    }

    public void clickOnBasketLink()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                By basketElementLocator = By.cssSelector("#basket > span > span:nth-child(2)");
                new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(basketElementLocator));
                WebElement basketElement = findElement(basketElementLocator);
                TimeHelper.waitInSeconds(1);
                jsClick(basketElement);
                return null;
            }
        }.execute();
    }

    public void confirmAndBuyWithoutPayment()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement basketContainer = findElement(By.id("basket-nil"));
                WebElement confirmButton = basketContainer.findElement(By.cssSelector("button[data-action=confirm-nil]"));
                jsClick(confirmButton);
                return null;
            }
        }.execute();
    }

    public void clickOnSellAnnuallyButton()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement basketContainer = findElement(By.id("basket-left"));
                WebElement sellAnnuallyContainer = basketContainer.findElement(By.className("left"));
                jsClick(sellAnnuallyContainer.findElement(By.cssSelector("button[data-action=pay-annual-now]")));
                return null;
            }
        }.execute();
    }

    public void clickOnSellMonthlyButton()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement basketContainer = findElement(By.id("basket-left"));
                WebElement sellMonthlyContainer = basketContainer.findElement(By.className("right"));
                jsClick(sellMonthlyContainer.findElement(By.cssSelector("button[data-action=pay-with-dd]")));
                return null;
            }
        }.execute();
    }

    public WebElement getSellAnnuallyButton()
    {
        WebElement basketContainer = findElement(By.id("basket-left"));
        WebElement sellAnnuallyContainer = basketContainer.findElement(By.className("left"));
        return sellAnnuallyContainer.findElement(By.cssSelector("button[data-action=pay-annual-now]"));
    }

    private WebElement getExistingCardOption()
    {
        WebElement cardOptionsAnchor = findElement(By.id("card-options"));
        return cardOptionsAnchor.findElement(By.cssSelector("li:nth-of-type(1) input"));
    }

    public void makePaymentWithExistingCard()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement existingCardOption = getExistingCardOption();
                existingCardOption.click();
                return null;
            }
        }.execute();

        clickOnSellAnnuallyButton();
    }

    public void switchToPaymentFrame()
    {
        new DynamicElementHandler<Void>()
        {
            By by = By.id("paymentframe");

            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, driver.findElement(by));
                driver.switchTo().frame("paymentframe");
                return null;
            }
        }.execute();
    }

    public void enterNetbanxRefundReference(final String netbanxReference)
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement basketRefundContainer = findElement(By.id("basket-refund"));
                WebElement refundTextInput = basketRefundContainer.findElement(By.cssSelector("input[data-id=netbanx-reference]"));
                waitForElementVisible(30, refundTextInput);
                TimeHelper.waitInSeconds(3);
                enterTextInput(refundTextInput, netbanxReference);
                Actions actions = new Actions(driver);
                actions.sendKeys(Keys.ENTER).perform();
                return null;
            }
        }.execute();
    }

    public String getMonthlyPrice()
    {
        WebElement basketContainer = findElement(By.id("basket-left"));
        WebElement sellMonthlyContainer = basketContainer.findElement(By.className("right"));
        WebElement monthlyPremium = sellMonthlyContainer.findElement(By.className("basket-premium"));
        return monthlyPremium.getText();
    }

    public String getAPRStatement()
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                WebElement paymentBreakdownDetails = findElement(By.className("payment-breakdown-details"));
                String aprStatement = paymentBreakdownDetails.findElement(By.cssSelector("p")).getText();
                return aprStatement;
            }
        }.execute();
    }

    public void removeQuotesFromBasket(String productName)
    {
        final WebElement basketContainer = findElement(By.id("basket-summary"));
        WebElement productElement = basketContainer.findElement(By.className("basket-product"));
        if (productElement.findElement(By.className("title")).getText().equals(productName))
        {
            WebElement removeButton = productElement.findElement(By.cssSelector("button[data-action=delete]"));
            removeButton.click();
        }
        else
        {
            List<WebElement> extrasElements = basketContainer.findElements(By.className("basket-extra"));
            for (WebElement extrasElement : extrasElements)
            {
                if (extrasElement.findElement(By.className("title")).getText().equals(productName))
                {
                    WebElement removeButton = extrasElement.findElement(By.cssSelector("button[data-action=delete-extra]"));
                    removeButton.click();
                    break;
                }
            }
        }
    }

    public void amendQuotesFromBasket(final String productName)
    {
        final WebElement basketContainer = findElement(By.id("basket-summary"));
        WebElement productElement = basketContainer.findElement(By.className("basket-product"));
        if (productElement.findElement(By.className("title")).getText().equals(productName))
        {
            WebElement amendButton = productElement.findElement(By.cssSelector("button[data-action=amend]"));
            amendButton.click();
        }
    }

    public void addDiscount(final String productName, final String discountAmount)
    {
        clickDiscountButton(productName);
        new DynamicElementHandler<Void>()
        {
            final WebElement discountContainer = findElement(By.cssSelector(".basket-discount"));

            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, discountContainer);
                WebElement discountInputBox = discountContainer.findElement(By.cssSelector("input[data-id=discountGiven]"));
                enterTextInput(discountInputBox, discountAmount);
                TimeHelper.waitInSeconds(2);
                return null;
            }
        }.execute();
    }

    public void editDiscountValue(String maxDiscount)
    {
        new DynamicElementHandler<Void>()
        {
            final WebElement discountContainer = findElement(By.cssSelector(".basket-discount"));

            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, discountContainer);
                WebElement discountInputBox = discountContainer.findElement(By.cssSelector("input[data-id=discountGiven]"));
                enterTextInput(discountInputBox, maxDiscount);
                TimeHelper.waitInSeconds(2);
                return null;
            }
        }.execute();
    }

    public void clickApplyDiscount()
    {
        new DynamicElementHandler<Void>()
        {
            final WebElement discountContainer = findElement(By.cssSelector(".basket-discount"));

            @Override
            public Void handleDynamicElement()
            {
                waitForElementVisible(30, discountContainer);
                WebElement discountApplyButton = discountContainer.findElement(By.cssSelector("button[data-action=apply]"));
                discountApplyButton.click();
                return null;
            }
        }.execute();
    }

    public boolean validateDiscount(BigDecimal productPrice, BigDecimal maxDiscountValue)
    {
        boolean isDiscountValid = false;
        final WebElement discountCointainer = findElement(By.cssSelector(".basket-discount"));
        Map<String, String> discountItem = getDiscountInfo(discountCointainer);
        BigDecimal currentAmount = productPrice.subtract(maxDiscountValue);
        if (discountItem.get("Original Product Price:").equals(productPrice) &&
            discountItem.get("Current discounted Price:").contains(currentAmount.toPlainString()) &&
            discountItem.get("Maximum discount available:").contains(String.valueOf(maxDiscountValue)) &&
            discountItem.get("Percentage used:").equals("100.00%"))
        {
            LOG.info("The discount table has valid discount data");
            isDiscountValid = true;
        }
        else
        {
            LOG.info("The discount table has invalid discount data");
        }
        return isDiscountValid;
    }

    private Map<String, String> getDiscountInfo(WebElement discountContainer)
    {
        WebElement discount = discountContainer.findElement(By.cssSelector("dl[class=discount-info]"));
        Map discountItem = new LinkedHashMap<>();
        for (WebElement discountItemLine : discount.findElements(By.cssSelector("dt")))
        {
            discountItem.put(discountItemLine.getText(), discount.findElement(By.cssSelector("dd")).getText());
        }
        return discountItem;
    }

    private void clickDiscountButton(final String productName)
    {
        final WebElement basketContainer = findElement(By.id("basket-summary"));
        WebElement productElement = basketContainer.findElement(By.className("basket-product"));
        if (productElement.findElement(By.className("title")).getText().equals(productName))
        {
            WebElement dButton = productElement.findElement(By.cssSelector("button[data-action=discount]"));
            dButton.click();
        }
    }

    public BigDecimal getProductPrice()
    {
        final WebElement basketContainer = findElement(By.id("basket-summary"));
        WebElement productElement = basketContainer.findElement(By.className("basket-product"));
        return new BigDecimal(productElement.findElement(By.className("price")).getText().replace("£", "").replace("x", "").trim());
    }

    public void waitForDiscountToBeApplied()
    {
        new DynamicElementHandler<Void>()
        {
            @Override
            public Void handleDynamicElement()
            {
                WebElement discountLoading = findElement(By.cssSelector(".tag-loading"));
                waitForElementNotVisible(30, discountLoading);
                return null;
            }
        }.execute();
    }

    public boolean checkValidationError(final String error)
    {
        Pattern pattern = Pattern.compile(error);
        Matcher matcher = pattern.matcher(getValidationError());
        if (matcher.find())
        {
            return true;
        }
        return false;
    }

    public String getMaxDiscountValue()
    {
        final String validationError = getValidationError();
        return validationError.substring(validationError.indexOf("£"), validationError.indexOf("or less than zero"));
    }

    private String getValidationError()
    {
        return new DynamicElementHandler<String>()
        {
            final WebElement discountContainer = findElement(By.cssSelector(".basket-discount"));

            @Override
            public String handleDynamicElement()
            {
                waitForElementVisible(30, discountContainer.findElement(By.cssSelector("label[class=validation]")));
                return discountContainer.findElement(By.cssSelector("label[class=validation]")).getText();
            }
        }.execute();
    }

    public String getPaymentPageText()
    {
        return driver.findElement(By.cssSelector("#cardDetails > fieldset:nth-child(1) > div > div:nth-child(2) > p:nth-child(1)")).getText();
    }

}
