package com.qmetric.pageobjects.enquiry_forms.legacy;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public abstract class BasePropertyDetails {

    private String postCode;
    private String propertyType;
    private String description;
    private String yearBuilt;
    private String numberOfBedrooms;
    private String ownershipStatus;
    private String occupiedBy;
    private String occupiedWhen;
    private boolean tallTrees;
    private boolean flatRoof;
    private String flatRoofPercentage;
    private String distanceFromWater;
    private String businessUse;

    private boolean hasLocksOnWindows;
    private boolean hasMainDoorLock;
    private String mainDoorLockDescription;
    private boolean hasPatioDoorsLock;
    private String patioDoorsLockDescription;
    private boolean hasOtherExternalDoorsLock;
    private String otherExternalDoorsLockDescription;
    private boolean hasAlarm;
    private boolean isVerifiedAlarm;
    private String moreThan4Floors;
    private String moreThan10Flats;
    private String propertySubStatement;
    private boolean ispropertyAffectedByFlood;
    private String floodSource;
    private String floodDistanceFormSource;
    private String heightOfPropertyFromSource;
    private String propertyOccupiedWhen;
    private String monthsUnOccupied;
    private String monthsUnOccupiedToDate;
    private String monthsUnOccupiedInTotal;
    private String unOccupancyReason;
    private boolean heatingDrained;
    private boolean propertyUnOccupiedMoreThan1Year;
    private String subsidenceVisible;
    private boolean propertyUnderPinned;
    private String subsidenceValue;
    private boolean subsidenceComplete;
    private boolean subsidenceSupportUnderpined;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoreThan4Floors(final String moreThan4Floors)
    {
        this.moreThan4Floors = moreThan4Floors;
    }

    public void setMoreThan10Flats(final String moreThan10Flats)
    {
        this.moreThan10Flats = moreThan10Flats;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(String numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getOwnershipStatus() {
        return ownershipStatus;
    }

    public void setOwnershipStatus(String ownershipStatus) {
        this.ownershipStatus = ownershipStatus;
    }

    public String getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public String getOccupiedWhen() {
        return occupiedWhen;
    }

    public void setOccupiedWhen(String occupiedWhen) {
        this.occupiedWhen = occupiedWhen;
    }

    public boolean isTallTrees() {
        return tallTrees;
    }

    public void setTallTrees(boolean tallTrees) {
        this.tallTrees = tallTrees;
    }

    public boolean isFlatRoof() {
        return flatRoof;
    }

    public void setFlatRoof(boolean flatRoof) {
        this.flatRoof = flatRoof;
    }

    public String getFlatRoofPercentage() {
        return flatRoofPercentage;
    }

    public void setFlatRoofPercentage(String flatRoofPercentage) {
        this.flatRoofPercentage = flatRoofPercentage;
    }

    public String getBusinessUse() {
        return businessUse;
    }

    public void setBusinessUse(String businessUse) {
        this.businessUse = businessUse;
    }

    public boolean isHasLocksOnWindows() {
        return hasLocksOnWindows;
    }

    public void setHasLocksOnWindows(boolean hasLocksOnWindows) {
        this.hasLocksOnWindows = hasLocksOnWindows;
    }

    public boolean isHasMainDoorLock() {
        return hasMainDoorLock;
    }

    public void setHasMainDoorLock(boolean hasMainDoorLock) {
        this.hasMainDoorLock = hasMainDoorLock;
    }

    public String getMainDoorLockDescription() {
        return mainDoorLockDescription;
    }

    public void setMainDoorLockDescription(String mainDoorLockDescription) {
        this.mainDoorLockDescription = mainDoorLockDescription;
    }

    public boolean isHasPatioDoorsLock() {
        return hasPatioDoorsLock;
    }

    public void setHasPatioDoorsLock(boolean hasPatioDoorsLock) {
        this.hasPatioDoorsLock = hasPatioDoorsLock;
    }

    public String getPatioDoorsLockDescription() {
        return patioDoorsLockDescription;
    }

    public void setPatioDoorsLockDescription(String patioDoorsLockDescription) {
        this.patioDoorsLockDescription = patioDoorsLockDescription;
    }

    public boolean isHasOtherExternalDoorsLock() {
        return hasOtherExternalDoorsLock;
    }

    public void setHasOtherExternalDoorsLock(boolean hasOtherExternalDoorsLock) {
        this.hasOtherExternalDoorsLock = hasOtherExternalDoorsLock;
    }

    public String getOtherExternalDoorsLockDescription() {
        return otherExternalDoorsLockDescription;
    }

    public void setOtherExternalDoorsLockDescription(String otherExternalDoorsLockDescription) {
        this.otherExternalDoorsLockDescription = otherExternalDoorsLockDescription;
    }

    public boolean isHasAlarm() {
        return hasAlarm;
    }

    public void setHasAlarm(boolean hasAlarm) {
        this.hasAlarm = hasAlarm;
    }

    public boolean isVerifiedAlarm() {
        return isVerifiedAlarm;
    }

    public void setVerifiedAlarm(boolean verifiedAlarm) {
        isVerifiedAlarm = verifiedAlarm;
    }

    public String getMoreThan4Floors()
    {
        return moreThan4Floors;
    }

    public String getMoreThan10Flats()
    {
        return moreThan10Flats;
    }

    public String getPropertySubStatement()
    {       
        return propertySubStatement;
    }

    public void setPropertySubStatement(String propertySubStatement)
    {
        this.propertySubStatement = propertySubStatement;
    }

    public boolean isPropertyAffectedByFlood()
    {
        return ispropertyAffectedByFlood;
    }

    public void setPropertyAffectedByFlood(boolean isPropertyAffectedByFlood)
    {
        this.ispropertyAffectedByFlood = ispropertyAffectedByFlood;
    }

    public String getFloodSource()
    {
        return floodSource;
    }

    public void setFloodSource(String floodSource)
    {
        this.floodSource = floodSource;
    }

    public String getFloodDistanceFormSource()
    {
        return floodDistanceFormSource;
    }

    public void setFloodDistanceFormSource(String floodDistanceFormSource)
    {
        this.floodDistanceFormSource = floodDistanceFormSource;
    }

    public String getHeightOfPropertyFromSource()
    {
        return heightOfPropertyFromSource;
    }

    public void setHeightOfPropertyFromSource(String heightOfPropertyFromSource)
    {
        this.heightOfPropertyFromSource = heightOfPropertyFromSource;
    }

    public String getPropertyOccupiedWhen()
    {
        return propertyOccupiedWhen;
    }

    public void setPropertyOccupiedWhen(String propertyOccupiedWhen)
    {
        this.propertyOccupiedWhen = propertyOccupiedWhen;
    }


    public String getMonthsUnOccupiedToDate()
    {
        return monthsUnOccupiedToDate;
    }

    public void setMonthsUnOccupiedToDate(String monthsUnOccupiedToDate)
    {
        this.monthsUnOccupiedToDate = monthsUnOccupiedToDate;
    }

    public String getMonthsUnOccupiedInTotal()
    {
        return monthsUnOccupiedInTotal;
    }

    public void setMonthsUnOccupiedInTotal(String monthsUnOccupiedInTotal)
    {
        this.monthsUnOccupiedInTotal = monthsUnOccupiedInTotal;
    }

    public String getUnOccupancyReason()
    {
        return unOccupancyReason;
    }

    public void setUnOccupancyReason(String unOccupancyReason)
    {
        this.unOccupancyReason = unOccupancyReason;
    }

    public boolean isHeatingDrained()
    {
        return heatingDrained;
    }

    public void setHeatingDrained(boolean heatingDrained)
    {
        this.heatingDrained = heatingDrained;
    }

    public boolean isPropertyUnOccupiedMoreThan1Year()
    {
        return propertyUnOccupiedMoreThan1Year;
    }

    public void setPropertyUnOccupiedMoreThan1Year(boolean propertyUnOccupiedMoreThan1Year)
    {
        this.propertyUnOccupiedMoreThan1Year = propertyUnOccupiedMoreThan1Year;
    }

    public String getSubsidenceVisible()
    {
        return subsidenceVisible;
    }

    public void setSubsidenceVisible(String subsidenceVisible)
    {
        this.subsidenceVisible = subsidenceVisible;
    }

    public boolean getPropertyUnderPinned()
    {
        return propertyUnderPinned;
    }

    public void setPropertyUnderPinned(boolean propertyUnderPinned)
    {
        this.propertyUnderPinned = propertyUnderPinned;
    }

    public String getSubsidenceValue()
    {
        return subsidenceValue;
    }

    public void setSubsidenceValue(final String subsidenceValue)
    {
        this.subsidenceValue = subsidenceValue;
    }

    public boolean getSubsidenceComplete()
    {
        return subsidenceComplete;
    }

    public void setSubsidenceComplete(final boolean subsidenceComplete)
    {
        this.subsidenceComplete = subsidenceComplete;
    }

    public boolean getSubsidenceSupportUnderpined()
    {
        return subsidenceSupportUnderpined;
    }

    public void setSubsidenceSupportUnderpined(final boolean subsidenceSupportUnderpined)
    {
        this.subsidenceSupportUnderpined = subsidenceSupportUnderpined;
    }
}
