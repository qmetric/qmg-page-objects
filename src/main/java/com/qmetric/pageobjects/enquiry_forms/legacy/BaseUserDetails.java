package com.qmetric.pageobjects.enquiry_forms.legacy;

import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: jmartins
 * Date: 24/05/2013
 */

public abstract class BaseUserDetails {

    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private DateTime dateOfBirth;
    private String maritalStatus;
    private String occupation;
    private boolean smoker;
    private String telephoneNumber;
    private String businessType;
    private BaseUserDetails jointPolicyHolder;

    protected BaseUserDetails() {
    }

    public BaseUserDetails getJointPolicyHolder() {
        return jointPolicyHolder;
    }

    public void setJointPolicyHolder(BaseUserDetails jointPolicyHolder) {
        this.jointPolicyHolder = jointPolicyHolder;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
