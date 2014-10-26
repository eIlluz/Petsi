package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 24/10/2014.
 */
public interface UserRegisterRespond {

    public void onRegisterSuccess(RegisterResponse registerResponse);

    public void onRegisterFailed();

    public void onRestCallError(RetrofitError error);
}
