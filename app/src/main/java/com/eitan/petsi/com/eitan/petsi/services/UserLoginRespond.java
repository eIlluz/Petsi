package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 24/10/2014.
 */
public interface UserLoginRespond {

    public void onLoginSuccess(LoginResponse loginResponse);

    public void onLoginFailed();

    public void onRestCallError(RetrofitError error);
}
