package com.qmetric.pageobjects.backoffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class EnquiriesCollectionDateAnswersSection extends EnquiriesDateAnswersSection
{
    private WebElement collectionQuestionContainer;

    public EnquiriesCollectionDateAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void setCollectionQuestionContainer(WebElement collectionQuestionContainer)
    {
        this.collectionQuestionContainer = collectionQuestionContainer;
    }

    public void selectDatesFromDropDowns(String questionId, String[] dates) throws Exception
    {
        selectYearAndMonthFromDropDowns(questionId, dates[1], dates[2]);
        WebElement questionContainer = collectionQuestionContainer.findElement(By.cssSelector("div[data-name=\"" + questionId + "\"]"));
        WebElement dayDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"days\"]"));
        // Parse int so that any single digit with 0 prefix is truncated, for example 08 becomes 8
        selectDropDownValueByVisibleText(dayDropDown, Integer.parseInt(dates[0]) + "");
    }

    public void selectYearAndMonthFromDropDowns(String questionId, String month, String year)
    {
        WebElement questionContainer = collectionQuestionContainer.findElement(By.cssSelector("div[id^=" + questionId + "]"));
        WebElement monthDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"months\"]"));
        WebElement yearDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"years\"]"));
        selectDropDownByOptionValue(yearDropDown, year);
        int monthInt = convertMonthToInteger(month);
        selectDropDownByOptionValue(monthDropDown, monthInt + "");
    }

    public void selectDobDates(String questionId, String[] dates) throws Exception
    {
        WebElement dayDropDown = collectionQuestionContainer.findElement(By.cssSelector("select[data-type=\"days\"][data-test=\"" + questionId + "\"]"));
        WebElement monthDropDown = collectionQuestionContainer.findElement(By.cssSelector("select[data-type=\"months\"][data-test=\"" + questionId + "\"]"));
        WebElement yearDropDown = collectionQuestionContainer.findElement(By.cssSelector("select[data-type=\"years\"][data-test=\"" + questionId + "\"]"));
        // Parse int so that any single digit with 0 prefix is truncated, for example 08 becomes 8
        selectDropDownValueByVisibleText(dayDropDown, Integer.parseInt(dates[0]) + "");
        selectDropDownValueByVisibleText(monthDropDown, dates[1]);
        selectDropDownValueByVisibleText(yearDropDown, dates[2]);
    }

    private int convertMonthToInteger(String month)
    {
        Date date = null;
        try
        {
            date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(month);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
}
