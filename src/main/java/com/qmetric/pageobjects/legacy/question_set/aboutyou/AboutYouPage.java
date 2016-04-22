package com.qmetric.pageobjects.legacy.question_set.aboutyou;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.Claim;
import com.qmetric.pageobjects.enquiry_forms.legacy.CoverDetails;
import com.qmetric.pageobjects.enquiry_forms.legacy.UserDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AboutYouPage extends BasePageObject
{

    private static final String LOADING_BOX_LOCATOR = "div.loading img";

    @FindBy(id = "submitPageOfEnquiry") WebElement nextPageButton;

    @FindBy(css = "button.action.submit") WebElement nextPageButtonBackoffice;

    @FindBy(id = "claimsTable") WebElement claimsTable;

    @FindBy(css = "#does_anyone_in_property_smoke_1") WebElement propertySmokeYes;

    public final AboutYouSection aboutYouSection;

    public final JointPolicyHolderSection jointPolicyHolderSection;

    public final YourInsuranceHistorySection yourInsuranceHistorySection;

    public AboutYouPage(WebDriver driver)
    {
        super(driver);
        aboutYouSection = PageFactory.initElements(driver, AboutYouSection.class);
        jointPolicyHolderSection = PageFactory.initElements(driver, JointPolicyHolderSection.class);
        yourInsuranceHistorySection = PageFactory.initElements(driver, YourInsuranceHistorySection.class);
    }

    public void enterDetails(final UserDetails userDetails, final CoverDetails coverDetails) throws Exception
    {
        try
        {
            aboutYouSection.selectDayOfBirth(userDetails.getDateOfBirth().dayOfMonth().getAsText());
            aboutYouSection.selectMonthOfBirth(userDetails.getDateOfBirth().monthOfYear().getAsText());
            aboutYouSection.selectYearOfBirth(userDetails.getDateOfBirth().year().getAsText());
        }
        catch (Exception e)
        {
            LOG.info("Can't find date of birth drop-downs. Could be in renewal state.");
            enterDateOfBirthWhenOnRenewal(userDetails);
        }
        aboutYouSection.selectMaritalStatus(userDetails.getMaritalStatus());
        aboutYouSection.enterOccupation(userDetails.getOccupation());
        aboutYouSection.selectSmoker(userDetails.isSmoker());
        aboutYouSection.selectStatements(userDetails.isUserStatements());

        jointPolicyHolderSection.selectJointHolderRadioButton(userDetails.getJointPolicyHolder() != null);
        if (userDetails.getJointPolicyHolder() != null)
        {
            enterJointPolicyHolderDetails(userDetails);
        }
        yourInsuranceHistorySection.selectYearsHeld(coverDetails.getYearsNoClaim());
        boolean hasPreviousClaims = coverDetails.getPreviousClaimList().size() > 0;
        yourInsuranceHistorySection.selectPreviousClaimsMade(hasPreviousClaims);
        if (hasPreviousClaims)
        {
            enterClaimDetails(coverDetails);
        }
        pause(3000);
    }

    //When renewing a policy, the elements for date of birth are different
    void enterDateOfBirthWhenOnRenewal(final UserDetails userDetails) throws Exception
    {
        aboutYouSection.selectDayOfBirthRenewal(userDetails.getDateOfBirth().dayOfMonth().getAsText());
        aboutYouSection.selectMonthOfBirthRenewal(userDetails.getDateOfBirth().monthOfYear().getAsText());
        aboutYouSection.selectYearOfBirthRenewal(userDetails.getDateOfBirth().year().getAsText());
    }

    public void continueToNextPage()
    {
        pause(2000);
        waitForElementPresent(5, "button.action.submit");
        jsClick(nextPageButtonBackoffice);
        waitForElementPresent(10, LOADING_BOX_LOCATOR);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    public void continueToNextRenewalPage()
    {
        nextPageButton = driver.findElement(By.className("continue"));
        jsClick(nextPageButton);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    // this method should only be used if you already clicked in 'yes' in 'previous claims' radio button
    private void enterClaimDetails(final CoverDetails coverDetails) throws Exception
    {
        List<Claim> claims = coverDetails.getPreviousClaimList();
        for (int i = 1; i <= claims.size(); i++)
        {
            if (i > 1)
            {
                yourInsuranceHistorySection.addNewClaim();
            }
            Claim claimInfo = claims.get(i - 1);
            NewClaim currentClaim = getCurrentClaim(i);
            currentClaim.enterValueOfClaim(claimInfo.getValue());
            currentClaim.selectTypeOfClaim(claimInfo.getType());
            if (claimInfo.getType().equals("Buildings"))
            {
                currentClaim.selectBuildingsCauseOfClaim(claimInfo.getCause());
            }
            else
            {
                currentClaim.selectContentsCauseOfClaim(claimInfo.getCause());
            }
            currentClaim.selectMonthOfClaim(claimInfo.getMonth());
            currentClaim.selectYearOfClaim(claimInfo.getYear());
            currentClaim.selectClaimSettled(claimInfo.isHasBeenSettled());
            currentClaim.saveClaim();
        }
    }

    private void enterJointPolicyHolderDetails(final UserDetails userDetails) throws Exception
    {
        jointPolicyHolderSection.firstPolicyHolder.selectJointHolderTitle(userDetails.getJointPolicyHolder().getTitle());
        jointPolicyHolderSection.firstPolicyHolder.enterJointHolderFirstName(userDetails.getJointPolicyHolder().getFirstName());
        jointPolicyHolderSection.firstPolicyHolder.enterJointHolderLastName(userDetails.getJointPolicyHolder().getLastName());
        jointPolicyHolderSection.firstPolicyHolder.selectJointHolderDayOfBirth(userDetails.getJointPolicyHolder().getDateOfBirth().dayOfMonth().getAsText());
        jointPolicyHolderSection.firstPolicyHolder.selectJointHolderMonthOfBirth(userDetails.getJointPolicyHolder().getDateOfBirth().monthOfYear().getAsText());
        jointPolicyHolderSection.firstPolicyHolder.selectJointHolderYearOfBirth(userDetails.getJointPolicyHolder().getDateOfBirth().year().getAsText());
        jointPolicyHolderSection.firstPolicyHolder.enterJointHolderOccupation(userDetails.getJointPolicyHolder().getOccupation());
        jointPolicyHolderSection.firstPolicyHolder.savePolicyHolder();
    }

    private NewClaim getCurrentClaim(int index)
    {
        NewClaim claim;
        switch (index)
        {
            case 1:
                claim = yourInsuranceHistorySection.firstClaim;
                break;
            case 2:
                claim = yourInsuranceHistorySection.secondClaim;
                break;
            case 3:
                claim = yourInsuranceHistorySection.thirdClaim;
                break;
            case 4:
                claim = yourInsuranceHistorySection.fourthClaim;
                break;
            default:
                throw new IllegalArgumentException("index must be in range [1, 4]");
        }
        return claim;
    }

    public WebElement getClaimsTable()
    {
        waitForElementVisible(20, "#claimsTableRow1");
        return claimsTable;
    }

    public void selectPropertySmoke()
    {
        jsClick(propertySmokeYes);
    }

    public void continueToNextPageWebsite()
    {
        nextPageButton = driver.findElement(By.id("submitPageOfEnquiry"));
        jsClick(nextPageButton);
    }
}
