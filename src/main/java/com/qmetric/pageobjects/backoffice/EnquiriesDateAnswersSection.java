package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.DateAnswersSection;
import com.qmetric.domain.Answer;
import com.qmetric.domain.QuestionType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormatSymbols;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesDateAnswersSection extends DateAnswersSection
{
    public EnquiriesDateAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectDateAnswer(String questionId, QuestionType questionType, String answer, String dateType) throws Exception
    {
        String[] dates;
        switch (questionType)
        {
            case DATEMONTHANDYEAR:
                if(questionId.contains("dateOfClaim") && answer.contains("<month> <year>"))
                {
                    String template = "<month> <year>";
                    template = template.replace("<month>", new DateFormatSymbols().getMonths()[DateTime.now().getMonthOfYear()]);
                    if (answer.contains("-"))
                    {
                        int years = Integer.parseInt(answer.substring(answer.indexOf("-")+1, answer.length()));
                        template = template.replace("<year>", String.valueOf(DateTime.now().minusYears(years).getYear()));
                    }
                    else if (answer.contains("+"))
                    {
                        int years = Integer.parseInt(answer.substring(answer.indexOf("+")+1, answer.length()));
                        template = template.replace("<year>", String.valueOf(DateTime.now().plusYears(years).getYear()));
                    }
                    else
                    {
                        template = DateTime.now().getYear()+"";
                    }
                    answer = template;
                }
                dates = answer.split(" ");
                // Only month and year drop down...
                selectYearAndMonthFromDropDowns(questionId, dates[0], dates[1]);
                break;
            case DATEOFBIRTH:
                dates = splitDates(answer);
                selectDobDates(questionId, dates);
                break;
            default:
                dates = splitDates(answer);
                if (dateType.equals("calendarSelect"))
                {
                    selectDateFromCalendarPicker(questionId, dates);
                }
                else
                {
                    selectDatesFromDropDowns(questionId, dates);
                }
                break;
        }
    }

    public void selectDobDates(String questionId, String[] dates) throws Exception
    {
        selectDatesFromDropDowns(questionId, dates);
    }

    protected void selectDateFromCalendarPicker(String questionId, String[] dates) throws Exception
    {
        WebElement dateTextBox = findElement(By.cssSelector("input#" + questionId));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id(questionId)));
        dateTextBox.click();
        selectDayFromPicker(dates, dateTextBox);
    }

    public void selectDatesFromDropDowns(String questionId, String[] dates) throws Exception
    {
        selectYearAndMonthFromDropDowns(questionId, dates[1], dates[2]);
        WebElement questionContainer = findElement(By.id(questionId));
        WebElement dayDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"days\"]"));
        // Parse int so that any single digit with 0 prefix is truncated, for example 08 becomes 8
        selectDropDownValueByVisibleText(dayDropDown, Integer.parseInt(dates[0]) + "");
    }

    public void selectYearAndMonthFromDropDowns(String questionId, String month, String year) throws Exception
    {
        WebElement questionContainer = findElement(By.id(questionId));
        WebElement monthDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"months\"]"));
        WebElement yearDropDown = questionContainer.findElement(By.cssSelector("select[data-type=\"years\"]"));
        selectDropDownValueByVisibleText(yearDropDown, year);
        selectDropDownValueByVisibleText(monthDropDown, month);
    }

    public void getDateAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        if (doesWebElementExist(mainQuestionContainer, By.cssSelector("input[data-type=\"pikaday\"]")))
        {
            getCalendarPickerAnswer(mainQuestionContainer, answer);
        }
        else
        {
            getDropdownDateAnswer(mainQuestionContainer, answer);
        }
    }

    public WebElement getDateAnswerElement(String questionId)
    {
        return findElement(By.cssSelector("input#" + questionId));
    }


    private void getCalendarPickerAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        WebElement dateElement = mainQuestionContainer.findElement(By.cssSelector("input[data-type=\"pikaday\"]"));
        answer.setQuestionId(dateElement.getAttribute("id"));
        String answerValue = dateElement.getAttribute("value");
        checkIfDateIsToday(answer, answerValue);
    }

    private void getDropdownDateAnswer(WebElement mainQuestionContainer, Answer answer)
    {
        WebElement hiddenInput = mainQuestionContainer.findElement(By.cssSelector("input"));
        answer.setQuestionId(hiddenInput.getAttribute("data-name"));
        if (answer.getQuestionType().equals(QuestionType.DATEMONTHANDYEAR))
        {
            Select monthDropDown = new Select(mainQuestionContainer.findElement(By.cssSelector("select[data-test=\"" + answer.getQuestionId() + "\"][data-type=\"months\"]")));
            Select yearDropDown = new Select(mainQuestionContainer.findElement(By.cssSelector("select[data-test=\"" + answer.getQuestionId() + "\"][data-type=\"years\"]")));
            answer.setAnswerValue(monthDropDown.getFirstSelectedOption().getText() + " " + yearDropDown.getFirstSelectedOption().getText());
        }
        else
        {
            String answerValue = hiddenInput.getAttribute("value");
            checkIfDateIsToday(answer, answerValue);
        }
    }

    private void checkIfDateIsToday(Answer answer, String answerValue)
    {
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String todaysDate = DateTime.now().toString(dateFormatter);
        if (answerValue.equals(todaysDate))
        {
            answerValue = "<dateToday>";
        }
        answer.setAnswerValue(answerValue);
    }
}
