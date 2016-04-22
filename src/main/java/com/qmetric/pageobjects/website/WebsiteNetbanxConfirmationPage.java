package com.qmetric.pageobjects.website;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.TimeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebsiteNetbanxConfirmationPage extends BasePageObject
{
    private final String NBX_THREE_D_SECURE = "nbx_three_d_secure";

    @FindBy(id = "TestButtons")
    WebElement buttonsContainerElement;

    private final String SUCCESS = "Y - Authentication Successful";

    private final String FAILED = "N - Authentication Failed";

    private final String NOT_PERFOMED = "U - Authentication Could Not Be Performed";

    private final String ATTEMPTS_PERFORMED = "A - Attempts Processing Performed";

    public WebsiteNetbanxConfirmationPage(final WebDriver driver)
    {
        super(driver);
    }

    public void clickOnAuthenticationSuccessfulButton()
    {
        jsClick(buttonsContainerElement.findElement(By.cssSelector("input[value='" + SUCCESS + "']")));
    }

    public void switchToNetbanxFrame()
    {
        TimeHelper.waitInSeconds(10);
        driver.switchTo().frame(NBX_THREE_D_SECURE);
    }

    public void switchToMainFrame()
    {
        driver.switchTo().parentFrame();
    }
}
