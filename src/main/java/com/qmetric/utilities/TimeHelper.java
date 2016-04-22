package com.qmetric.utilities;

import java.util.concurrent.TimeUnit;

public final class TimeHelper
{
    public static void waitInSeconds(int seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitInMilliseconds(int milliseconds)
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitHalfASecond()
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
