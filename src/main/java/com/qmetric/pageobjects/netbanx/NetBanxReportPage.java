package com.qmetric.pageobjects.netbanx;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by vketipisz on 19/05/15.
 */
public class NetBanxReportPage extends BasePageObject
{

    @FindBy(id = "j_username")
    private WebElement userName;

    @FindBy(id = "j_password")
    private WebElement password;

    @FindBy(id = "loginBtn")
    private WebElement loginButton;

    @FindBy(css = "table.rptTable")
    private WebElement resultTable;

    @FindBy(id = "_menuReports")
    private WebElement menuReports;

    @FindBy(css = "tr.clearCell")
    private List<WebElement> resultRows;

    public NetBanxReportPage(WebDriver driver)
    {
        super(driver);
        driver.get("https://login.test.netbanx.com/");
    }

    public NetBanxReportPage login(String user, String pass)
    {
        userName.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
        return this;
    }

    public boolean lookupTransaction(String renewingPolicyId, String merchReference)
    {
        waitForElementPresent(15,By.id("_menuReports"));
        menuReports.click();
        waitForElementPresent(15, By.id("lookupLbl"));
        driver.findElement(By.id("lookupLbl")).click();
        driver.findElement(By.id("refNbr")).sendKeys(renewingPolicyId + "-" + merchReference);
        driver.findElement(By.id("btnSearch")).click();
        waitForResultTable();
        return isTransactionFound(renewingPolicyId,merchReference);
    }

    private void waitForResultTable()
    {
        waitForElementPresent(15,"table.rptTable");
    }

    private boolean isTransactionFound(String renewingPolicyId,String merchReference)
    {
        for ( WebElement resultRow : resultRows)
        {
            for(WebElement cell : resultRow.findElements(By.cssSelector("td")))
            {
                if(cell.getText().equalsIgnoreCase(renewingPolicyId+"-"+merchReference))
                {
                    return true;
                }
            }
        }
        return false;
    }


}
