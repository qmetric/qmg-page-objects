package com.qmetric.pageobjects.enquiry_forms.legacy.builders;

import com.qmetric.pageobjects.enquiry_forms.legacy.PropertyDetails;
import org.apache.commons.lang3.builder.Builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 28/05/2013
 */

public class PropertyDetailsBuilder implements Builder<PropertyDetails> {

    private String postCode;
    private String propertyType;
    private String description;
    private String moreThan10Floors;
    private String moreThan4Floors;
    private String yearBuilt;
    private String numberOfBedrooms;
    private String ownershipStatus;
    private String occupiedBy;
    private String occupiedWhen;
    private boolean tallTrees;
    private boolean flatRoof;
    private String flatRoofPercentage;
    private String businessUse;
    private boolean propertyStatements;
    private String propertySubStatement;

    private boolean hasLocksOnWindows;
    private boolean hasMainDoorLock;
    private String mainDoorLockDescription;
    private boolean hasPatioDoorsLock;
    private String patioDoorsLockDescription;
    private boolean hasOtherExternalDoorsLock;
    private String otherExternalDoorsLockDescription;
    private boolean hasAlarm;
    private boolean isVerifiedAlarm;
    private String alarmType;
    private String floodDistanceFormSource;
    private String heightOfPropertyFromSource;
    private String floodSource;
    private boolean isPropertyAffectedByFlood;
    private String monthsUnOccupiedInTotal;
    private String monthsUnOccupiedToDate;
    private String unoccupancyReason;
    private boolean heatingDrained;
    private boolean propertyUnOccupiedMoreThan1Year;
    private String subsidenceVisible;
    private boolean propertyUnderPinned;
    private String subsidenceValue;
    private boolean subsidenceComplete;
    private boolean isSubsidenceSupportUnderpinned;

    public static final Map<String, String> propertyMapping = new HashMap<String, String>();

    static {
        propertyMapping.put("Detached", "Detached house");
        propertyMapping.put("Mid-terrace", "Mid terrace");
        propertyMapping.put("First floor or above - purpose built", "Flat: First floor or above - purpose built");
        propertyMapping.put("Ground floor or basement - purpose built", "Flat: Ground floor or basement - purpose built");
        propertyMapping.put("Ground floor or basement - converted", "Flat: Ground floor or basement - converted");
        propertyMapping.put("First floor or above - converted", "Flat: First floor or above - converted");
    }

    @Override
    public PropertyDetails build() {
        PropertyDetails propertyDetails = new PropertyDetails();
        propertyDetails.setPostCode(this.postCode);
        propertyDetails.setPropertyType(this.propertyType);
        propertyDetails.setDescription(this.description);
        propertyDetails.setMoreThan4Floors(this.moreThan4Floors);
        propertyDetails.setMoreThan10Flats(this.moreThan10Floors);
        propertyDetails.setYearBuilt(this.yearBuilt);
        propertyDetails.setNumberOfBedrooms(this.numberOfBedrooms);
        propertyDetails.setOwnershipStatus(this.ownershipStatus);
        propertyDetails.setOccupiedBy(this.occupiedBy);
        propertyDetails.setOccupiedWhen(this.occupiedWhen);
        propertyDetails.setTallTrees(this.tallTrees);
        propertyDetails.setFlatRoof(this.flatRoof);
        propertyDetails.setFlatRoofPercentage(this.flatRoofPercentage);
        propertyDetails.setBusinessUse(this.businessUse);
        propertyDetails.setPropertyStatements(this.propertyStatements);
        propertyDetails.setHasLocksOnWindows(this.hasLocksOnWindows);
        propertyDetails.setHasMainDoorLock(this.hasMainDoorLock);
        propertyDetails.setMainDoorLockDescription(this.mainDoorLockDescription);
        propertyDetails.setHasPatioDoorsLock(this.hasPatioDoorsLock);
        propertyDetails.setPatioDoorsLockDescription(this.patioDoorsLockDescription);
        propertyDetails.setHasOtherExternalDoorsLock(this.hasOtherExternalDoorsLock);
        propertyDetails.setOtherExternalDoorsLockDescription(this.otherExternalDoorsLockDescription);
        propertyDetails.setHasAlarm(this.hasAlarm);
        propertyDetails.setVerifiedAlarm(this.isVerifiedAlarm);
        propertyDetails.setAlarmType(this.alarmType);
        propertyDetails.setPropertySubStatement(this.propertySubStatement);
        propertyDetails.setPropertyAffectedByFlood(this.isPropertyAffectedByFlood);
        propertyDetails.setFloodDistanceFormSource(this.floodDistanceFormSource);
        propertyDetails.setHeightOfPropertyFromSource(this.heightOfPropertyFromSource);
        propertyDetails.setFloodSource(this.floodSource);
        propertyDetails.setMonthsUnOccupiedInTotal(this.monthsUnOccupiedInTotal);
        propertyDetails.setMonthsUnOccupiedToDate(this.monthsUnOccupiedToDate);
        propertyDetails.setUnOccupancyReason(this.unoccupancyReason);
        propertyDetails.setPropertyUnOccupiedMoreThan1Year(this.propertyUnOccupiedMoreThan1Year);
        propertyDetails.setHeatingDrained(this.heatingDrained);
        propertyDetails.setSubsidenceSupportUnderpined(this.isSubsidenceSupportUnderpinned);
        propertyDetails.setSubsidenceVisible(this.subsidenceVisible);
        propertyDetails.setPropertyUnderPinned(this.propertyUnderPinned);
        propertyDetails.setSubsidenceValue(this.subsidenceValue);
        propertyDetails.setSubsidenceComplete(this.subsidenceComplete);
        return propertyDetails;
    }


