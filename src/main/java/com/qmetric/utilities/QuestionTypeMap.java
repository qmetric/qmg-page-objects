package com.qmetric.utilities;

import com.google.common.collect.Maps;
import com.qmetric.domain.QuestionType;

import java.util.Map;

public class QuestionTypeMap {
    private final Map<String, QuestionType> questionTypeMapping;

    public QuestionTypeMap() {
        questionTypeMapping = Maps.newHashMap();
        questionTypeMapping.put("Text", QuestionType.TEXT);
        questionTypeMapping.put("Drop down", QuestionType.SELECT);
        questionTypeMapping.put("Radio", QuestionType.RADIO);
        questionTypeMapping.put("Yes / No", QuestionType.YESNO);
        questionTypeMapping.put("Number", QuestionType.NUMBER);
        questionTypeMapping.put("Currency", QuestionType.CURRENCY);
        questionTypeMapping.put("Email", QuestionType.EMAIL);
        questionTypeMapping.put("Image", QuestionType.IMAGESELECT);
        questionTypeMapping.put("Occupation", QuestionType.OCCUPATION);
        questionTypeMapping.put("Building Type", QuestionType.BUILDINGTYPE);
        questionTypeMapping.put("Collection", QuestionType.COLLECTION);
        questionTypeMapping.put("Vehicle Registration", QuestionType.VEHICLE);
        questionTypeMapping.put("Address", QuestionType.ADDRESS);
        questionTypeMapping.put("Legacy Policy Lookup", QuestionType.LEGACYPOLICYLOOKUP);
        questionTypeMapping.put("Abi Code", QuestionType.ABICODE);
        questionTypeMapping.put("Date (any)", QuestionType.DATE);
        questionTypeMapping.put("Date (now or future)", QuestionType.DATENOWORFUTURE);
        questionTypeMapping.put("Date of birth", QuestionType.DATEOFBIRTH);
        questionTypeMapping.put("Date (month/year)", QuestionType.DATEMONTHANDYEAR);
        questionTypeMapping.put("Date (new)", QuestionType.NEWDATE);
        questionTypeMapping.put("Rosser Lookup", QuestionType.ROSSERLOOKUP);
        questionTypeMapping.put("Rebuild Price Recalculator", QuestionType.REBUILDPRICE);
        questionTypeMapping.put("Customer Login", QuestionType.CUSTOMERLOGIN);
    }

    public QuestionType get(String key) {
        return questionTypeMapping.get(key);
    }

}
