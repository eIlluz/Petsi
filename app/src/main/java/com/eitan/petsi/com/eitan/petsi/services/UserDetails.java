package com.eitan.petsi.com.eitan.petsi.services;

import java.util.Date;

/**
 * Created by eitan on 07/11/2014.
 */
public class UserDetails {
    private String name;
    private String lastName;
    private String mailAddress;
    private Date birthDate;
    private String address;
    private String phoneNumber;

    public UserDetails(String name, String lastName, String mailAddress, Date birthDate, String address, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
