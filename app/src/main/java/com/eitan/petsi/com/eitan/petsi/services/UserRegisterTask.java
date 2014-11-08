package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 24/10/2014.
 */
public class UserRegisterTask implements Callback<PostActionResponse>{

    private UserRegisterRespond userRegisterRespond;
    private String userName;
    private String password;
    private String name;

    public UserRegisterTask(String userName, String password, String name, UserRegisterRespond userRegisterRespond){
        this.userRegisterRespond = userRegisterRespond;
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public void registerUser(){
        PetsiRestClient.get().addUser(userName,password,name,this);
    }


    @Override
    public void success(PostActionResponse PostActionResponse, Response response) {

        if (PostActionResponse.isSuccess()){
            userRegisterRespond.onRegisterSuccess();
        }else{
            userRegisterRespond.onRegisterFailed();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        userRegisterRespond.onRestCallError(error);
    }
}
