package com.qmetric.pageobjects.legacy;

import com.google.common.base.Predicate;
import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class ClaimsUploadPage extends BasePageObject
{
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement uploadClaim;

    @FindBy(name = "claims")
    private WebElement fileTextBox;

    @FindBy(id = "Midas")
    private WebElement midas;

    @FindBy(id = "Ageas")
    private WebElement ageas;

    @FindBy(id = "Aviva")
    private WebElement aviva;

    @FindBy(id = "Groupama")
    private WebElement groupama;

    @FindBy(id = "Axa")
    private WebElement axa;

    @FindBy(id = "Trinity M")
    private WebElement trinity;

    @FindBy(id = "Direct Group")
    private WebElement directGroup;

    @FindBy(id = "success")
    private WebElement results;

    @FindBy(id = "failure")
    private WebElement errors;

    public ClaimsUploadPage(WebDriver driver)
    {
        super(driver);
    }

    public void uploadMidasClaim()
    {
        String file = "Midas";
        pause(2000);
        jsClick(midas);
        File claimsUploadFile = new File(file + " claims file.csv");
        LOG.info("Claims upload file was created: " + claimsUploadFile.getAbsolutePath());
        enterTextInput(fileTextBox, claimsUploadFile.getAbsolutePath());
        jsClick(uploadClaim);
    }

    public String getResults()
    {
        return results.getText();
    }

    public String getSuccessfulMessage(final int numberOfClaims)
    {
        webDriverWaitWithPolling(60, 1, new Predicate<WebDriver>()
        {
            @Override public boolean apply(final WebDriver driver)
            {
                return results.getText().equals("Successful " + numberOfClaims + " Finished Upload");
            }
        });
        return results.getText();
    }

    public String getErrors()
    {
        return errors.getText();
    }

    public void uploadClaim(final String policyType, final File claimsUploadFile)
    {
        pause(2000);
        switch(policyType.toLowerCase().trim())
        {
            case "ageas": jsClick(ageas);break;
            case "aviva": jsClick(aviva);break;
            case "midas": jsClick(midas);break;
            case "groupama": jsClick(groupama);break;
            case "axa": jsClick(axa);break;
            case "trinity": jsClick(trinity); break;
            case "directgroup": jsClick(directGroup); break;
            default: break;
        }
        LOG.info("Claims upload file was created: " + claimsUploadFile.getAbsolutePath());
        pause(2000);
        LOG.info("Claims upload file was created: " + claimsUploadFile.getAbsolutePath());
        enterTextInput(fileTextBox, claimsUploadFile.getAbsolutePath());
        jsClick(uploadClaim);
    }
}
