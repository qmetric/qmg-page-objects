package com.qmetric.pageobjects.enquiry_forms.legacy.builders;

import com.google.common.collect.Lists;
import com.qmetric.pageobjects.enquiry_forms.legacy.Claim;
import com.qmetric.pageobjects.enquiry_forms.legacy.CoverDetails;
import com.qmetric.pageobjects.enquiry_forms.legacy.Item;
import org.apache.commons.lang3.builder.Builder;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 28/05/2013
 */

public class CoverDetailsBuilder implements Builder<CoverDetails> {

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
    private String totalHighValueItemCover;
    private boolean highValueItemsCover = true;
    private List<Item> personalItemList = Lists.newArrayList();
    private List<Item> bicycleList = Lists.newArrayList();
    private String unspecifiedItemsValue = "0";

    //previous claims
    private List<Claim> previousClaimList = Lists.newArrayList();

    @Override
    public CoverDetails build() {
        CoverDetails coverDetails = new CoverDetails();
        coverDetails.setCoverType(this.coverType);
        coverDetails.setCoverStartDate(this.coverStartDate);
        coverDetails.setYearsNoClaim(this.yearsNoClaim);
        coverDetails.setMarketValue(this.marketValue);
        coverDetails.setVoluntaryExcess(this.voluntaryExcess);
        coverDetails.setRebuildCost(this.rebuildCost);
        coverDetails.setBuildingsAccidentalCoverRequired(this.buildingsAccidentalCoverRequired);
        coverDetails.setTotalContentsValue(this.totalContentsValue);
        coverDetails.setContentsAccidentalCoverRequired(this.contentsAccidentalCoverRequired);
        coverDetails.setTotalHighValueItemCover(this.totalHighValueItemCover);
        coverDetails.setHighValueItemsCover(this.highValueItemsCover);
        coverDetails.setPersonalItemList(this.personalItemList);
        coverDetails.setBicycleList(this.bicycleList);
        coverDetails.setUnspecifiedItemsValue(this.unspecifiedItemsValue);
        coverDetails.setPreviousClaimList(this.previousClaimList);
        return coverDetails;
    }

    public CoverDetailsBuilder withCoverType(String coverType) {
        this.coverType = coverType;
        return this;
    }

    public CoverDetailsBuilder withCoverStartDate(DateTime coverStartDate) {
        this.coverStartDate = coverStartDate;
        return this;
    }

    public CoverDetailsBuilder withYearsNoClaim(String yearsNoClaim) {
        this.yearsNoClaim = yearsNoClaim;
        return this;
    }

    public CoverDetailsBuilder withMarketValue(String marketValue) {
        this.marketValue = marketValue;
        return this;
    }

    public CoverDetailsBuilder withVoluntaryExcess(String voluntaryExcess) {
        this.voluntaryExcess = voluntaryExcess;
        return this;
    }

    public CoverDetailsBuilder withRebuildCost(String rebuildCost) {
        this.rebuildCost = rebuildCost;
        return this;
    }

    public CoverDetailsBuilder withBuildingsAccidentalCoverRequired(boolean buildingsAccidentalCoverRequired) {
        this.buildingsAccidentalCoverRequired = buildingsAccidentalCoverRequired;
        return this;
    }

    public CoverDetailsBuilder withTotalContentsValue(String totalContentsValue) {
        this.totalContentsValue = totalContentsValue;
        return this;
    }

    public CoverDetailsBuilder withContentsAccidentalCoverRequired(boolean contentsAccidentalCoverRequired) {
        this.contentsAccidentalCoverRequired = contentsAccidentalCoverRequired;
        return this;
    }

    public CoverDetailsBuilder withTotalHighValueItemCover(String totalHighValueItemCover) {
        this.totalHighValueItemCover = totalHighValueItemCover;
        return this;
    }

    public CoverDetailsBuilder withHighValueItemsCover(boolean highValueItemsCover) {
        this.highValueItemsCover = highValueItemsCover;
        return this;
    }

    public CoverDetailsBuilder withPersonalItemList(List<Item> peronsalItemList) {
        this.personalItemList = peronsalItemList;
        return this;
    }

    public CoverDetailsBuilder withBicycleList(List<Item> bicycleList) {
        this.bicycleList = bicycleList;
        return this;
    }

    public CoverDetailsBuilder withUnspecifiedItemsValue(String unspecifiedItemsValue) {
        this.unspecifiedItemsValue = unspecifiedItemsValue;
        return this;
    }

    public CoverDetailsBuilder withPreviousClaimList(List<Claim> previousClaimList) {
        this.previousClaimList = previousClaimList;
        return this;
    }

    public CoverDetailsBuilder withDefaultValues() {

        this.coverType = "Buildings & Contents";
        this.coverStartDate = new DateTime();

        this.marketValue = "150000";
        this.rebuildCost = "150000";
        this.buildingsAccidentalCoverRequired = true;

        this.totalContentsValue = "50000";
        this.contentsAccidentalCoverRequired = true;

        this.yearsNoClaim = "5 years";
        Claim claim = new Claim();
        claim.setMonth("January");
        claim.setYear("2011");
        claim.setType("Buildings");
        claim.setCause("Accidental damage");
        claim.setValue("1");
        claim.setHasBeenSettled(true);
        this.previousClaimList.add(claim);

        return this;
    }

}
