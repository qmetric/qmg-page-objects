package com.qmetric.domain;

import org.openqa.selenium.WebElement;

public class OneAccountEnquiry
{
    public final String businessLine;

    public final String risk;

    public final String price;

    public final WebElement viewButton;

    public OneAccountEnquiry(final String businessLine, final String risk, final String price, final WebElement viewButton)
    {
        this.businessLine = businessLine;
        this.risk = risk;
        this.price = price;
        this.viewButton = viewButton;
    }
}
