package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 07/11/2014
 */
public class EnquiriesImageAnswerSection extends BasePageObject
{
    public EnquiriesImageAnswerSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectImageAnswer(final String questionId, final String answer)
    {
        WebElement imageElementContainer = findElement(By.id(questionId));
        List<WebElement> imagesElement = imageElementContainer.findElements(By.className("clickable-image"));
        for (WebElement imageElement : imagesElement)
        {
            WebElement headerElement = imageElement.findElement(By.cssSelector("header"));
            if (headerElement.getText().equals(answer))
            {
                jsClick(imageElement);
                break;
            }
        }
    }
}
