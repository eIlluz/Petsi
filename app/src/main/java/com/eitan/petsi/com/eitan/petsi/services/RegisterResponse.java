package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 24/10/2014.
 */
public class RegisterResponse {

    private String text;
    private boolean success;

    public RegisterResponse(String text, boolean success) {
        this.text = text;
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
