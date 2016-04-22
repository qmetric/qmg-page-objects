package com.qmetric.pageobjects.enquiry_forms.legacy;

public class ClaimsDataForm {
    private String columnTitles;
    private String perils;
    private String subPerils;
    private String causeCode;

    public String getColumnTitles() {
        return columnTitles;
    }

    public String getPerils() {
        return perils;
    }

    public String getSubPerils() {
        return subPerils;
    }

    public void setColumnTitles(String columnTitles) {
        this.columnTitles = columnTitles;
    }

    public void setPerils(String perils) {
        this.perils = perils;
    }

    public void setSubPerils(String subPerils) {
        this.subPerils = subPerils;
    }

    public void setCauseCode(String causeCode)
    {
        this.causeCode = causeCode;
    }

    public String getCauseCode()
    {
        return causeCode;
    }
}
