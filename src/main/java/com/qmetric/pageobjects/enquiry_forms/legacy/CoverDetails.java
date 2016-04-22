package com.qmetric.pageobjects.enquiry_forms.legacy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public class CoverDetails extends BaseCoverDetails {

    private boolean highValueItemsCover;
    private String totalHighValueItemCover;
    private List<Item> personalItemList;
    private List<Item> bicycleList;
    private String unspecifiedItemsValue;

    public boolean isHighValueItemsCover() {
        return highValueItemsCover;
    }

    public void setHighValueItemsCover(boolean highValueItemsCover) {
        this.highValueItemsCover = highValueItemsCover;
    }

    public String getTotalHighValueItemCover() {
        return totalHighValueItemCover;
    }

    public void setTotalHighValueItemCover(String totalHighValueItemCover) {
        this.totalHighValueItemCover = totalHighValueItemCover;
    }

    public List<Item> getPersonalItemList() {
        return personalItemList;
    }

    public void setPersonalItemList(List<Item> personalItemList) {
        this.personalItemList = personalItemList;
    }

    public List<Item> getBicycleList() {
        return bicycleList;
    }

    public void setBicycleList(List<Item> bicycleList) {
        this.bicycleList = bicycleList;
    }

    public String getUnspecifiedItemsValue() {
        return unspecifiedItemsValue;
    }

    public void setUnspecifiedItemsValue(String unspecifiedItemsValue) {
        this.unspecifiedItemsValue = unspecifiedItemsValue;
    }
}
