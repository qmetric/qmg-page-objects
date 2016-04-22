package com.qmetric.pageobjects;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.qmetric.browser.utility.Browser;
import com.qmetric.shared.SharedData;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public abstract class BasePageObject
{
    protected WebDriver driver;

    protected final Logger LOG = Logger.getLogger(getClass());

    protected BasePageObject(WebDriver driver)
    {
        this.driver = driver;
    }

    public void refresh()
    {
        driver.navigate().refresh();
    }

    public void openUrl(String url)
    {
        driver.get(url);
    }

    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    protected void waitForElementPresent(int timeOutInSeconds, String cssLocator)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssLocator)));
    }

    protected void waitForElementPresent(int timeOutInSeconds, By by)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void waitForElementVisible(int timeOutInSeconds, WebElement element)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementVisible(int timeOutInSeconds, String cssLocator)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    protected void waitForElementClickable(int timeOutInSeconds, String cssLocator)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssLocator)));
    }

    public void waitForElementNotVisible(int timeOutInSeconds, WebElement element)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    protected void waitForElementNotVisible(int timeOutInSeconds, String cssLocator)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    protected void waitForElementNotVisible(int timeOutInSeconds, By by)
    {
        new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void scrollTo(WebElement element)
    {
        int x = element.getLocation().getX();
        int y = element.getLocation().getY();
        if (SharedData.BROWSER.equals(Browser.INTERNET_EXPLORER))
        {
            String script = "window.scrollTo("+x+","+y+")";
            ((JavascriptExecutor) driver).executeScript(script, element);
        }
        else
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(String.format("javascript:window.scrollBy(%d, %d)", x, y));
        }
    }

    protected void webDriverWaitWithPolling(int timeoutInSeconds, int pollingSeconds, Predicate<WebDriver> predicate)
    {
        new WebDriverWait(driver, timeoutInSeconds).pollingEvery(pollingSeconds, TimeUnit.SECONDS).until(predicate);
    }

    protected void enterTextInput(WebElement inputTextBox, String text)
    {
        waitForElementVisible(30, inputTextBox);
        try
        {
            clearTextInput(inputTextBox);
        }
        catch (WebDriverException ignore)
        {
        }

        // workaround because firefox doesn't allow typing these two special characters
        if (SharedData.BROWSER.equals(Browser.FIREFOX) && (text.contains("(") || text.contains("&")))
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value ='" + text + "';", inputTextBox);
        }
        else
        {
            inputTextBox.sendKeys(text);
        }
    }

    protected void clearTextInput(WebElement inputTextBox)
    {
        waitForElementVisible(30, inputTextBox);
        inputTextBox.clear();
    }

    protected void selectDropDownValueByVisibleText(WebElement element, String value) throws Exception
    {
        if (value == null)
        {
            throw new Exception("Value to be selected in dropdown is null");
        }
        Select select = waitForSelectElement(element);
        try
        {
            select.selectByVisibleText(value);
        }
        catch (Exception e)
        {
            throw new Exception("Could not select text from dropdown - " + value, e);
        }
    }

    protected void selectDropDownValueByIndex(WebElement element, int index)
    {
        Select select = waitForSelectElement(element);
        select.selectByIndex(index);
    }

    protected void selectDropDownByOptionValue(WebElement element, String value)
    {
        Select select = waitForSelectElement(element);
        select.selectByValue(value);
    }

    private Select waitForSelectElement(final WebElement element)
    {
        Wait<WebDriver> wait = webDriverFluentWait();
        final Select select = wait.until(new Function<WebDriver, Select>()
        {
            public Select apply(WebDriver driver)
            {
                return new Select(element);
            }
        });
        wait.until(new Function<WebDriver, Boolean>()
        {
            public Boolean apply(WebDriver driver)
            {
                return select.getOptions().size() > 0;
            }
        });
        return select;
    }

    private Wait<WebDriver> webDriverFluentWait()
    {
        return new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    protected void jsClick(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, 45);
        wait.until(ExpectedConditions.visibilityOf(element));
        jsClickWithoutWait(element);
    }

    protected void jsClickWithoutWait(WebElement element)
    {
        scrollTo(element);
        doJsClick(element);
    }

    private void doJsClick(final WebElement element)
    {
        if (SharedData.BROWSER.equals(Browser.INTERNET_EXPLORER))
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(element).click(element).release().build().perform();
        }
        else
        {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private String getBrowserName()
    {
        Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
        return cp.getBrowserName();
    }

    protected boolean doesWebElementExist(final By by)
    {
        return driver.findElements(by).size() > 0;
    }

    protected boolean doesWebElementExist(final WebElement element, final By by)
    {
        return element.findElements(by).size() > 0;
    }

    protected WebElement findElement(final By by)
    {
        Wait<WebDriver> wait = webDriverFluentWait();
        return wait.until(new Function<WebDriver, WebElement>()
        {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(by);
            }
        });
    }

    protected WebElement findElement(final WebElement parentElement, final By by)
    {
        Wait<WebDriver> wait = webDriverFluentWait();
        return wait.until(new Function<WebDriver, WebElement>()
        {
            public WebElement apply(WebDriver driver)
            {
                return parentElement.findElement(by);
            }
        });
    }

    protected List<WebElement> findElements(final By by)
    {
        Wait<WebDriver> wait = webDriverFluentWait();
        return wait.until(new Function<WebDriver, List<WebElement>>()
        {
            public List<WebElement> apply(WebDriver driver)
            {
                return driver.findElements(by);
            }
        });
    }

    protected List<WebElement> findElements(final WebElement element, final By by)
    {
        Wait<WebDriver> wait = webDriverFluentWait();
        return wait.until(new Function<WebDriver, List<WebElement>>()
        {
            public List<WebElement> apply(WebDriver driver)
            {
                return element.findElements(by);
            }
        });
    }

    protected List<WebElement> getVisibleElementsFromElementsList(By locator) throws Exception
    {
        List<WebElement> visibleElements = Lists.newArrayList();
        List<WebElement> elementsToFind = findElements(locator);
        for (WebElement elementFound : elementsToFind)
        {
            if (elementFound.isDisplayed())
            {
                visibleElements.add(elementFound);
            }
        }
        return visibleElements;
    }

    protected WebElement getVisibleElementFromElementsList(By locator) throws Exception
    {
        WebElement visibleElement = null;
        List<WebElement> elementsToFind = findElements(locator);
        for (WebElement elementFound : elementsToFind)
        {
            if (elementFound.isDisplayed())
            {
                visibleElement = elementFound;
                break;
            }
        }
        if (visibleElement == null)
        {
            throw new Exception("Unable to find element with locator " + locator);
        }
        return visibleElement;
    }

    protected void waitForUrlDoesNotContain(int timeOutInSeconds, String url)
    {
        new WebDriverWait(driver, 10).until(urlDoesNotContain(url));
    }

    public Predicate<WebDriver> urlDoesNotContain(final String urlPart)
    {
        return new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver driver)
            {
                return !driver.getCurrentUrl().contains(urlPart);
            }
        };
    }

    protected void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    protected List<String> getDropdownElementsText(WebElement dropDownElement)
    {
        List<WebElement> dropDownOptionElements = dropDownElement.findElements(By.cssSelector("option"));
        List<String> dropDownElementsTextList = Lists.newArrayListWithCapacity(dropDownOptionElements.size());
        for (WebElement optionElement : dropDownOptionElements)
        {
            dropDownElementsTextList.add(optionElement.getText().trim());
        }
        return dropDownElementsTextList;
    }

    protected void pause(long miliseconds)
    {
        try
        {
            Thread.sleep(miliseconds);
        }
        catch (InterruptedException ignored)
        {

        }
    }

    public String getPageTitle()
    {
        return driver.getTitle();
    }
}
