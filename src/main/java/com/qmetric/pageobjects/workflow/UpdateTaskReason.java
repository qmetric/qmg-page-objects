package com.qmetric.pageobjects.workflow;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 31/07/2014
 */
public enum UpdateTaskReason
{
    RINGS_OUT_NO_VOICE_MAIL("No", "Rings out - no voicemail"),
    VOICE_MAIL_MESSAGE_LEFT("No", "Went to voicemail - message left"),
    VOICE_MAIL_MESSAGE_NOT_LEFT("No", "Went to voicemail - message not left"),
    BUSY_ENGAGED_TONE("No", "Busy / engaged tone"),
    INVALID_CUSTOMER_DETAILS("No", "Invalid customer details"),

    INSURANCE_NOT_DUE_YET("Yes", "Insurance not due yet"),
    UNABLE_TO_PAY_AT_MOMENT("Yes", "Unable to pay at the moment"),
    COLLECT_PAYMENT_DETAILS("Yes", "Collect payment details"),
    ADDITIONAL_POLICY_INFORMATION_REQUIRED("Yes", "Additional policy information required"),
    REFER_TO_UNDERWRITER("Yes", "Refer to underwriter"),
    CALL_BACK_REQUESTED("Yes", "Call back Requested"),
    TIME_NEEDED_TO_CONSIDER("Yes", "Time needed to consider"),
    DECISION_MAKER_UNAVAILABLE("Yes", "Decision maker unavailable"),
    WOULD_NOT_DISCUSS_HUNG_UP("Yes", "Wouldn't discuss hung up"),
    CALL_LIMIT_REACHED("Yes", "Call limit reached"),
    UNABLE_TO_COVER("Yes", "Unable to cover"),
    PAST_RENEWAL_DATE("Yes", "Past renewal date"),
    PURCHASED_ELSEWHERE("Yes", "Purchased elsewhere"),
    ALREADY_BOUGHT_WITH_US_ONLINE("Yes", "Already bought with us online"),
    WANTS_TO_BUY_ONLINE("Yes", "Wants to buy online"),
    OTHER("Yes", "Other"),
    DO_NOT_CONTACT("Yes", "Do not contact");

    private String contact;

    private String reason;

    UpdateTaskReason(String contact, String reason)
    {
        this.contact = contact;
        this.reason = reason;
    }

    public String getContact()
    {
        return contact;
    }

    public String getReason()
    {
        return reason;
    }

    public static UpdateTaskReason fromReason(String reason)
    {
        if (reason.equals("Rings out - no voicemail"))
        {
            return RINGS_OUT_NO_VOICE_MAIL;
        }
        else if (reason.equals("Went to voicemail - message left"))
        {
            return VOICE_MAIL_MESSAGE_LEFT;
        }
        else if (reason.equals("Went to voicemail - message not left"))
        {
            return VOICE_MAIL_MESSAGE_NOT_LEFT;
        }
        else if (reason.equals("Busy / engaged tone"))
        {
            return BUSY_ENGAGED_TONE;
        }
        else if (reason.equals("Insurance not due yet"))
        {
            return INSURANCE_NOT_DUE_YET;
        }
        else if (reason.equals("Unable to pay at the moment"))
        {
            return UNABLE_TO_PAY_AT_MOMENT;
        }
        else if (reason.equals("Collect payment details"))
        {
            return COLLECT_PAYMENT_DETAILS;
        }
        else if (reason.equals("Additional policy information required"))
        {
            return ADDITIONAL_POLICY_INFORMATION_REQUIRED;
        }
        else if (reason.equals("Refer to underwriter"))
        {
            return REFER_TO_UNDERWRITER;
        }
        else if (reason.equals("Call Back Requested"))
        {
            return CALL_BACK_REQUESTED;
        }
        else if (reason.equals("Time needed to consider"))
        {
            return TIME_NEEDED_TO_CONSIDER;
        }
        else if (reason.equals("Decision maker unavailable"))
        {
            return DECISION_MAKER_UNAVAILABLE;
        }
        else if (reason.equals("Wouldn't discuss hung up"))
        {
            return WOULD_NOT_DISCUSS_HUNG_UP;
        }
        else if (reason.equals("Invalid customer details"))
        {
            return INVALID_CUSTOMER_DETAILS;
        }
        else if (reason.equals("Call limit reached"))
        {
            return CALL_LIMIT_REACHED;
        }
        else if (reason.equals("Unable to cover"))
        {
            return UNABLE_TO_COVER;
        }
        else if (reason.equals("Past renewal date"))
        {
            return PAST_RENEWAL_DATE;
        }
        else if (reason.equals("Purchased elsewhere"))
        {
            return PURCHASED_ELSEWHERE;
        }
        else if (reason.equals("Already bought with us online"))
        {
            return ALREADY_BOUGHT_WITH_US_ONLINE;
        }
        else if (reason.equals("Wants to buy online"))
        {
            return WANTS_TO_BUY_ONLINE;
        }
        else if (reason.equals("Other"))
        {
            return OTHER;
        }
        else if (reason.equals("Do not contact"))
        {
            return DO_NOT_CONTACT;
        }
        else
        {
            return null;
        }
    }
}
