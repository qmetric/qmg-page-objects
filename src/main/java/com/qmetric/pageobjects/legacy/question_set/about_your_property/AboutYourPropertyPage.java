package com.qmetric.pageobjects.legacy.question_set.about_your_property;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.PropertyDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AboutYourPropertyPage extends BasePageObject
{

    private static final String LOADING_BOX_LOCATOR = "div.loading img";

    @FindBy(id = "submitPageOfEnquiry")
    private WebElement nextPageButton;

    @FindBy(className = "validation-advice")
    private List<WebElement> validationErrorMessages;

    public final AboutYourPropertySection aboutYourPropertySection;

    public final AboutSafetyAndSecuritySection aboutSafetyAndSecuritySection;

    public AboutYourPropertyPage(WebDriver driver)
    {
        super(driver);
        aboutYourPropertySection = PageFactory.initElements(driver, AboutYourPropertySection.class);
        aboutSafetyAndSecuritySection = PageFactory.initElements(driver, AboutSafetyAndSecuritySection.class);
        aboutYourPropertySection.initialisePropertyTypeMap();
        aboutSafetyAndSecuritySection.initialiseAlarmMap();
        aboutSafetyAndSecuritySection.initialiseExternalLockMap();
        aboutSafetyAndSecuritySection.initialiseMainLockMap();
        aboutSafetyAndSecuritySection.initialisePatioDoorsLockMap();
    }

    public void enterFullPropertyDetails(PropertyDetails propertyDetails) throws Exception
    {
        //About Your Property
        try
        {
            aboutYourPropertySection.inputAddress(propertyDetails.getPostCode());
        }
        catch (Exception e)
        {
            LOG.info("Couldn't select address. Could be because it is in renewal process. " + "Trying to edit it...");

            try
            {
                aboutYourPropertySection.clickEditAddress();
                aboutYourPropertySection.inputAddress(propertyDetails.getPostCode());
            }
            catch (Exception e1)
            {
                LOG.info("Policy in renewal process.");
            }
        }

        aboutYourPropertySection.selectPropertyType(propertyDetails.getPropertyType());
        aboutYourPropertySection.selectPropertyDescription(propertyDetails.getPropertyType(), propertyDetails.getDescription());
        switch(propertyDetails.getPropertyType())
        {
            case "House":
            case "Bungalow":
                break;
            case "Flat/apartment":
            {
                aboutYourPropertySection.select10FlatsWithinBuilding(Boolean.parseBoolean(propertyDetails.getMoreThan10Flats()));
                aboutYourPropertySection.select4FloorsWithinBuilding(Boolean.parseBoolean(propertyDetails.getMoreThan4Floors()));
                break;
            }
        }
        aboutYourPropertySection.inputPropertyBuiltYear(propertyDetails.getYearBuilt());
        aboutYourPropertySection.selectNumberOfBedrooms(propertyDetails.getNumberOfBedrooms());
        aboutYourPropertySection.selectPropertyOwnership(propertyDetails.getOwnershipStatus());
        aboutYourPropertySection.selectOccupiedBy(propertyDetails.getOccupiedBy());
        aboutYourPropertySection.selectOccupiedWhen(propertyDetails.getOccupiedWhen());
        if(propertyDetails.getOccupiedWhen().equalsIgnoreCase("Never - it is unoccupied"))
        {
            aboutYourPropertySection.selectMonthsUnOccupiedToDate(propertyDetails.getMonthsUnOccupiedToDate());
            aboutYourPropertySection.selectMonthsUnOccupiedInTotal(propertyDetails.getMonthsUnOccupiedInTotal());
            if(propertyDetails.getMonthsUnOccupiedInTotal().equalsIgnoreCase("More than 6 months"))
            {
                aboutYourPropertySection.selectUnOccupiedMoreThan1Year(propertyDetails.isPropertyUnOccupiedMoreThan1Year());
            }
            aboutYourPropertySection.selectUnOccupancyReason(propertyDetails.getUnOccupancyReason());
            aboutYourPropertySection.setHeatingRadioButton(propertyDetails.isHeatingDrained());
        }
        else if(propertyDetails.getOccupiedWhen().equalsIgnoreCase("it is unoccupied for 30 days or more"))
        {
            aboutYourPropertySection.selectMonthsUnOccupiedInTotal(propertyDetails.getMonthsUnOccupiedInTotal());
            if(propertyDetails.getMonthsUnOccupiedInTotal().equalsIgnoreCase("More than 6 months"))
            {
                aboutYourPropertySection.selectUnOccupiedMoreThan1Year(propertyDetails.isPropertyUnOccupiedMoreThan1Year());
            }
            aboutYourPropertySection.setHeatingRadioButton(propertyDetails.isHeatingDrained());
            aboutYourPropertySection.selectUnOccupancyReason(propertyDetails.getUnOccupancyReason());
        }
        aboutYourPropertySection.selectTreesOption(propertyDetails.isTallTrees());
        aboutYourPropertySection.selectFlatRoofOption(propertyDetails.isFlatRoof());
        if (propertyDetails.isFlatRoof())
        {
            aboutYourPropertySection.selectFlatRoofPercentage(propertyDetails.getFlatRoofPercentage());
        }
        aboutYourPropertySection.selectStatementsOption(propertyDetails.isPropertyStatements());
        if (!propertyDetails.isPropertyStatements())
        {
             switch(propertyDetails.getPropertySubStatement())
             {
                 case "property_flood_affected_10_yr":
                 {
                     aboutYourPropertySection.statementsAgreementModalBox.selectNotAffectedByFloodsRadioButton(propertyDetails.isPropertyAffectedByFlood());
                     if (propertyDetails.getFloodSource().equalsIgnoreCase("Drain or culvert")) {
                         aboutYourPropertySection.statementsAgreementModalBox.floodsSection.selectFloodingSource(propertyDetails.getFloodSource());
                     } else {
                         aboutYourPropertySection.statementsAgreementModalBox.floodsSection.selectFloodingSource(propertyDetails.getFloodSource());
                         aboutYourPropertySection.statementsAgreementModalBox.floodsSection.selectFloodingDistanceFromWatercourse(propertyDetails.getFloodDistanceFormSource());
                         aboutYourPropertySection.statementsAgreementModalBox.floodsSection.selectGroundOfPropertyAboveTheFloodingSourceRadioButton(propertyDetails.getHeightOfPropertyFromSource());
                     }
                     break;
                 }
                 case "subsidence_support_underpinning":
                 {
                     aboutYourPropertySection.statementsAgreementModalBox.selectNoSubsidenceSupportUnderpinningRadioButton(propertyDetails.getSubsidenceSupportUnderpined());
                     aboutYourPropertySection.statementsAgreementModalBox.subsidenceSection.selectSubsidenceVisibleDate(propertyDetails.getSubsidenceVisible());
                     aboutYourPropertySection.statementsAgreementModalBox.subsidenceSection.selectPropertyUnderpinnedRadioButton(propertyDetails.getPropertyUnderPinned());
                     aboutYourPropertySection.statementsAgreementModalBox.subsidenceSection.selectSubsidenceValue(propertyDetails.getSubsidenceValue());
                     aboutYourPropertySection.statementsAgreementModalBox.subsidenceSection.selectSubsidenceCompleteRadioButton(propertyDetails.getSubsidenceComplete());
                 }
             }
            aboutYourPropertySection.statementsAgreementModalBox.confirmStatements();
        }

        aboutSafetyAndSecuritySection.selectWindowLocksOption(propertyDetails);
        aboutSafetyAndSecuritySection.selectMainLockOption(propertyDetails);
        aboutSafetyAndSecuritySection.setPatioLockOption(propertyDetails);
        aboutSafetyAndSecuritySection.setExternalLockOption(propertyDetails);
        aboutSafetyAndSecuritySection.setAlarmOption(propertyDetails);
    }

    public void continueToNextPage()
    {
        nextPageButton = driver.findElement(By.cssSelector("button.action.submit"));
        jsClick(nextPageButton);
        waitForElementPresent(10, LOADING_BOX_LOCATOR);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    public void continueToNextRenewalPage()
    {
        nextPageButton = driver.findElement(By.className("continue"));
        jsClick(nextPageButton);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    public boolean areThereValidationErrorMessages()
    {
        return validationErrorMessages.size() > 0;
    }

    public boolean checkIfRenewalEnquiry()
    {
        WebElement page = driver.findElement(By.cssSelector("#questionSetForm > h2 > strong"));
        if (page.getText().startsWith("Renewal"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void answerRenewalEnquiry()
    {
        aboutYourPropertySection.selectStatementsOption(true);
        continueToNextPage();
    }

    public void continueToNextPageWebsite()
    {
        nextPageButton = driver.findElement(By.id("submitPageOfEnquiry"));
        jsClick(nextPageButton);
    }

    public void editPostCode(String postCode)
    {
        aboutYourPropertySection.clickEditAddress();
        aboutYourPropertySection.inputPostCode(postCode);
    }
}
