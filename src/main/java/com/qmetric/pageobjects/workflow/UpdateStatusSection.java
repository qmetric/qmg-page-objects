package com.qmetric.pageobjects.workflow;

import com.qmetric.pageobjects.BasePageObject;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 31/07/2014
 */
public class UpdateStatusSection extends BasePageObject
{
    final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

    final DateTimeFormatter hourFormatter = DateTimeFormat.forPattern("HH");

    final DateTimeFormatter minuteFormatter = DateTimeFormat.forPattern("mm");

    @FindBy(css = "button.show-contacted.btn-white.button")
    WebElement contactYes;

    @FindBy(css = "button.show-no-contact.btn-white button")
    WebElement contactNo;

    @FindBy(css = "x-deck x-card:nth-child(2) select")
    WebElement contactYesDropDown;

    @FindBy(css = "x-deck x-card:nth-child(3) select")
    WebElement contactNoDropDown;

    @FindBy(css = "select.diarise-select-hours")
    WebElement diariseHoursDropDown;

    @FindBy(css = "select.diarise-select-minutes")
    WebElement diariseMinutesDropDown;

    @FindBy(css = "button.close-the-task.button")
    WebElement closeTaskButton;

    @FindBy(css = "button.diarise-the-task.button")
    WebElement diariseTaskButton;

    @FindBy(id = "workflow-get-task")
    WebElement getTaskButton;

    @FindBy(css = "input.diarise-date-input")
    WebElement calendarInput;

    @FindBy(css = "a.diarise-date-today")
    WebElement todayButton;

    public UpdateStatusSection(WebDriver driver)
    {
        super(driver);
    }

    public void clickContactNoButton()
    {
        contactNo.click();
    }

    public void clickContactYesButton()
    {
        contactYes.click();
    }

    public void selectNoContactReason(String noContactReason) throws Exception
    {
        selectDropDownValueByVisibleText(contactNoDropDown, noContactReason);
    }

    public void selectContactReason(String contactReason) throws Exception
    {
        selectDropDownValueByVisibleText(contactYesDropDown, contactReason);
    }

    public void clickCloseTaskButton()
    {
        closeTaskButton.click();
    }

    public void clickDiariseTaskButton()
    {
        diariseTaskButton.click();
    }

    public void diariseTask(UpdateTaskReason updateTaskReason, DateTime scheduledTime) throws Exception
    {
        LOG.info("Update task with reason: " + updateTaskReason + "\nFor date: " + dateFormatter.print(scheduledTime));
        if (updateTaskReason.getContact().equals("Yes"))
        {
            clickContactYesButton();
            selectContactReason(updateTaskReason.getReason());
        }
        else if (updateTaskReason.getContact().equals("No"))
        {
            clickContactNoButton();
            selectContactReason(updateTaskReason.getReason());
        }

        //is today
        if (DateUtils.isSameDay(scheduledTime.toDate(), DateTime.now().toDate()))
        {
            clickTodayButton();
        }
        else
        {
            //if not today choose day from calendar
            calendarInput.click();
            DiariseCalendar diariseCalendar = PageFactory.initElements(driver, DiariseCalendar.class);
            diariseCalendar.selectDate(scheduledTime);
        }

        selectDiariseHours(hourFormatter.print(scheduledTime));
        selectDiariseMinutes(minuteFormatter.print(scheduledTime));

        clickDiariseTaskButton();
        waitForElementVisible(5, getTaskButton);
    }

    public void closeTask(UpdateTaskReason updateTaskReason) throws Exception
    {
        LOG.info("Closing task with reason: " + updateTaskReason.getReason());
        if (updateTaskReason.getContact().equals("Yes"))
        {
            clickContactYesButton();
            selectContactReason(updateTaskReason.getReason());
        }
        else if (updateTaskReason.getContact().equals("No"))
        {
            clickContactNoButton();
            selectNoContactReason(updateTaskReason.getReason());
        }
        clickCloseTaskButton();
        waitForElementVisible(5, getTaskButton);
    }

    private void selectDiariseHours(String hours) throws Exception
    {
        selectDropDownValueByVisibleText(diariseHoursDropDown, hours);
    }

    private void selectDiariseMinutes(String minutes) throws Exception
    {
        selectDropDownValueByVisibleText(diariseMinutesDropDown, minutes);
    }

    public void clickTodayButton()
    {
        todayButton.click();
    }
}
