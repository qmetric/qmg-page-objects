package com.qmetric.domain;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 06/03/2013
 */

public enum PropertyType
{
    HOUSE, FLAT, BUNGALOW, TOWN_HOUSE, OTHER;

    @Override
    public String toString()
    {
        //only capitalize the first letter except for flats
        String s = super.toString();
        String ret = s.substring(0, 1) + s.substring(1).toLowerCase();

        if (ret.equals("Flat"))
        {
            ret = "Flat/apartment";
        }
        if (ret.equals("Town_house"))
        {
            ret = "Town house";
        }
        return ret;
    }

}
