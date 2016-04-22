package com.qmetric.pageobjects.legacy.question_set.about_your_property;

import com.google.common.collect.Maps;
import com.qmetric.pageobjects.BasePageObject;
import com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement.BOStatementsAgreementBox;
import com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement.StatementsAgreementModalBox;
import com.qmetric.pageobjects.legacy.question_set.about_your_property.statementsagreement.StatementsSummary;
import com.qmetric.domain.PropertyType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 08/03/2013 Time: 10:58
 */

public class AboutYourPropertySection extends BasePageObject
{

    @FindBy(id = "addressSearch") WebElement addressSearchButton;

    @FindBy(id = "postcodeErrorMsg") WebElement postcodeErrorMsgBox;

    @FindBy(id = "property_postcode") WebElement propertyPostcodeTextBox;

    @FindBy(className = "nobranch") WebElement addressDropDown;

    @FindBy(id = "your_address") WebElement yourAddressLabel;

    @FindBy(id = "editAddress") WebElement editAddress;

    @FindBy(id = "address_line_1") WebElement addressLineOneTextBox;

    @FindBy(id = "address_line_2") WebElement addressLineTwoTextBox;

    @FindBy(id = "address_line_3") WebElement addressLineThreeTextBox;

    @FindBy(id = "address_line_4") WebElement addressLineFourTextBox;

    @FindBy(id = "address_town") WebElement addressTownTextBox;

    @FindBy(id = "your_address") WebElement yourAddress;

    @FindBy(id = "questionproperty_type") WebElement propertyTypeQuestionContainer;

    @FindBy(className = "property_type_1") WebElement propertyTypeHouseLabel;

    @FindBy(className = "property_type_2") WebElement propertyTypeFlatLabel;

    @FindBy(className = "property_type_3") WebElement propertyTypeBungalowLabel;

    @FindBy(className = "property_type_4") WebElement propertyTypeOtherLabel;

    @FindBy(id = "property_type_house") WebElement propertyTypeHouseDescriptionDropDown;

    @FindBy(id = "property_type_flat_apartment") WebElement propertyTypeFlatDescriptionDropDown;

    @FindBy(id = "property_type_bungalow") WebElement propertyTypeBungalowDescriptionDropDown;

    @FindBy(id = "property_type_other") WebElement propertyTypeOtherDescriptionDropDown;

    @FindBy(id = "age_of_property") WebElement yearBuiltTextBox;

    @FindBy(id = "number_of_bedrooms") WebElement noOfBedroomsDropDown;

    @FindBy(id = "property_ownership") WebElement propertyOwnershipDropDown;

    @FindBy(id = "property_occupied_by") WebElement occupiedByDropDown;

    @FindBy(id = "number_of_lodgers_or_guests") WebElement numberOfGuestsDropDown;

    @FindBy(id = "lodgers_minimum_stay") WebElement lodgersMinimumStayDropDown;

    @FindBy(id = "source_of_lodgers") WebElement sourceOfLodgersDropDown;

    @FindBy(id = "frequency_property_occupied") WebElement occupiedWhenDropDown;

    @FindBy(id = "months_unoccupied_in_total") WebElement monthsUnoccupiedDropDown;

    @FindBy(id = "months_unoccupied_to_date") WebElement getMonthsUnoccupiedToDateDropDown;

    @FindBy(id = "questionheating_drained_property_checkedLabel") WebElement heatingCheckBox;

    @FindBy(id = "heating_drained_property_checked_1") WebElement heatingdrainedYes;

    @FindBy(id = "heating_drained_property_checked_2") WebElement heatingdrainedNo;

    @FindBy(id = "unoccupancy_reason") WebElement unnoccupancyReasonDropDown;

    @FindBy(id = "questionunoccupied_more_than_1_yearLabel") WebElement unoccupiedMoreThan1YearRadioButtonLabel;

    @FindBy(id = "unoccupied_more_than_1_year_1") WebElement unoccupiedMoreThan1YearRadioButtonYes;

    @FindBy(id = "unoccupied_more_than_1_year_2") WebElement unoccupiedMoreThan1YearRadioButtonNo;

    @FindBy(id = "questionmore_than_10_flats") WebElement moreThan10Flats;

    @FindBy(id = "questionmore_than_4_floors") WebElement moreThan4Floors;

    @FindBy(id = "trees_within_5m_1") WebElement treesYesRadioButton;

    @FindBy(id = "trees_within_5m_2") WebElement treesNoRadioButton;

    @FindBy(id = "flat_roof_no_1") WebElement flatRoofYesRadioButton;

    @FindBy(id = "flat_roof_no_2") WebElement flatRoofNoRadioButton;

    @FindBy(id = "flat_roof") WebElement flatRoofPercentageDropDown;

