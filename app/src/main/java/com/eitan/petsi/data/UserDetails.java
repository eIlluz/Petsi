package com.eitan.petsi.data;

/**
 * Created by eitan on 02/11/2014.
 */
public class UserDetails {

    //"lastName":"Mezamer","password":"123456","mailAddress":"mezamern@gmail.com","birthDate":"Jul 8, 1989","address":"Ramat Gan"}
    private String name;
    private String lastName;
    private String mailAddress;
    private String Address;

    public UserDetails(String name, String lastName, String mailAddress, String address) {
        this.name = name;
        this.lastName = lastName;
        this.mailAddress = mailAddress;
        Address = address;
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
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
