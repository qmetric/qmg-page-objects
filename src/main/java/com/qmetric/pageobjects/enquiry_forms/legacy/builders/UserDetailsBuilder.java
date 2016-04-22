package com.qmetric.pageobjects.enquiry_forms.legacy.builders;

import com.qmetric.pageobjects.enquiry_forms.legacy.UserDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 28/05/2013
 */

public class UserDetailsBuilder implements Builder<UserDetails> {

    private String title;
    private String firstName = "auto";
    private String lastName = RandomStringUtils.random(10) + "test";
    private String email = RandomStringUtils.random(15) + "@coverinsure.co.uk";
    private String password = "Password5";
    private DateTime dateOfBirth;
    private String maritalStatus;
    private String occupation;
    private boolean smoker;
    private String telephoneNumber = "01234567890";
    private String businessType;
    private UserDetails jointPolicyHolder;

    private String secondaryPhoneNumber = "";
    private boolean newsletterOptIn = false;
    private boolean userStatements = true;

    @Override
    public UserDetails build() {
        UserDetails userDetails = new UserDetails();
        userDetails.setTitle(this.title);
        userDetails.setFirstName(this.firstName);
        userDetails.setLastName(this.lastName);
        userDetails.setEmail(this.email);
        userDetails.setPassword(this.password);
        userDetails.setDateOfBirth(this.dateOfBirth);
        userDetails.setMaritalStatus(this.maritalStatus);
        userDetails.setOccupation(this.occupation);
        userDetails.setSmoker(this.smoker);
        userDetails.setTelephoneNumber(this.telephoneNumber);
        userDetails.setBusinessType(this.businessType);
        userDetails.setJointPolicyHolder(this.jointPolicyHolder);
        userDetails.setSecondaryPhoneNumber(this.secondaryPhoneNumber);
        userDetails.setNewsletterOptIn(this.newsletterOptIn);
        userDetails.setUserStatements(this.userStatements);
        return userDetails;
    }

    public UserDetailsBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserDetailsBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDetailsBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDetailsBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDetailsBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDetailsBuilder withDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDetailsBuilder withMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public UserDetailsBuilder withOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public UserDetailsBuilder withSmoker(boolean smoker) {
        this.smoker = smoker;
        return this;
    }

    public UserDetailsBuilder withTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public UserDetailsBuilder withBusinessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public UserDetailsBuilder withJointPolicyHolder(UserDetails jointPolicyHolder) {
        this.jointPolicyHolder = jointPolicyHolder;
        return this;
    }

    public UserDetailsBuilder withSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        return this;
    }

    public UserDetailsBuilder withNewsletterOptIn(boolean newsletterOptIn) {
        this.newsletterOptIn = newsletterOptIn;
        return this;
    }

    public UserDetailsBuilder withUserStatements(boolean userStatements) {
        this.userStatements = userStatements;
        return this;
    }

    public UserDetailsBuilder withDefaultValues() {
        this.dateOfBirth = new DateTime()
                .withDayOfMonth(10)
                .withMonthOfYear(7)
                .withYear(1980);
        this.maritalStatus = "Married";
        this.occupation = "Accountant";
        this.smoker = false;
        this.userStatements = true;
        this.jointPolicyHolder = null;
        return this;
    }

}