    @FindBy(id = "property_statements_1") WebElement propertyStatementsYesRadioButton;

    @FindBy(id = "property_statements_2") WebElement propertyStatementsNoRadioButton;

    @FindBy(id = "more_than_10_flats_1") WebElement moreThan10FlatsYes;

    @FindBy(id = "more_than_10_flats_2") WebElement moreThan10FlatsNo;

    @FindBy(id = "more_than_4_floors_1") WebElement moreThan4FloorsYes;

    @FindBy(id = "more_than_4_floors_2") WebElement moreThan4FloorsNo;

    @FindBy(id = "roof_type_majority_1") WebElement roofMajorityYes;

    @FindBy(id = "roof_type_majority_2") WebElement roofMajorityNo;

    /**
     * **************************************************************************************************************
     */

    private Map<WebElement, Map.Entry<WebElement, WebElement>> propertyTypeMap;

    public StatementsAgreementModalBox statementsAgreementModalBox;

    public final StatementsSummary statementsSummary;

    public AboutYourPropertySection(WebDriver driver)
    {
        super(driver);
        statementsSummary = PageFactory.initElements(driver, StatementsSummary.class);
    }

    public void initialisePropertyTypeMap()
    {
        waitForElementVisible(60, propertyTypeQuestionContainer);

        propertyTypeMap = new HashMap<WebElement, Map.Entry<WebElement, WebElement>>();

        Map.Entry<WebElement, WebElement> propertyDescriptionMapEntry =
                Maps.immutableEntry(propertyTypeQuestionContainer.findElement(By.cssSelector("label.radio.property_type_1.label-1")), propertyTypeHouseDescriptionDropDown);
        propertyTypeMap.put(propertyTypeHouseLabel, propertyDescriptionMapEntry);
        propertyDescriptionMapEntry =
                Maps.immutableEntry(propertyTypeQuestionContainer.findElement(By.cssSelector("label.radio.property_type_2.label-2")), propertyTypeFlatDescriptionDropDown);
        propertyTypeMap.put(propertyTypeFlatLabel, propertyDescriptionMapEntry);
        propertyDescriptionMapEntry =
                Maps.immutableEntry(propertyTypeQuestionContainer.findElement(By.cssSelector("label.radio.property_type_3.label-3")), propertyTypeBungalowDescriptionDropDown);
        propertyTypeMap.put(propertyTypeBungalowLabel, propertyDescriptionMapEntry);
        propertyDescriptionMapEntry =
                Maps.immutableEntry(propertyTypeQuestionContainer.findElement(By.cssSelector("label.radio.property_type_4.label-4")), propertyTypeOtherDescriptionDropDown);
        propertyTypeMap.put(propertyTypeOtherLabel, propertyDescriptionMapEntry);
    }

    public void inputPostCodeAndClickFindAddress(String postCode)
    {
        enterTextInput(propertyPostcodeTextBox, postCode);
        jsClick(addressSearchButton);
        pause(2000);
    }

    /**
     * Inputs postCode and selects the first address from the list
     *
     * @param postCode
     */
    public void inputAddress(String postCode)
    {
        inputPostCodeAndClickFindAddress(postCode);
        if (!doesWebElementExist(By.id("your_address")))
        {
            if (isAddressListDisplayed())
            {
                selectAddressFromList(1);
            }
        }
    }

    /**
     * Waits for address list is displayed
     *
     * @return - false if address list is not displayed after 5 seconds
     */
    public boolean isAddressListDisplayed()
    {
        try
        {
            waitForElementVisible(5, addressDropDown);
            return true;
        }
        catch (TimeoutException e)
        {
            return false;
        }
    }

    public void waitForAddressListNotDisplayed()
    {
        waitForElementNotVisible(5, addressDropDown);
    }

    /**
     * Selects address from address list by index starting in 0
     *
     * @param addressIndex
     */
    public void selectAddressFromList(int addressIndex)
    {
        selectDropDownValueByIndex(addressDropDown, addressIndex);
    }

    public void clickEditAddress()
    {
        waitForElementPresent(5, "#editAddress");
        jsClick(editAddress);
    }

    public boolean areEditAddressInputBoxesDisplayed()
    {
        return addressLineOneTextBox.isDisplayed() &&
               addressLineTwoTextBox.isDisplayed() &&
               addressLineThreeTextBox.isDisplayed() &&
               addressLineFourTextBox.isDisplayed() &&
               addressTownTextBox.isDisplayed() &&
               propertyPostcodeTextBox.isDisplayed();
    }

    public void selectPropertyType(String propertyType)
    {
        for (WebElement propertyLabel : propertyTypeMap.keySet())
        {
            if (propertyType.equals(propertyLabel.getText()))
            {
                Map.Entry<WebElement, WebElement> propertyDescriptionMapEntry = propertyTypeMap.get(propertyLabel);
                jsClick(propertyDescriptionMapEntry.getKey());
            }
        }
    }

