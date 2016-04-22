package com.qmetric.pageobjects.referrersweb;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.shared.properties.ConfigurationProperties;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ReferrersWebMainPage extends BasePageObject
{
    public ReferrersWebMainPage(WebDriver driver)
    {
        super(driver);
    }

    public void open()
    {
        driver.get(ConfigurationProperties.REFERRERS_WEB_URL);
    }

    public List<Map<String, String>> getReferrerTable()
    {
        return new DynamicElementHandler<List<Map<String, String>>>()
        {
            @Override
            public List<Map<String, String>> handleDynamicElement()
            {
                WebElement table = findElement(By.cssSelector("table"));
                HtmlTable htmlTable = new HtmlTable(table);
                return htmlTable.getTableBodyColumnTextValues();
            }
        }.execute();
    }
}
