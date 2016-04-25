package com.qmetric.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator
{
    private static Random random = new Random();

    public static String randomEmail()
    {
        double randomDouble = random.nextDouble();
        return "qa-test-user+" + (int)(randomDouble * 1000000000) + "@qmetric.co.uk";
    }

    public static String randomFirstName()
    {
        String[] firstNames = {"Alex", "Duncan", "Victoria", "Huw", "Jose", "Oliver", "Andy", "Alessandro",
                               "Tristan", "Dom", "Bert", "Matt", "Samantha", "Ephraim", "Simon", "Christine", "Jason",
                               "Fran", "Davide", "Grace", "Beth", "Ryan", "Rick", "Adam", "Cristian", "Ismael",
                               "David", "Jonathan", "Daniel", "Wayne", "Michael", "Peter", "Andrea", "Marcin", "Patrycja"};
        return firstNames[random.nextInt(firstNames.length)];
    }

    public static String randomLastName()
    {
        String[] lastNames = {"Spurling", "Smaldon", "Hall", "Farr", "McKelvey", "Burns", "Powell", "Perez", "Mecca",
                               "Martins", "Green", "Logan", "Woodhouse", "Scott", "Cameron", "Rollett", "Buckley", "Yung",
                               "Lewis", "Crotty", "Bearpark", "Lye", "Fryer", "Hansen", "Wilton", "Carlesso", "Minetou",
                               "Ruckwood", "Prior", "Wood", "Bunyan", "Wadsworth", "Molin", "Rivera", "Gigante", "Kazala", "Jurkowski"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    public static String randomPhoneNumber()
    {
        double randomDouble = random.nextDouble();
        return String.format("07%08d", (int)(randomDouble * 1000000000));
    }

    public static String randomTitle()
    {
        String[] titles = {"Mr", "Mrs"};
        return titles[random.nextInt(titles.length)];
    }

    public static String randomDateOfBirth()
    {
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int year = 1960 + random.nextInt(25);
        String prefixedMonth;
        if(month < 10)
        {
            prefixedMonth = "0" + month;
        }
        else
        {
            prefixedMonth = month + "";
        }
        String prefixedDay;
        if(day < 10)
        {
            prefixedDay = "0" + day;
        }
        else
        {
            prefixedDay = day + "";
        }
        return year + "-" + prefixedMonth + "-" + prefixedDay;
    }

    public static String randomGender()
    {
        return random.nextInt(2) == 1 ? "Male" : "Female";
    }

    public static String randomCreditCardNumber()
    {
        List<String> cardList =  new ArrayList<>();
        cardList.add("4929671538589522");
        cardList.add("4532595473331419");
        cardList.add("4485261564566242");
        cardList.add("4556551513408205");
        cardList.add("4024007116894519");
        cardList.add("5235123561708583");
        cardList.add("5593631759933330");
        cardList.add("5136576053534371");
        cardList.add("5384009939274904");
        cardList.add("5357591370380681");

        int randomIndex = random.nextInt(cardList.size());
        return cardList.get(randomIndex);
    }
}
