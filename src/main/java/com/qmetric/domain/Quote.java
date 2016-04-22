package com.qmetric.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 10/11/2014
 */
public class Quote implements Comparable<Quote>
{

    private String productName;
    private String price;
    private boolean productDataAvailable;

    public Quote(String productName, String price, boolean productDataAvailable)
    {
        this.productName = productName;
        this.price = price;
        this.productDataAvailable = productDataAvailable;
    }

    public Quote()
    {

    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(final String productName)
    {
        this.productName = productName;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(final String price)
    {
        this.price = price;
    }

    public boolean isProductDataAvailable()
    {
        return productDataAvailable;
    }

    public void setProductDataAvailable(boolean productDataAvailable)
    {
        this.productDataAvailable = productDataAvailable;
    }

    public boolean equals(Object quote)
    {
        if (quote == null)
        {
            return false;
        }
        if (quote == this)
        {
            return true;
        }
        if (quote.getClass() != getClass())
        {
            return false;
        }
        Quote comparedQuote = (Quote) quote;
        return new EqualsBuilder().append(productName, comparedQuote.getProductName()).isEquals();
    }

    @Override
    public String toString()
    {
        return "Quote{" + "productName='" + productName + '\'' + ", price='" + price + '\'' + '}';
    }

    @Override
    public int compareTo(final Quote o)
    {
        return this.getProductName().compareTo(o.getProductName());
    }
}
