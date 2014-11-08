package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 07/11/2014.
 */
public class UserDetails {
    private String name;
    private String lastName;
    private String mailAddress;
    private String address;
    private String phoneNum;
    private String birthDate;

    public UserDetails(String name, String lastName, String mailAddress, String address, String phoneNum,String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
        this.address = address;
        this.phoneNum = phoneNum;
        this.birthDate = birthDate;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
