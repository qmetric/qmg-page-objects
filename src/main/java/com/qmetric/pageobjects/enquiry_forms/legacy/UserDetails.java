package com.qmetric.pageobjects.enquiry_forms.legacy;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public class UserDetails extends BaseUserDetails {

    private String secondaryPhoneNumber;
    private boolean newsletterOptIn;
    private boolean userStatements;


    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public boolean isNewsletterOptIn() {
        return newsletterOptIn;
    }

    public void setNewsletterOptIn(boolean newsletterOptIn) {
        this.newsletterOptIn = newsletterOptIn;
    }

    public boolean isUserStatements() {
        return userStatements;
    }

    public void setUserStatements(boolean userStatements) {
        this.userStatements = userStatements;
    }
}
