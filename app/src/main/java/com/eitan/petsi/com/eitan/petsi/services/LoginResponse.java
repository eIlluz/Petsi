package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 24/10/2014.
 */
public class LoginResponse {

    private String name;
    private String mailAddress;

    public LoginResponse(String name, String mailAddress) {
        this.name = name;
        this.mailAddress = mailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
