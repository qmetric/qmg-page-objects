package com.qmetric.pageobjects.website;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.DateAnswersSection;
import com.qmetric.domain.QuestionType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteDateAnswerSection extends DateAnswersSection
{
    public WebsiteDateAnswerSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectDateAnswer(WebElement questionElement, QuestionType questionType, String answer, String dateType) throws Exception
    {
        String[] dates;
        switch (questionType)
        {
            case DATEMONTHANDYEAR:
                dates = answer.split(" ");
                // Only month and year drop down...
                List<WebElement> dateDropDownElements = questionElement.findElements(By.cssSelector("div[class='dk_container dk_theme_default dk-enabled']"));
                selectYearAndMonthFromDropDowns(dateDropDownElements, dates[0], dates[1]);
                break;
            default:
                dates = splitDates(answer);
                if (dateType.equals("calendarSelect"))
                {
                    selectDateFromCalendarPicker(questionElement, dates);
                }
                else
                {
                    selectDatesFromDropDowns(questionElement, dates);
                }
                break;
        }
    }

    public void selectDateFromCalendarPicker(WebElement questionElement, String[] dates) throws Exception
    {
        WebElement dateTextBox = questionElement.findElement(By.cssSelector("input[type=text]"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=text]")));
        jsClick(dateTextBox);
        selectDayFromPicker(dates, dateTextBox);
    }

    private void selectDatesFromDropDowns(WebElement questionElement, String[] dates)
    {
        List<WebElement> dateDropDownElements = questionElement.findElements(By.cssSelector("div[class='dk_container dk_theme_default dk-enabled']"));
        WebElement dayDropDownElement = dateDropDownElements.get(0);
        dateDropDownElements.remove(0);
        selectYearAndMonthFromDropDowns(dateDropDownElements, dates[1], dates[2]);
        jsClick(dayDropDownElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']")));
        final WebElement dayOptionsElement = dayDropDownElement.findElement(By.className("dk_options_inner"));
        jsClick(dayOptionsElement.findElement(By.cssSelector("a[data-dk-dropdown-value='" + Integer.parseInt(dates[0]) + "']")));
        webDriverWaitWithPolling(5, 1, new Predicate<WebDriver>()
        {
            @Override
            public boolean apply(WebDriver input)
            {
                return !dayOptionsElement.getAttribute("class").contains("dk_open");
            }
        });
    }

    public void selectYearAndMonthFromDropDowns(List<WebElement> dateDropDownElements, String month, String year)
    {
        WebElement monthDropDownElement = dateDropDownElements.get(0);
        jsClick(monthDropDownElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']")));
        WebElement monthOptionsElement = monthDropDownElement.findElement(By.className("dk_options_inner"));
        List<WebElement> monthValueElements = monthOptionsElement.findElements(By.cssSelector("a"));
        for (WebElement monthValueElement : monthValueElements)
        {
            if (monthValueElement.getText().equals(month))
            {
                jsClick(monthValueElement);
                break;
            }
        }
        WebElement yearDropDownElement = dateDropDownElements.get(1);
        jsClick(yearDropDownElement.findElement(By.cssSelector("a[class='dk_toggle dk_label']")));
        WebElement yearOptionsElement = yearDropDownElement.findElement(By.className("dk_options_inner"));
        jsClick(yearOptionsElement.findElement(By.cssSelector("a[data-dk-dropdown-value='" + year + "']")));
    }
}
