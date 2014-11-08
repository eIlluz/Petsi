package com.eitan.petsi.com.eitan.petsi.services;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 24/10/2014.
 */
public class UserLoginTask implements Callback<UserDetails>{

    private UserLoginRespond userLoginRespond;
    private String userName;
    private String password;

    public UserLoginTask(String userName, String password, UserLoginRespond userLoginRespond){
        this.userLoginRespond = userLoginRespond;
        this.userName = userName;
        this.password = password;
    }

    public void getLogin(){
        Map<String,String> fields = new HashMap<String, String>();

        fields.put("id",userName);
        fields.put("pass",password);

        PetsiRestClient.get().getUser(fields, this);
    }


    @Override
    public void success(UserDetails userDetails, Response response) {

        if (userDetails == null){
            userLoginRespond.onLoginFailed();
        }else{
            userLoginRespond.onLoginSuccess();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        userLoginRespond.onRestCallError(error);
    }
}
