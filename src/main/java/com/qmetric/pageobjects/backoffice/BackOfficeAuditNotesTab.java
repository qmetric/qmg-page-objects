package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BackOfficeAuditNotesTab extends BasePageObject
{
    public BackOfficeAuditNotesTab(WebDriver driver)
    {
        super(driver);
    }

    public WebElement getAuditNotesTable()
    {
        return findElement(By.className("policy-info")).findElement(By.className("grid"));
    }

    public boolean compareAuditNotes(String auditType, String expectedAuditNote)
    {
        return new DynamicElementHandler<Boolean>()
        {
            @Override
            public Boolean handleDynamicElement()
            {
                WebElement entireAuditInfo = getAuditNotesTable();
                List<WebElement> auditTypes = entireAuditInfo.findElements(By.cssSelector("td:nth-child(1)"));
                List<WebElement> auditNotes = entireAuditInfo.findElements(By.cssSelector("td:nth-child(4)"));

                for (WebElement auditTypeEntry : auditTypes)
                {
                    String auditEntry = auditTypeEntry.getText();
                    if (auditEntry.equalsIgnoreCase(auditType))
                    {
                        for (WebElement auditNoteEntry : auditNotes)
                        {
                            String auditNote = auditNoteEntry.getText();
                            if (auditNote.equalsIgnoreCase(expectedAuditNote))
                            {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        }.execute();
    }

    public String getDetails(String expectedAuditType)
    {
        return new DynamicElementHandler<String>()
        {
            @Override
            public String handleDynamicElement()
            {
                WebElement entireAuditInfo = getAuditNotesTable();
                List<WebElement> tableRows = entireAuditInfo.findElements(By.cssSelector("tbody > tr"));
                for(WebElement tableRow : tableRows)
                {
                    WebElement auditType = tableRow.findElement(By.cssSelector("td:nth-child(1)"));
                    String auditTypeEntry = auditType.getText();
                    if(auditTypeEntry.equals(expectedAuditType))
                    {
                        return tableRow.findElement(By.cssSelector("td:nth-child(4)")).getText();
                    }
                }
                return "";
            }
        }.execute();
    }

    public void addAuditNote(final String auditType, final String auditNote) throws Exception
    {
        WebElement auditTypeSection = findElement(By.cssSelector(".select"));
        selectDropDownValueByVisibleText(auditTypeSection, auditType);
        WebElement auditNoteSection = findElement(By.cssSelector(".input"));
        enterTextInput(auditNoteSection, auditNote);
        WebElement submitAuditButton = findElement(By.cssSelector(".Audit-Add-Note"));
        jsClick(submitAuditButton);
    }
}
