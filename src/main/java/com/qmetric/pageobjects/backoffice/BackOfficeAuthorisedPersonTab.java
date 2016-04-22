package com.qmetric.pageobjects.backoffice;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.HtmlTable;
import com.qmetric.domain.QuestionType;
import com.qmetric.utilities.DynamicElementHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class BackOfficeAuthorisedPersonTab extends BasePageObject
{
    private final String authorisedPersonTitleId = "auth-person-title";

    private final String authorisedPersonFirstNameId = "auth-person-first-name";

    private final String authorisedPersonLastNameId = "auth-person-last-name";

    private final String authorisedPersonDobId = "auth-person-date-of-birth";

    public BackOfficeAuthorisedPersonTab(final WebDriver driver)
    {
        super(driver);
    }

    public void addAuthorisedPerson(final Map<String, String> authorisedPerson) throws Exception
    {
        WebElement policyInformationSection = findElement(By.id("policy-informations"));
        WebElement authorisedPersonForm = policyInformationSection.findElement(By.id("auth-persons-form"));
        EnquiriesDropDownAnswersSection enquiriesDropDownAnswersSection = new EnquiriesDropDownAnswersSection(driver);
        enquiriesDropDownAnswersSection.selectDropDownAnswer(authorisedPersonTitleId, authorisedPerson.get("Title"));
        EnquiriesTextAnswersSection enquiriesTextAnswersSection = new EnquiriesTextAnswersSection(driver);
        enquiriesTextAnswersSection.enterAnswerText(authorisedPersonFirstNameId, authorisedPerson.get("First name"));
        enquiriesTextAnswersSection.enterAnswerText(authorisedPersonLastNameId, authorisedPerson.get("Surname"));
        EnquiriesDateAnswersSection enquiriesDateAnswersSection = new EnquiriesDateAnswersSection(driver);
        enquiriesDateAnswersSection.selectDateAnswer(authorisedPersonDobId, QuestionType.DATEOFBIRTH, authorisedPerson.get("DOB"), "");
        WebElement button = authorisedPersonForm.findElement(By.cssSelector("button"));
        jsClick(button);
    }

    public void removeAuthorisedPerson(final Map<String, String> authorisedPerson) throws Exception
    {
        final WebElement policyInformationSection = findElement(By.id("policy-informations"));
        List<Map<String, WebElement>> authorisedPersonsList = new DynamicElementHandler<List<Map<String, WebElement>>>()
        {
            @Override
            public List<Map<String, WebElement>> handleDynamicElement()
            {
                WebElement authorisedPersonInfo = policyInformationSection.findElement(By.cssSelector("table"));
                HtmlTable authorisedPersonInfoTable = new HtmlTable(authorisedPersonInfo);
                return authorisedPersonInfoTable.getTableBodyColumnWebElementValues();
            }
        }.execute();
        for (Map<String, WebElement> authorisedPersonsMap : authorisedPersonsList)
        {
            if (authorisedPerson.get("Title").equals(authorisedPersonsMap.get("Title").getText()) &&
                authorisedPerson.get("First name").equals(authorisedPersonsMap.get("First name").getText()) &&
                authorisedPerson.get("Surname").equals(authorisedPersonsMap.get("Surname").getText()) &&
                authorisedPerson.get("DOB").equals(authorisedPersonsMap.get("DOB").getText()))
            {
                jsClick(authorisedPersonsMap.get("Action").findElement(By.tagName("a")));
                return;
            }
        }
        throw new Exception("Failed to remove authorised person: " + authorisedPerson);
    }
}
