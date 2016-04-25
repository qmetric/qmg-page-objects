package com.qmetric.domain;

import com.qmetric.utilities.Generator;

public enum RandomType
{
    RANDOM_FIRSTNAME("<randomFirstName>"),
    RANDOM_LASTNAME("<randomLastName>"),
    RANDOM_EMAIL("<randomEmail>"),
    RANDOM_WORKFLOW_EMAIL("<randomWFEmail>"),
    RANDOM_DOB("<randomDob>"),
    RANDOM_PHONENO("<randomPhoneNo>"),
    RANDOM_GENDER("<randomGender>"),
    RANDOM_TITLE("<randomTitle>");

    private String randomType;

    RandomType(String randomType)
    {
        this.randomType = randomType;
    }

    public static RandomType getType(String randomType) {
        for (RandomType type : RandomType.values()) {
            if (randomType.equals(type.getType())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown type " + randomType);
    }

    public String getType() {
        return randomType;
    }

    public String generate()
    {
        switch(this)
        {
            case RANDOM_EMAIL:
                return Generator.randomEmail();
            case RANDOM_WORKFLOW_EMAIL:
                return Generator.randomEmail().replace("@qmetric.co.uk", "@webdriverinsure.com");
            case RANDOM_FIRSTNAME:
                return Generator.randomFirstName();
            case RANDOM_LASTNAME:
                return Generator.randomLastName();
            case RANDOM_DOB:
                return Generator.randomDateOfBirth();
            case RANDOM_PHONENO:
                return Generator.randomPhoneNumber();
            case RANDOM_GENDER:
                return Generator.randomGender();
            case RANDOM_TITLE:
                return Generator.randomTitle();
            default:
                return "";
        }
    }
}