    public PropertyDetailsBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public PropertyDetailsBuilder withPropertyType(String propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public PropertyDetailsBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PropertyDetailsBuilder withYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
        return this;
    }

    public PropertyDetailsBuilder withNumberOfBedrooms(String numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
        return this;
    }

    public PropertyDetailsBuilder withOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
        return this;
    }

    public PropertyDetailsBuilder withOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
        return this;
    }

    public PropertyDetailsBuilder withOccupiedWhen(String occupiedWhen) {
        this.occupiedWhen = occupiedWhen;
        return this;
    }

    public PropertyDetailsBuilder withTallTrees(boolean tallTrees) {
        this.tallTrees = tallTrees;
        return this;
    }

    public PropertyDetailsBuilder withFlatRoof(boolean flatRoof) {
        this.flatRoof = flatRoof;
        return this;
    }

    public PropertyDetailsBuilder withFlatRoofPercentage(String flatRoofPercentage) {
        this.flatRoofPercentage = flatRoofPercentage;
        return this;
    }

    public PropertyDetailsBuilder withBusinessUse(String businessUse) {
        this.businessUse = businessUse;
        return this;
    }

    public PropertyDetailsBuilder withHasLocksOnWindows(boolean hasLocksOnWindows) {
        this.hasLocksOnWindows = hasLocksOnWindows;
        return this;
    }

    public PropertyDetailsBuilder withHasMainDoorLock(boolean hasMainDoorLock) {
        this.hasMainDoorLock = hasMainDoorLock;
        return this;
    }

    public PropertyDetailsBuilder withMainDoorLockDescription(String mainDoorLockDescription) {
        this.mainDoorLockDescription = mainDoorLockDescription;
        return this;
    }

    public PropertyDetailsBuilder withHasPatioDoorsLock(boolean hasPatioDoorsLock) {
        this.hasPatioDoorsLock = hasPatioDoorsLock;
        return this;
    }

    public PropertyDetailsBuilder withPatioDoorsLockDescription(String patioDoorsLockDescription) {
        this.patioDoorsLockDescription = patioDoorsLockDescription;
        return this;
    }

    public PropertyDetailsBuilder withHasOtherExternalDoorsLock(boolean hasOtherExternalDoorsLock) {
        this.hasOtherExternalDoorsLock = hasOtherExternalDoorsLock;
        return this;
    }

    public PropertyDetailsBuilder withOtherExternalDoorsLockDescription(String otherExternalDoorsLockDescription) {
        this.otherExternalDoorsLockDescription = otherExternalDoorsLockDescription;
        return this;
    }

    public PropertyDetailsBuilder withHasAlarm(boolean hasAlarm) {
        this.hasAlarm = hasAlarm;
        return this;
    }

    public PropertyDetailsBuilder withVerifiedAlarm(boolean verifiedAlarm) {
        isVerifiedAlarm = verifiedAlarm;
        return this;
    }

    public PropertyDetailsBuilder withPropertyStatements(boolean propertyStatements) {
        this.propertyStatements = propertyStatements;
        return this;
    }

    public PropertyDetailsBuilder withAlarmType(String alarmType) {
        this.alarmType = alarmType;
        return this;
    }

    public PropertyDetailsBuilder withMoreThan10Flats(String moreThan10Flats)
    {
        this.moreThan10Floors = moreThan10Flats;
        return this;
    }

    public PropertyDetailsBuilder withMoreThan4Floors(String moreThan4Floors)
    {
        this.moreThan4Floors = moreThan4Floors;
        return this;
    }

    public PropertyDetailsBuilder withIsPropertyAffectedByFlood(boolean isPropertyAffectedByFlood) {
        this.isPropertyAffectedByFlood = isPropertyAffectedByFlood;
        return this;
    }

    public PropertyDetailsBuilder withFloodSource(String floodSource) {
        this.floodSource = floodSource;
        return this;
    }

    public PropertyDetailsBuilder withFloodDistanceFromSource(String floodDistanceFormSource) {
        this.floodDistanceFormSource = floodDistanceFormSource;
        return this;
    }

    public PropertyDetailsBuilder withHeightOfPropertyFromFlood(String heightOfPropertyFromSource) {
        this.heightOfPropertyFromSource = heightOfPropertyFromSource;
        return this;
    }

    public PropertyDetailsBuilder withPropertySubStatements(String propertySubStatement)
    {
        this.propertySubStatement = propertySubStatement;
        return this;
    }

    public PropertyDetailsBuilder withMonthsUnOccupiedInTotal(String monthsUnOccupiedInTotal)
    {
        this.monthsUnOccupiedInTotal = monthsUnOccupiedInTotal;
        return this;
    }

    public PropertyDetailsBuilder withMonthsUnOccupiedToDate(String monthsUnOccupiedToDate)
    {
        this.monthsUnOccupiedToDate = monthsUnOccupiedToDate;
        return this;
    }

    public PropertyDetailsBuilder withUnOccupancyReason(final String unoccupancyReason)
    {
        this.unoccupancyReason = unoccupancyReason;
        return this;
    }

    public PropertyDetailsBuilder withHeatingDrained(boolean heatingDrained)
    {
        this.heatingDrained = heatingDrained;
        return this;
    }

    public PropertyDetailsBuilder withPropertyUnOccupiedMoreThan1Year(boolean propertyUnOccupiedMoreThan1Year)
    {
        this.propertyUnOccupiedMoreThan1Year = propertyUnOccupiedMoreThan1Year;
        return this;
    }

    public PropertyDetailsBuilder withSubsidenceVisible(String subsidenceVisible)
    {
        this.subsidenceVisible = subsidenceVisible;
        return this;
    }

    public PropertyDetailsBuilder withPropertyUnderPinned(boolean propertyUnderPinned)
    {
        this.propertyUnderPinned = propertyUnderPinned;
        return this;
    }

    public PropertyDetailsBuilder withSubsidenceValue(String subsidenceValue)
    {
        this.subsidenceValue = subsidenceValue;
        return this;
    }

    public PropertyDetailsBuilder withSubsidenceComplete(boolean subsidenceComplete)
    {
        this.subsidenceComplete = subsidenceComplete;
        return this;
    }

    public PropertyDetailsBuilder withIsSubsidenceSupportUnderpinned(boolean isSubsidenceSupportUnderpinned)
    {
        this.isSubsidenceSupportUnderpinned = isSubsidenceSupportUnderpinned;
        return this;
    }
    public PropertyDetailsBuilder withDefaultValues() {
        this.postCode = "WA9 2EJ";
        this.propertyType = "House";
        this.description = "Detached house";
        this.yearBuilt = "1980";
        this.numberOfBedrooms = "2";
        this.ownershipStatus = "Yes - mortgaged";
        this.occupiedBy = "You as sole occupier";
        this.occupiedWhen = "During the day";
        this.tallTrees = false;
        this.flatRoof = false;
        this.propertyStatements = true;
        this.hasLocksOnWindows = true;
        this.hasMainDoorLock = true;
        this.mainDoorLockDescription = "5 lever mortice deadlock conforming to BS3621";
        this.hasPatioDoorsLock = false;
        this.hasOtherExternalDoorsLock = true;
        this.otherExternalDoorsLockDescription = "5 lever mortice deadlock conforming to BS3621";
        this.hasAlarm = false;
        return this;
    }
}