    public void selectPropertyDescription(String propertyType, String propertyDescription) throws Exception
    {
        for (WebElement propertyLabel : propertyTypeMap.keySet())
        {
            if (propertyType.equals(propertyLabel.getText()))
            {
                Map.Entry<WebElement, WebElement> propertyDescriptionMapEntry = propertyTypeMap.get(propertyLabel);
                final WebElement propertyTypeDescriptionDropDown = propertyDescriptionMapEntry.getValue();
                waitForElementVisible(10, propertyTypeDescriptionDropDown);
                selectDropDownValueByVisibleText(propertyTypeDescriptionDropDown, propertyDescription);
            }
        }
    }

    public List<String> getPropertyDescriptionDropdownElementsText(final PropertyType propertyType)
    {
        if (propertyType.equals(PropertyType.HOUSE))
        {
            return getDropdownElementsText(propertyTypeHouseDescriptionDropDown);
        }
        else if (propertyType.equals(PropertyType.FLAT))
        {
            return getDropdownElementsText(propertyTypeFlatDescriptionDropDown);
        }
        else if (propertyType.equals(PropertyType.BUNGALOW))
        {
            return getDropdownElementsText(propertyTypeBungalowDescriptionDropDown);
        }
        else if (propertyType.equals(PropertyType.OTHER))
        {
            return getDropdownElementsText(propertyTypeOtherDescriptionDropDown);
        }
        else
        {
            return Collections.emptyList();
        }
    }

