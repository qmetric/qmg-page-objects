package com.qmetric.pageobjects.website.one_account;

import com.google.common.collect.Lists;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.utilities.FileDownloader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 27/06/2014
 */
public class PolicyDetailsPage extends BasePageObject
{
    @FindBy(css = "a.navigation-policies-btn")
    WebElement goBackButton;

    //Policy Information
    @FindBy(css = "div.policy-detail-content section:nth-child(1) span:nth-child(3)")
    WebElement typeOfInsurance;

    @FindBy(css = "div.policy-detail-content section:nth-child(1) span:nth-child(5)")
    WebElement postCode;

    @FindBy(css = "div.policy-detail-content section:nth-child(1) span:nth-child(7)")
    WebElement periodOfCover;

    @FindBy(css = "div.policy-detail-content section:nth-child(1) span:nth-child(9)")
    WebElement policyNumber;

    @FindBy(css = "div.policy-detail-content section:nth-child(1) span:nth-child(11)")
    WebElement insurer;

    //Policyholder information
    @FindBy(css = "div.policy-detail-content section:nth-child(2) span:nth-child(3)")
    WebElement policyHolder;

    @FindBy(css = "div.policy-detail-content section:nth-child(3)")
    WebElement policyDocumentationSection;

    public PolicyDetailsPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickGoBackButton()
    {
        goBackButton.click();
    }

    public String getTypeOfInsurance()
    {
        return typeOfInsurance.getText();
    }

    public String getPostCode()
    {
        return postCode.getText();
    }

    public String getPeriodOfCover()
    {
        return periodOfCover.getText();
    }

    public String getPolicyNumber()
    {
        return policyNumber.getText();
    }

    public String getInsurer()
    {
        return insurer.getText();
    }

    public String getPolicyHolder()
    {
        return policyHolder.getText();
    }

    public List<String> getDocumentNames()
    {
        List<String> documentNames = Lists.newArrayList();
        List<WebElement> documentElements = policyDocumentationSection.findElements(By.className("document-name"));
        for (WebElement documentElement : documentElements)
        {
            documentNames.add(documentElement.getText());
        }
        return documentNames;
    }

    public List<String> downloadDocuments(List<String> documentsList) throws Exception
    {
        List<String> fileLocations = new LinkedList<String>();
        FileDownloader fileDownloader = new FileDownloader();
        int index = 0;
        int max_index = documentsList.size();
        List<WebElement> downloadLinks = policyDocumentationSection.findElements(By.cssSelector("a"));
        for (WebElement downloadLink : downloadLinks)
        {
            if (downloadLink.getText().contains(documentsList.get(index)))
            {
                fileLocations.add(fileDownloader.downloadFile(downloadLink));
                index++;
                if (index == max_index)
                {
                    break;
                }
                else
                {
                    continue;
                }
            }
        }
        return fileLocations;
    }
}
