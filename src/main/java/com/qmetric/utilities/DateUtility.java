package com.qmetric.utilities;

import com.google.common.base.Optional;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: ateoh Date: 05/11/2012 Time: 13:48 To change this template use File | Settings | File Templates.
 */
public class DateUtility
{
    private String day;

    private String month;

    private String year;

    private final Calendar calendar = Calendar.getInstance();

    public DateUtility()
    {
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        year = String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getDateTime(String dateTimePattern)
    {
        DateTime dateTime = new DateTime();
        return dateTime.toString(dateTimePattern);
    }

    public void generateDateOfBirth(int age)
    {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.add(Calendar.YEAR, -age);
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        year = String.valueOf(calendar.get(Calendar.YEAR));
    }

    public long getTimestampInSeconds()
    {
        return new Date().getTime() / 1000;
    }

    public String getFormattedDate(String pattern)
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(calendar.getTime());
    }

    public int convertMonthToNumber(String month)
    {
        Calendar cal = Calendar.getInstance();
        monthFormatter(month, "MMM", cal);
        int monthInt = cal.get(Calendar.MONTH) + 1;
        return monthInt;
    }

    private SimpleDateFormat monthFormatter(final String month, final String format, final Calendar cal)
    {
        SimpleDateFormat formatter = null;
        try
        {
            formatter = new SimpleDateFormat(format);
            cal.setTime(formatter.parse(month));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return formatter;
    }

    public String getDay()
    {
        String format = "%1$02d";
        return String.format(format, Integer.valueOf(day));
    }

    public String getMonth()
    {
        return month;
    }

    public String getYear()
    {
        return year;
    }

    public static String formatMonthFromNumber(Integer monthInNumberFormat)
    {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        try
        {
            return monthDisplay.format(monthParse.parse(monthInNumberFormat.toString()));
        }
        catch (ParseException e)
        {
            return "Error formatting month";
        }
    }

    public static Optional<String> formatDate(String dateTime, String fromFormat, String toFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
        SimpleDateFormat formatter1 = new SimpleDateFormat(toFormat);
        try
        {
            Date date = formatter.parse(dateTime);
            return Optional.of(formatter1.format(date));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return Optional.absent();
    }

    public static String parseDate(final String date, String format)
    {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        if(date.contains("today"))
        {
            if(date.contains("-"))
            {
                return DateTime.now().minusDays(Integer.parseInt(date.substring(date.indexOf("-") + 1, date.length()))).toString(formatter);
            }
            else if(date.contains("+"))
            {
                return DateTime.now().plusDays(Integer.parseInt(date.substring(date.indexOf("+"), date.length()))).toString(formatter);
            }
            else
            {
                return DateTime.now().toString(formatter);
            }
        }
        else
        {
            return formatter.parseDateTime(date).toString();
        }
    }
}
