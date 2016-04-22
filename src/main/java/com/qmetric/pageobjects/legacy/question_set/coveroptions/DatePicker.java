package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import com.qmetric.pageobjects.BasePageObject;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/05/2013
 */
public abstract class DatePicker extends BasePageObject
{

    @FindBy(css = "div.datepicker_vista:not([aria-hidden='true'])") WebElement calendarContainer;

    DatePicker(WebDriver driver)
    {
        super(driver);
    }

    public abstract void clickOnStartDateInputBox();

    public abstract void clickOnStartDateInputBoxBackOffice();

    public boolean selectToday()
    {
        try
        {
            WebElement todayElement = calendarContainer.findElement(By.cssSelector("td.today.day.selected"));
            jsClick(todayElement);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void selectDate(String prevStartDate, String startDate) throws Exception
    {
        if(!startDate.equals("today")) {
            int day = Integer.parseInt(startDate.substring(0, startDate.indexOf("/")));
            int month = Integer.parseInt(startDate.substring(startDate.indexOf("/") + 1, startDate.lastIndexOf("/")));
            int presentMonth = 0;
            if (prevStartDate.equals("today")) {
                presentMonth = new DateTime().now().getMonthOfYear();
            } else {
                presentMonth = Integer.parseInt(prevStartDate.substring(prevStartDate.indexOf("/") + 1, prevStartDate.lastIndexOf("/")));
            }

            if (presentMonth < month) {
                while (presentMonth < month) {
                    getVisibleElementFromElementsList(By.cssSelector(".next")).click();
                    pause(2000);
                    presentMonth++;
                }
            } else if (presentMonth > month) {
                while (presentMonth > month) {
                    getVisibleElementFromElementsList(By.cssSelector(".previous")).click();
                    pause(2000);
                    presentMonth--;
                }
            }
            WebElement monthTd = getVisibleElementFromElementsList(By.xpath("//td[text()='" + day + "']"));
            waitForElementVisible(120, monthTd);
            if(monthTd.isDisplayed())
            {
                jsClick(monthTd);
            }
        }
        else
        {
            selectToday();
        }
    }
}
