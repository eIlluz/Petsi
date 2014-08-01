package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 26/07/2014.
 */
public interface RegisterCallBack
{
    public void onRegisterError(String errorMessage);
    public void onRegisterSuccess(String UserName);
}
