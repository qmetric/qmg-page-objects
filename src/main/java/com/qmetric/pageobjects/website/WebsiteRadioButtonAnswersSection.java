package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 11/11/2014
 */
public class WebsiteRadioButtonAnswersSection extends BasePageObject
{
    public WebsiteRadioButtonAnswersSection(WebDriver driver)
    {
        super(driver);
    }

    public void selectYesNoAnswer(WebElement questionElement, String answer)
    {
        boolean isSelected = false;
        int retries = 0;
        do
        {
            try
            {
                WebElement yesNoContainer = questionElement.findElement(By.className("yes-no"));
                WebElement radioButton = yesNoContainer.findElement(By.cssSelector("button[data-value='" + answer + "']"));
                jsClick(radioButton);
                if (radioButton.getAttribute("class").equals("selected"))
                {
                    isSelected = true;
                }
            }
            catch (Exception e)
            {
                retries++;
                LOG.warn("Could not select radio button. Retry attempt no: " + retries + ". Retrying...");
            }
        }
        while (!isSelected && retries < 10);
    }

    public void selectYesNoAnswerForCustomisable(WebElement questionElement, String answer)
    {
        WebElement yesNoContainer = questionElement.findElement(By.className("yes-no"));
        clickOnYesNoButton(yesNoContainer, answer);
    }

    public void selectYesNoForCustomisableExtra(WebElement questionElement, String answer)
    {
        clickOnYesNoButton(questionElement, answer);
    }

    private void clickOnYesNoButton(final WebElement yesNoContainer, final String answer)
    {
        WebElement radioButton = yesNoContainer.findElement(By.cssSelector("button[data-value='" + answer + "']"));
        jsClick(radioButton);
    }

    public void selectRadioButtonAnswer(WebElement questionElement, String answer)
    {
        boolean isSelected = false;
        int retries = 0;
        do
        {
            try
            {
                WebElement radioContainer = questionElement.findElement(By.className("radio"));
                WebElement radioButton = radioContainer.findElement(By.cssSelector("button[data-value='" + answer + "']"));
                jsClick(radioButton);
                if (radioButton.getAttribute("class").equals("selected"))
                {
                    isSelected = true;
                }
            }
            catch (Exception e)
            {
                retries++;
                LOG.warn("Could not select radio button. Retry attempt no: " + retries + ". Retrying...");
            }
        }
        while (!isSelected && retries < 10);
    }
}
