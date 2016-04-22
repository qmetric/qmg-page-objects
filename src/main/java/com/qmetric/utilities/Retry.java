package com.qmetric.utilities;

import java.util.function.Predicate;

/**
 * Created by vketipisz on 29/05/15.
 */
public class Retry<T>
{
    public int LOOP_SLEEP = 5000;

    public int MAX_RETRY = 14;

    public void waitWhile(T t, Predicate<T> checker)
    {
        int retryCount = 0;
        while(checker.test(t))
        {
            TimeHelper.waitInMilliseconds(LOOP_SLEEP);
            retryCount++;
            if (retryCount > MAX_RETRY)
            {
                throw new RuntimeException("Max number of retries exceeded.");
            }
        }
    }

    public Retry<T> setLoopSleep(int sleepMillis)
    {
        LOOP_SLEEP = sleepMillis;
        return this;
    }

    public Retry<T> setMaxRetry(int retryCount)
    {
        MAX_RETRY = retryCount;
        return this;
    }

}
