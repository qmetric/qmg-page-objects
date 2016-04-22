package com.qmetric.pageobjects.enquiry_forms.legacy;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public class PropertyDetails extends BasePropertyDetails {

    private boolean propertyStatements;
    private String alarmType;

    public boolean isPropertyStatements() {
        return propertyStatements;
    }

    public void setPropertyStatements(boolean propertyStatements) {
        this.propertyStatements = propertyStatements;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }
}
