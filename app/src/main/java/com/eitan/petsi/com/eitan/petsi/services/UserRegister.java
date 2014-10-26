package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 24/10/2014.
 */
public class UserRegister implements Callback<RegisterResponse>{

    private UserRegisterRespond userRegisterRespond;
    private String userName;
    private String password;
    private String name;

    public UserRegister(String userName, String password,String name, UserRegisterRespond userRegisterRespond){
        this.userRegisterRespond = userRegisterRespond;
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public void registerUser(){
        PetsiRestClient.get().insertUser(userName,password,this);
    }


    @Override
    public void success(RegisterResponse registerResponse, Response response) {

//        if (loginResponse == null){
//            userRegisterRespond.onLoginFailed();
//        }else{
//            userRegisterRespond.onLoginSuccess(loginResponse);
//        }
        userRegisterRespond.onRegisterSuccess(registerResponse);
    }


    @Override
    public void failure(RetrofitError error) {
        userRegisterRespond.onRestCallError(error);
    }
}
