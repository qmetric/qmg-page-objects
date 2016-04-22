package com.qmetric.pageobjects;

import com.qmetric.utilities.TimeHelper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 10/11/2014
 */
public abstract class DateAnswersSection extends BasePageObject
{
    public DateAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    protected String[] splitDates(String answer)
    {
        DateTime dateTime;
        if (answer.equals("<dateToday>"))
        {
            dateTime = DateTime.now().toDateTime();
        }
        else if (answer.equals("<date+30days>"))
        {
            dateTime = DateTime.now().toDateTime().plusDays(30);
        }
        else if (answer.equals("<date-30days>"))
        {
            dateTime = DateTime.now().toDateTime().minusDays(30);
        }
        else
        {
            DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            dateTime = DateTime.parse(answer, dateFormatter);
        }
        DateTimeFormatter DD_MMMM_YYYY_FORMAT = DateTimeFormat.forPattern("dd/MMMM/yyyy");
        return dateTime.toString(DD_MMMM_YYYY_FORMAT).split("/");
    }

    protected void selectDayFromPicker(String[] dates, WebElement dateTextBox) throws Exception
    {
        WebElement datePickerContainer = waitAndGetDatePickerContainer(dateTextBox);

        WebElement yearDropDown = datePickerContainer.findElement(By.cssSelector("select.pika-select.pika-select-year"));
        selectDropDownValueByVisibleText(yearDropDown, dates[2]);
        jsClick(dateTextBox);

        datePickerContainer = waitAndGetDatePickerContainer(dateTextBox);

        WebElement monthDropDown = datePickerContainer.findElement(By.cssSelector("select.pika-select.pika-select-month"));
        selectDropDownValueByVisibleText(monthDropDown, dates[1]);
        jsClick(dateTextBox);

        datePickerContainer = waitAndGetDatePickerContainer(dateTextBox);

        WebElement calendarTable = datePickerContainer.findElement(By.cssSelector("table.pika-table"));
        WebElement dayButton = calendarTable.findElement(By.cssSelector("td[data-day=\"" + Integer.parseInt(dates[0]) + "\"] > button"));
        dayButton.click();
    }

    private WebElement waitAndGetDatePickerContainer(WebElement dateTextBox) throws Exception
    {
        WebElement datePickerContainer = getDatePickerContainer(dateTextBox);
        return datePickerContainer;
    }

    private WebElement getDatePickerContainer(WebElement dateTextBox) throws Exception
    {
        Optional<WebElement> datePickerContainer;
        int timeout = 0;
        do
        {
            datePickerContainer = waitForDatePickerElement();
            if (!datePickerContainer.isPresent())
            {
                jsClick(dateTextBox);
            }
            else
            {
                break;
            }
            timeout++;
            TimeHelper.waitHalfASecond();
        }
        while (datePickerContainer == null && timeout < 10);
        if (datePickerContainer == null)
        {
            throw new Exception("Date picker is not visible.");
        }
        return datePickerContainer.get();
    }

    private Optional<WebElement> waitForDatePickerElement()
    {
        List<WebElement> datePickerElements = findElements(By.cssSelector("div.pika-single.is-bound"));
        for (WebElement datePickerElement : datePickerElements)
        {
            if (!datePickerElement.getAttribute("class").contains("is-hidden"))
            {
                return Optional.of(datePickerElement);
            }
        }
        return Optional.empty();
    }
}
