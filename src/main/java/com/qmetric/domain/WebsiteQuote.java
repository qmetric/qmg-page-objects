package com.qmetric.domain;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 10/11/2014
 */
public class WebsiteQuote implements Comparable<WebsiteQuote>
{

    private String productName;


    private String price;
    private boolean productDataAvailable;

    public WebsiteQuote(String productName, String price, boolean productDataAvailable)
    {
        this.productName = productName;
        this.price = price;
        this.productDataAvailable = productDataAvailable;
    }

    public WebsiteQuote()
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

    @Override
    public String toString()
    {
        return "Quote{" + "productName='" + productName + '\'' + ", price='" + price + '\'' + '}';
    }

    @Override
    public int compareTo(final WebsiteQuote o)
    {
        return this.getPrice().compareTo(o.getPrice());
    }
}
