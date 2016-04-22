package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteImageAnswerSection extends BasePageObject
{
    public WebsiteImageAnswerSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectImageAnswer(final WebElement questionElement, final String answer)
    {
        List<WebElement> imagesElement = questionElement.findElements(By.className("clickable-image"));
        for (WebElement imageElement : imagesElement)
        {
            WebElement headerElement = imageElement.findElement(By.cssSelector("header"));
            if (headerElement.getText().equals(answer))
            {
                imageElement.click();
                break;
            }
        }
    }
}