    public boolean isMoreThan10FlatsRadioButtonDisplayed()
    {
        try
        {
            return moreThan10Flats.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public boolean isMoreThan4FloorsRadioButtonDisplayed()
    {
        try
        {
            return moreThan4Floors.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void inputPropertyBuiltYear(String yearBuilt)
    {
        enterTextInput(yearBuiltTextBox, yearBuilt);
    }

    public boolean isQuestionPropertyAgeDisplayed()
    {
        try
        {
            return yearBuiltTextBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void selectNumberOfBedrooms(String numberOfBedrooms) throws Exception
    {
        selectDropDownValueByVisibleText(noOfBedroomsDropDown, numberOfBedrooms);
    }

    public List<String> getNumberOfBedroomsDropDownElementsText()
    {
        return getDropdownElementsText(noOfBedroomsDropDown);
    }

    public List<String> getPropertyOwnerDropDownElementsText()
    {
        return getDropdownElementsText(propertyOwnershipDropDown);
    }

    public void selectPropertyOwnership(String owner) throws Exception
    {
        selectDropDownValueByVisibleText(propertyOwnershipDropDown, owner);
    }

    public List<String> getPropertyOccupiedByDropDownElementsText()
    {
        return getDropdownElementsText(occupiedByDropDown);
    }

    public void selectOccupiedBy(String occupiedBy) throws Exception
    {
        selectDropDownValueByVisibleText(occupiedByDropDown, occupiedBy);
    }

    public List<String> getNumberOfGuestsDropDownElementsText()
    {
        return getDropdownElementsText(numberOfGuestsDropDown);
    }

    public List<String> getLodgersMinimumStayDropDownElementsText()
    {
        return getDropdownElementsText(lodgersMinimumStayDropDown);
    }

    public List<String> getLodgersSourceDropDownElementsText()
    {
        return getDropdownElementsText(sourceOfLodgersDropDown);
    }

    public void selectOccupiedWhen(String occupiedWhen) throws Exception
    {
        selectDropDownValueByVisibleText(occupiedWhenDropDown, occupiedWhen);
    }

    public List<String> getPropertyOccupiedWhenDropDownElementsText()
    {
        return getDropdownElementsText(occupiedWhenDropDown);
    }

    public void selectMonthsUnOccupiedToDate(String monthsUnOccupied) throws Exception
    {
        selectDropDownValueByVisibleText(getMonthsUnoccupiedToDateDropDown, monthsUnOccupied);
    }

    public int getSizeOfMonthsUnoccupiedToDateDropDown()
    {
        return getMonthsUnoccupiedToDateDropDown.findElements(By.cssSelector("option")).size();
    }

    public void selectMonthsUnOccupiedInTotal(String monthsUnOccupied) throws Exception
    {
        selectDropDownValueByVisibleText(monthsUnoccupiedDropDown, monthsUnOccupied);
    }

    public List<String> getMonthsUnOccupiedInTotalDropDownElementsText()
    {
        return getDropdownElementsText(monthsUnoccupiedDropDown);
    }

    public void setHeatingRadioButton(final boolean heatingRadioButton)
    {
        if(heatingRadioButton)
        {
            jsClick(heatingdrainedYes);
        }
        else
        {
            jsClick(heatingdrainedNo);
        }
    }

    public boolean isHeatingRadioButtonDisplayed()
    {
        try
        {
            return heatingCheckBox.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void selectUnOccupancyReason(final String unoccupancyReason) throws Exception
    {
        selectDropDownValueByVisibleText(unnoccupancyReasonDropDown, unoccupancyReason);
    }

    public List<String> getUnOccupancyReasonDropDownElementsText()
    {
        return getDropdownElementsText(unnoccupancyReasonDropDown);
    }

    public void selectUnOccupiedMoreThan1Year(boolean UnOccupiedMoreThan1Year)
    {
        if(UnOccupiedMoreThan1Year)
        {
            jsClick(unoccupiedMoreThan1YearRadioButtonYes);
        }
        else
        {
            jsClick(unoccupiedMoreThan1YearRadioButtonNo);
        }
    }

    public boolean isUnoccupiedMoreThan1YearRadioButtonDisplayed()
    {
        try
        {
            return unoccupiedMoreThan1YearRadioButtonLabel.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }


    public boolean isTreesTallerThan10MetersRadioButtonDisplayed()
    {
        try
        {
            return treesNoRadioButton.isDisplayed() && treesYesRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public void selectTreesOption(boolean tallerThanTenMetres)
    {
        if (tallerThanTenMetres)
        {
            jsClick(treesYesRadioButton);
        }
        else
        {
            jsClick(treesNoRadioButton);
        }
    }

    public boolean isFlatRoofRadioButtonDisplayed()
    {
        try
        {
            return flatRoofNoRadioButton.isDisplayed() && flatRoofYesRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    public List<String> getFlatRoofPercentageDropDownElementsText()
    {
        return getDropdownElementsText(flatRoofPercentageDropDown);
    }

    public void selectFlatRoofOption(boolean flatRoof)
    {
        if (flatRoof)
        {
            jsClick(flatRoofYesRadioButton);
        }
        else
        {
            jsClick(flatRoofNoRadioButton);
        }
    }

    public void selectFlatRoofPercentage(String flatRoofPercentage) throws Exception
    {
        selectDropDownValueByVisibleText(flatRoofPercentageDropDown, flatRoofPercentage);
    }

    public boolean isAgreementStatementsRadioButtonDisplayed()
    {
        try
        {
            return propertyStatementsNoRadioButton.isDisplayed() && propertyStatementsYesRadioButton.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    /**
     * If the value of yes is false, it will initialize StatementsAgreementModalBox
     *
     * @param yes
     */
    public void selectStatementsOption(boolean yes)
    {
        if (yes)
        {
            jsClick(propertyStatementsYesRadioButton);
        }
        else
        {
            jsClick(propertyStatementsNoRadioButton);
            statementsAgreementModalBox = PageFactory.initElements(driver, BOStatementsAgreementBox.class);
        }
    }

    public void select10FlatsWithinBuilding(boolean yes)
    {
        if (yes)
        {
            jsClick(moreThan10FlatsYes);
        }
        else
        {
            jsClick(moreThan10FlatsNo);
        }
    }

    public void select4FloorsWithinBuilding(boolean yes)
    {
        if (yes)
        {
            jsClick(moreThan4FloorsYes);
        }
        else
        {
            jsClick(moreThan4FloorsNo);
        }
    }

    public void selectIsRoofMajority(boolean yes)
    {
        if (yes)
        {
            jsClick(roofMajorityYes);
        }
        else
        {
            jsClick(roofMajorityNo);
        }
    }

    public void inputPostCode(String postCode)
    {
        enterTextInput(propertyPostcodeTextBox, postCode);
        if(postCode.equals("CF31 3PA"))
        {
            enterTextInput(addressLineOneTextBox, "58 Bowham Avenue");
        }
        else if(postCode.equals("FK3 9AR"))
        {
            enterTextInput(addressLineOneTextBox, "24 Kings Road");
        }
        else if(postCode.equals("BT48 8EW"))
        {
            enterTextInput(addressLineOneTextBox, "26 Daisy Hill Park");
        }
        else if(postCode.equals("L10 6LL"))
        {
            enterTextInput(addressLineOneTextBox, "309 Oriel Drive");
        }
        else if(postCode.equals("CT13 0AS"))
        {
            enterTextInput(addressLineOneTextBox, "320 St Barts Road");
        }
        else if(postCode.equals("GL2 5FJ"))
        {
            enterTextInput(addressLineOneTextBox, "4 Towpath Road");
        }
        else if(postCode.equals("CH4 8TS"))
        {
            enterTextInput(addressLineOneTextBox, "54 Park Avenue");
        }
    }
}
