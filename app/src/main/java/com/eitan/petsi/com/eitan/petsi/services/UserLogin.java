package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 24/10/2014.
 */
public class UserLogin implements Callback<LoginResponse>{

    private UserLoginRespond userLoginRespond;
    private String userName;
    private String password;

    public UserLogin(String userName, String password,UserLoginRespond userLoginRespond){
        this.userLoginRespond = userLoginRespond;
        this.userName = userName;
        this.password = password;
    }

    public void getLogin(){
        PetsiRestClient.get().getUser(userName,password,this);
    }


    @Override
    public void success(LoginResponse loginResponse, Response response) {
        if (loginResponse == null){
            userLoginRespond.onLoginFailed();
        }else{
            userLoginRespond.onLoginSuccess(loginResponse);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        userLoginRespond.onRestCallError(error);
    }
}
