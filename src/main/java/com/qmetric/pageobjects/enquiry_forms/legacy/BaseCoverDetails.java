package com.qmetric.pageobjects.enquiry_forms.legacy;


import org.joda.time.DateTime;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public abstract class BaseCoverDetails {

    private String coverType;
    private DateTime coverStartDate;
    private String yearsNoClaim;

    //buildings
    private String marketValue;
    private String voluntaryExcess;
    private String rebuildCost;
    private boolean buildingsAccidentalCoverRequired;

    //contents
    private String totalContentsValue;
    private boolean contentsAccidentalCoverRequired;

    //previous claims
    private List<Claim> previousClaimList;

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public DateTime getCoverStartDate() {
        return coverStartDate;
    }

    public void setCoverStartDate(DateTime coverStartDate) {
        this.coverStartDate = coverStartDate;
    }

    public String getYearsNoClaim() {
        return yearsNoClaim;
    }

    public void setYearsNoClaim(String yearsNoClaim) {
        this.yearsNoClaim = yearsNoClaim;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getVoluntaryExcess() {
        return voluntaryExcess;
    }

    public void setVoluntaryExcess(String voluntaryExcess) {
        this.voluntaryExcess = voluntaryExcess;
    }

    public String getRebuildCost() {
        return rebuildCost;
    }

    public void setRebuildCost(String rebuildCost) {
        this.rebuildCost = rebuildCost;
    }

    public boolean isBuildingsAccidentalCoverRequired() {
        return buildingsAccidentalCoverRequired;
    }

    public void setBuildingsAccidentalCoverRequired(boolean buildingsAccidentalCoverRequired) {
        this.buildingsAccidentalCoverRequired = buildingsAccidentalCoverRequired;
    }

    public String getTotalContentsValue() {
        return totalContentsValue;
    }

    public void setTotalContentsValue(String totalContentsValue) {
        this.totalContentsValue = totalContentsValue;
    }

    public boolean isContentsAccidentalCoverRequired() {
        return contentsAccidentalCoverRequired;
    }

    public void setContentsAccidentalCoverRequired(boolean contentsAccidentalCoverRequired) {
        this.contentsAccidentalCoverRequired = contentsAccidentalCoverRequired;
    }

    public List<Claim> getPreviousClaimList() {
        return previousClaimList;
    }

    public void setPreviousClaimList(List<Claim> previousClaimList) {
        this.previousClaimList = previousClaimList;
    }
}
