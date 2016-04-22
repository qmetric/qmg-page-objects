package com.qmetric.pageobjects.legacy;

import java.util.Random;

/**
 * Created with IntelliJ IDEA. User: jmartins Date: 03/02/15
 */
public class UserForm
{

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String title;

    private String cid;

    private String temporaryPassword;

    public UserForm()
    {
        this.firstName = "TestFirstName";
        this.lastName = "TestLastName";
        this.email = "qa-test-user+" + (int)(new Random().nextDouble() * 1000000000) + "@com.qmetric.co.uk";
        this.password = "Password5";
        this.title = "Mr";
        this.phoneNumber = "1234567890";
    }

    public UserForm(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getTemporaryPassword()
    {
        return this.temporaryPassword;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setTemporaryPassword(String temporaryPassword)
    {
        this.temporaryPassword = temporaryPassword;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCid()
    {
        return this.cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }
}
