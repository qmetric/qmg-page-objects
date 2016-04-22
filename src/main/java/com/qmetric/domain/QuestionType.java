package com.qmetric.domain;

public enum QuestionType {
    TEXT,
    YESNO,
    SELECT,
    IMAGESELECT,
    RADIO,
    NUMBER,
    DATE,
    DATENOWORFUTURE,
    DATEOFBIRTH,
    DATEMONTHANDYEAR,
    NEWDATE,
    COLLECTION,
    CURRENCY,
    ADDRESS,
    EMAIL,
    VEHICLE,
    OCCUPATION,
    BUILDINGTYPE,
    LEGACYPOLICYLOOKUP,
    ABICODE,
    ROSSERLOOKUP,
    REBUILDPRICE,
    CUSTOMERLOGIN,;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
