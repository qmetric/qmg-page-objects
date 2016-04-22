package com.qmetric.utilities;

import org.openqa.selenium.StaleElementReferenceException;

public abstract class DynamicElementHandler<T> {

    protected abstract T handleDynamicElement();

    public T execute()
    {
        T dynamicElement = null;
        int timeout = 0;
        int maxTimeout = 10;
        while (timeout < maxTimeout)
        {
            try
            {
                dynamicElement = handleDynamicElement();
                if(terminatingCondition())
                {
                    break;
                }
            }
            catch(StaleElementReferenceException exception)
            {
                // Keep going.
            }
            timeout++;
            TimeHelper.waitInSeconds(1);
        }
        return dynamicElement;
    }

    protected boolean terminatingCondition()
    {
        return true;
    }
}

