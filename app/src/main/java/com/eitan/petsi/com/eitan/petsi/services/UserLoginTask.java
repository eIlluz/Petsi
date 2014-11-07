package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 24/10/2014.
 */
public class UserLoginTask implements Callback<AuthResponse>{

    private UserLoginRespond userLoginRespond;
    private String userName;
    private String password;

    public UserLoginTask(String userName, String password, UserLoginRespond userLoginRespond){
        this.userLoginRespond = userLoginRespond;
        this.userName = userName;
        this.password = password;
    }

    public void getLogin(){
        PetsiRestClient.get().authUser(userName,password,this);
    }


    @Override
    public void success(AuthResponse authResponse, Response response) {

        if (!authResponse.isSuccess()){
            userLoginRespond.onLoginFailed();
        }else{
            userLoginRespond.onLoginSuccess(authResponse);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        userLoginRespond.onRestCallError(error);
    }
}
