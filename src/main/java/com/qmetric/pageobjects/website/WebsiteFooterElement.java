package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebsiteFooterElement extends BasePageObject
{
    public WebsiteFooterElement(WebDriver driver)
    {
        super(driver);
    }

    public String getFooterText()
    {
        WebElement footer = findElement(By.tagName("footer"));
        return footer.getText();
    }
}
