package com.qmetric.pageobjects.enquiry_forms.legacy;

import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 28/05/2013
 */

public class Claim {

    private String value;
    private String type;
    private String cause;
    private String month;
    private String year;
    private DateTime dateOfIncident;
    private String wasClaimMade;
    private boolean hasBeenSettled;
    private boolean atProperty;

    //floods
    private String causeOfFlood;
    private String floodDepth;

    public Claim() {
        this.causeOfFlood = "Flood";
        this.floodDepth = "5";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isHasBeenSettled() {
        return hasBeenSettled;
    }

    public void setHasBeenSettled(boolean hasBeenSettled) {
        this.hasBeenSettled = hasBeenSettled;
    }

    public DateTime getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(DateTime dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }

    public String getWasClaimMade() {
        return wasClaimMade;
    }

    public void setWasClaimMade(String wasClaimMade) {
        this.wasClaimMade = wasClaimMade;
    }

    public String getCauseOfFlood() {
        return causeOfFlood;
    }

    public void setCauseOfFlood(String causeOfFlood) {
        this.causeOfFlood = causeOfFlood;
    }

    public String getFloodDepth() {
        return floodDepth;
    }

    public void setFloodDepth(String floodDepth) {
        this.floodDepth = floodDepth;
    }

    public boolean isAtProperty() {
        return atProperty;
    }

    public void setAtProperty(boolean atProperty) {
        this.atProperty = atProperty;
    }
}
