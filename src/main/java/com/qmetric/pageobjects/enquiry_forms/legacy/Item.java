package com.qmetric.pageobjects.enquiry_forms.legacy;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 28/05/2013
 */

public class Item {

    private String type;
    private String description;
    private String value;
    private String itemsCoveredLocation;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getItemsCoveredLocation() {
        return itemsCoveredLocation;
    }

    public void setItemsCoveredLocation(String itemsCoveredLocation) {
        this.itemsCoveredLocation = itemsCoveredLocation;
    }
}
