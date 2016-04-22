package com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 13/03/2013
 */

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * This page object represents the customer agreed statements
 */

public class StatementsSummary extends BasePageObject
{

    @FindBy(css = "ol.summary > li") List<WebElement> summaryStatementsList;

    public StatementsSummary(WebDriver driver)
    {
        super(driver);
    }

    public int getNumberOfStatements()
    {
        return summaryStatementsList.size();
    }

    public String getStatementText(int index)
    {
        return summaryStatementsList.get(index).getText();
    }

    boolean isStatementHighlighted(int index)
    {
        WebElement statementElem = summaryStatementsList.get(index);
        return statementElem.findElements(By.className("highlight")).size() > 0;
    }

    public int getNumberOfHighlightedStatements()
    {
        int count = 0;
        for (int i = 0; i < getNumberOfStatements(); i++)
        {
            if (isStatementHighlighted(i))
            {
                count++;
            }
        }
        return count;
    }
}
