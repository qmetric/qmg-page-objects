package com.qmetric.pageobjects.legacy.question_set.coveroptions;

import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.enquiry_forms.legacy.CoverDetails;
import com.qmetric.pageobjects.enquiry_forms.legacy.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoverOptionsPage extends BasePageObject
{

    private static final String LOADING_BOX_LOCATOR = "div.loading img";

    @FindBy(id = "insurance_type_1") WebElement buildingsContentsRadioButton;

    @FindBy(id = "insurance_type_2") WebElement buildingsOnlyRadioButton;

    @FindBy(id = "insurance_type_3") WebElement contentsOnlyRadioButton;

    @FindBy(css = "a[rel='today']") WebElement dateTodayLink;

    @FindBy(css = "a[rel='tomorrow']") WebElement dateTomorrowLink;

    @FindBy(id = "policy_start_date") WebElement policyStartDateTextBox;

    @FindBy(className = "dateHelper") WebElement policyDateHelper;

    @FindBy(id = "submitPageOfEnquiry") WebElement continueButton;

    @FindBy(css = "button.action.submit") WebElement continueButtonBO;

    @FindBy(css = "input.validate-date.datePickerStartDate") WebElement boDatePicker;

    @FindBy(css = "#more_than_2000_2") WebElement personalValuablesNoAnswer;

    @FindBy(id = "submitPageOfEnquiry") WebElement nextPageButton;


    public final BuildingsCoverSection buildingsCoverSection;

    public final ContentsCoverSection contentsCoverSection;

    public CoverOptionsPage(WebDriver driver)
    {
        super(driver);
        buildingsCoverSection = PageFactory.initElements(driver, BuildingsCoverSection.class);
        contentsCoverSection = PageFactory.initElements(driver, ContentsCoverSection.class);
    }

    public boolean isTypeOfHomeInsuranceRadioButton()
    {
        try
        {
            return contentsOnlyRadioButton.isDisplayed() &&
                   buildingsContentsRadioButton.isDisplayed() &&
                   buildingsContentsRadioButton.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean isPolicyStartDateDisplayed()
    {
        try
        {
            return policyStartDateTextBox.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void enterCoverDetails(CoverDetails coverDetails) throws Exception
    {
        pause(2000);
        try
        {
            selectDate();
        }
        catch (Exception e)
        {
            LOG.info("Was not possible to select start date. Could be in renewal state.", e);
        }
        if (coverDetails.getCoverType().equals("Buildings only"))
        {
            clickBuildingsOnly();
            enterBuildingsCoverDetails(coverDetails);
        }
        else if (coverDetails.getCoverType().equals("Contents only"))
        {
            clickContentsOnly();
            enterContentsCoverDetails(coverDetails);
        }
        else if (coverDetails.getCoverType().equals("Buildings & Contents"))
        {
            clickBuildingsAndContents();
            enterBuildingsCoverDetails(coverDetails);
            enterContentsCoverDetails(coverDetails);
        }
    }

    public void continueToNextPage()
    {
        waitForElementPresent(5, "button.action.submit");
        pause(3500);
        jsClick(continueButtonBO);
        waitForElementPresent(10, LOADING_BOX_LOCATOR);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    public void continueToNextRenewalPage()
    {
        WebElement nextPageButton = driver.findElement(By.className("continue"));
        jsClick(nextPageButton);
        waitForElementNotVisible(20, LOADING_BOX_LOCATOR);
    }

    public void clickContentsOnly()
    {
        jsClick(contentsOnlyRadioButton);
        waitForElementVisible(3, "#cover_contents");
        pause(1000); // need this because of strange behaviour of input box
    }

    public void clickBuildingsOnly()
    {
        jsClick(buildingsOnlyRadioButton);
        waitForElementVisible(3, "#cover_buildings");
        pause(1000); // need this because of strange behaviour of input box
    }

    void clickBuildingsAndContents()
    {
        jsClick(buildingsContentsRadioButton);
        waitForElementVisible(3, "#cover_contents");
        waitForElementVisible(3, "#cover_buildings");
        pause(1000); // need this because of strange behaviour of input box
    }

    void selectDate()
    {
        DatePickerQuestionSet datePicker = PageFactory.initElements(driver, DatePickerQuestionSet.class);
        datePicker.clickOnStartDateInputBox();
        datePicker.selectToday();
    }

    private void enterContentsCoverDetails(final CoverDetails coverDetails) throws Exception
    {
        contentsCoverSection.enterContentsValue(coverDetails.getTotalContentsValue());
        contentsCoverSection.selectAccidentalDamageCover(coverDetails.isContentsAccidentalCoverRequired());
        contentsCoverSection.selectEnoughToCover(coverDetails.isHighValueItemsCover());
        if (!coverDetails.isHighValueItemsCover())
        {
            contentsCoverSection.selectValuableTotalCover(coverDetails.getTotalHighValueItemCover());
        }

        boolean hasPersonalItems = coverDetails.getPersonalItemList().size() > 0;
        contentsCoverSection.selectPersonalValuesMoreThan2000(hasPersonalItems);
        if (hasPersonalItems)
        {
            enterPersonalItemInformation(coverDetails.getPersonalItemList());
        }

        boolean hasBicycles = coverDetails.getBicycleList().size() > 0;
        contentsCoverSection.selectBicycleCover(hasBicycles);
        if (hasBicycles)
        {
            enterBicycleInformation(coverDetails.getBicycleList());
        }

        boolean hasUnspecifiedItemsCover = !coverDetails.getUnspecifiedItemsValue().equals("0");
        contentsCoverSection.selectUnspecifiedItemsCover(hasUnspecifiedItemsCover);
        if (hasUnspecifiedItemsCover)
        {
            contentsCoverSection.selectUnspecifiedItemsCoverValue(coverDetails.getUnspecifiedItemsValue());
        }
    }

    private void enterBicycleInformation(final List<Item> bicycleInfoList)
    {
        int derp = 0;

        for (int i = 1; i <= bicycleInfoList.size(); i++)
        {
            NewBicycle bicycle;
            if (i > 1)
            {
                bicycle = contentsCoverSection.addNewBicycle();
            }
            else
            {
                bicycle = contentsCoverSection.bicycleList.get(0);
            }
            Item bicycleInfo = bicycleInfoList.get(i - 1);
            bicycle.enterBicycleDescription(bicycleInfo.getDescription());
            bicycle.enterBicycleValue(bicycleInfo.getValue());
            bicycle.saveBicycle();

            derp = i;
        }

        List<WebElement> addBicycleButtons = driver.findElements(By.className("addition"));
        jsClick(addBicycleButtons.get(addBicycleButtons.size() - 1));
        pause(2500);
        WebElement bicycleElem = driver.findElement(By.id("pedal_cycle" + (derp + 1)));
        WebElement button = bicycleElem.findElement(By.className("link"));
        jsClick(button);
    }

    private void enterPersonalItemInformation(final List<Item> personalItemList) throws Exception
    {
        for (int i = 1; i <= personalItemList.size(); i++)
        {
            NewPersonalItem personalItem;
            if (i > 1)
            {
                personalItem = contentsCoverSection.addNewPersonalItem();
            }
            else
            {
                personalItem = contentsCoverSection.personalItemList.get(0);
            }
            Item personalItemInfo = personalItemList.get(i - 1);
            personalItem.selectSpecifiedItem(personalItemInfo.getType());
            personalItem.enterSpecifiedItemDescription(personalItemInfo.getDescription());
            personalItem.enterSpecifiedItemValue(personalItemInfo.getValue());
            personalItem.selectSpecifiedItemsCoverType(personalItemInfo.getItemsCoveredLocation());
            personalItem.savePersonalItem();
        }
    }

    private void enterBuildingsCoverDetails(final CoverDetails coverDetails)
    {
        buildingsCoverSection.enterPropertyMarketValue(coverDetails.getMarketValue());
        buildingsCoverSection.enterPropertRebuildValue(coverDetails.getRebuildCost());
        buildingsCoverSection.selectIncludeAccidentalDamageCover(coverDetails.isBuildingsAccidentalCoverRequired());
    }

    public void waitForSubmitButtonNotVisible()
    {
        waitForElementNotVisible(10, "button.action.submit");
    }

    public void clickPersonalValueablesNoAnswer()
    {
        jsClick(personalValuablesNoAnswer);
    }

    public void continueToQuotesPanelWebsite()
    {
        nextPageButton = driver.findElement(By.id("submitPageOfEnquiry"));
        jsClick(nextPageButton);
    }

    public void enterCoverDetailsIPT(final CoverDetails coverDetails, String startDate) throws Exception
    {
        try
        {
            selectDate("today", startDate);
        }
        catch (Exception e)
        {
            LOG.info("Was not possible to select start date. Could be in renewal state.", e);
        }
        pause(5000);
        if (coverDetails.getCoverType().equals("Buildings only"))
        {
            clickBuildingsOnly();
            enterBuildingsCoverDetails(coverDetails);
        }
        else if (coverDetails.getCoverType().equals("Contents only"))
        {
            clickContentsOnly();
            enterContentsCoverDetails(coverDetails);
        }
        else if (coverDetails.getCoverType().equals("Buildings & Contents"))
        {
            clickBuildingsAndContents();
            enterBuildingsCoverDetails(coverDetails);
            enterContentsCoverDetails(coverDetails);
        }

    }

    void selectDateBackOffice(String prevStartDate,  String startDate) throws Exception
    {
        DatePickerQuestionSet datePicker = PageFactory.initElements(driver, DatePickerQuestionSet.class);
        datePicker.clickOnStartDateInputBoxBackOffice();
        datePicker.selectDate(prevStartDate, startDate);
    }

    public void enterstartDateBackOffice(String prevStartDate, String startDate) throws Exception
    {
        selectDateBackOffice(prevStartDate, startDate);
    }

    void selectDate(String prevStartDate,  String startDate) throws Exception
    {
        DatePickerQuestionSet datePicker = PageFactory.initElements(driver, DatePickerQuestionSet.class);
        datePicker.clickOnStartDateInputBox();
        datePicker.selectDate(prevStartDate, startDate);
    }

    public void enterstartDate(String prevStartDate, String startDate) throws Exception
    {
        selectDate(prevStartDate, startDate);
    }
}
