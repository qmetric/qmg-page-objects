package com.qmetric.pageobjects.workflow;

import com.qmetric.pageobjects.BasePageObject;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 24/10/2014
 */
public class DiariseCalendar extends BasePageObject
{
    final DateTimeFormatter yearFormatter = DateTimeFormat.forPattern("yyyy");

    final DateTimeFormatter monthFormatter = DateTimeFormat.forPattern("MMMM");  //may change

    final DateTimeFormatter dayFormatter = DateTimeFormat.forPattern("d");

    @FindBy(css = ".pika-title .pika-label")
    List<WebElement> monthAndYearLabels;

    @FindBy(css = "button.pika-next")
    WebElement nextPageButton;

    public DiariseCalendar(WebDriver driver)
    {
        super(driver);
        waitForElementVisible(5, driver.findElement(By.cssSelector("div.pika-single.is-bound")));
    }

    private void selectMonth(String month, String year)
    {
        //loop until in right year
        boolean yearIsNotCorrect = true;
        while (yearIsNotCorrect)
        {
            String currentYear = getYearSelected();
            if (currentYear.equals(year))
            {
                yearIsNotCorrect = false;
            }
            else
            {
                clickNextPage();
            }
        }
        //loop until in right month
        boolean monthIsNotCorrect = true;
        while (monthIsNotCorrect)
        {
            String currentMonth = getMonthSelected();
            if (currentMonth.equals(month))
            {
                monthIsNotCorrect = false;
            }
            else
            {
                clickNextPage();
            }
        }
    }

    private void selectDay(String day)
    {
        String locator = "td[data-day='" + day + "'] button";
        WebElement dayButton = driver.findElement(By.cssSelector("table.pika-table tbody " + locator));
        dayButton.click();
    }

    private void clickNextPage()
    {
        nextPageButton.click();
    }

    public String getMonthSelected()
    {
        return StringUtils.substringBefore(monthAndYearLabels.get(0).getText(), "\n");
    }

    public String getYearSelected()
    {
        return StringUtils.substringBefore(monthAndYearLabels.get(1).getText(), "\n");
    }

    public void selectDate(DateTime dateTime)
    {
        selectMonth(monthFormatter.print(dateTime), yearFormatter.print(dateTime));
        selectDay(dayFormatter.print(dateTime));
    }
}
