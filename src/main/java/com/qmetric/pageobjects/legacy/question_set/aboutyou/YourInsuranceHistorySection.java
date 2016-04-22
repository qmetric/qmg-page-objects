package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.qmetric.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 18/03/2013
 */
public class YourInsuranceHistorySection extends BasePageObject
{

    @FindBy(id = "no_claims_discount") WebElement consecutiveYearsHomeInsuranceDropDown;

    @FindBy(id = "previous_claims_1") WebElement claimsMadeYesRadioButton;

    @FindBy(id = "previous_claims_2") WebElement claimsMadeNoRadioButton;

    @FindBy(id = "questionprevious_claims") WebElement previousClaimsContainer;

    @FindBy(id = "claim1") WebElement firstClaimContainer;

    @FindBy(id = "claim2") WebElement secondClaimContainer;

    @FindBy(id = "claim3") WebElement thirdClaimContainer;

    @FindBy(id = "claim4") WebElement fourthClaimContainer;

    @FindBy(id = "claimsTable") WebElement claimsTable;

    @FindBy(css = "p.max-items") WebElement maxNumClaimsMessage;

    public NewClaim firstClaim = null;

    public NewClaim secondClaim = null;

    public NewClaim thirdClaim = null;

    public NewClaim fourthClaim = null;

    public YourInsuranceHistorySection(WebDriver driver)
    {
        super(driver);
    }

    public boolean isConsecutiveYearsHomeInsuranceDropDownDisplayed()
    {
        try
        {
            return consecutiveYearsHomeInsuranceDropDown.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isClaimsMadeRadioButtonDisplayed()
    {
        try
        {
            return claimsMadeYesRadioButton.isDisplayed() && claimsMadeNoRadioButton.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isMaxNumClaimsMessageDisplayed()
    {
        try
        {
            return maxNumClaimsMessage.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void selectYearsHeld(String yearsHeld) throws Exception
    {
        selectDropDownValueByVisibleText(consecutiveYearsHomeInsuranceDropDown, yearsHeld);
    }

    public void selectPreviousClaimsMade(boolean yes)
    {
        if (yes)
        {
            jsClick(claimsMadeYesRadioButton);
            firstClaim = new NewClaim(driver, firstClaimContainer, 1);
        }
        else
        {
            jsClick(claimsMadeNoRadioButton);
            clearAllClaims();
        }
    }

    public int getNumberOfClaimsAlreadySaved()
    {
        claimsTable = driver.findElement(By.cssSelector("li#questionprevious_claims table.stripy"));
        return claimsTable.findElements(By.cssSelector("tbody > tr")).size();
    }

    /**
     * Use this method to initialize a new claim
     */
    public void addNewClaim()
    {

        int numberOfClaims = getNumberOfClaimsAlreadySaved();
        if (numberOfClaims == 1)
        {
            clickNewClaimButton();
            secondClaim = new NewClaim(driver, secondClaimContainer, 2);
        }
        else if (numberOfClaims == 2)
        {
            clickNewClaimButton();
            thirdClaim = new NewClaim(driver, thirdClaimContainer, 3);
        }
        else if (numberOfClaims == 3)
        {
            clickNewClaimButton();
            fourthClaim = new NewClaim(driver, fourthClaimContainer, 4);
        }
        else if (numberOfClaims == 4)
        {
            clickNewClaimButton(); //should expect message saying that you can't add more claims
        }
    }

    /**
     * @param index starts in 1
     */
    public void deleteClaim(int index)
    {
        if (index > getNumberOfClaimsAlreadySaved())
        {
            throw new IllegalStateException("Index cannot be greater thant the number of claims already saved");
        }
        else
        {
            claimsTable = driver.findElement(By.cssSelector("li#questionprevious_claims table.stripy"));
            WebElement deleteLink = claimsTable.findElements(By.cssSelector("tbody > tr a.deleteLink")).get(index - 1);
            jsClick(deleteLink);
        }
    }

    public void deleteClaimByClaimCause(String claimCause)
    {
        claimsTable = driver.findElement(By.cssSelector("li#questionprevious_claims table.stripy"));
        List<WebElement> claimsTableRows = claimsTable.findElements(By.cssSelector("tbody > tr"));
        for(int i=0;i< claimsTableRows.size();i++)
        {
            if(claimsTableRows.get(i).findElement(By.cssSelector("td:nth-child(3)")).getText().equals(claimCause))
            {
                WebElement deleteLink = claimsTable.findElements(By.cssSelector("tbody > tr a.deleteLink")).get(i);
                deleteLink.click();
            }
        }
    }

    public void startUpdatingClaim(int index)
    {
        if (index > getNumberOfClaimsAlreadySaved())
        {
            throw new IllegalStateException("Index cannot be greater than the number of claims already saved");
        }
        else
        {
            claimsTable = driver.findElement(By.cssSelector("li#questionprevious_claims table.stripy"));
            WebElement updateLink = claimsTable.findElements(By.cssSelector("tbody > tr a.amendLink")).get(index - 1);
            jsClick(updateLink);
            pause(2000);
        }
    }

    private void clickNewClaimButton()
    {
        WebElement addButton = previousClaimsContainer.findElement(By.className("addition"));
        jsClick(addButton);
    }

    private void clearAllClaims()
    {
        firstClaim = null;
        secondClaim = null;
        thirdClaim = null;
        fourthClaim = null;
    }
}
