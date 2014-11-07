package com.eitan.petsi.com.eitan.petsi.services;

import java.util.Date;

/**
 * Created by eitan on 07/11/2014.
 */
public class UserDetails {
    private String name;
    private String lastName;
    private String mailAddress;
    private String address;
    private String phoneNumber;

    public UserDetails(String name, String lastName, String mailAddress, String address, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
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
