package com.eitan.petsi.com.eitan.petsi.services;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by eitan on 07/11/2014.
 */
public class GetUserDetailsTask implements Callback<UserDetails> {

    private String id;
    private UserDetailsRespond userDetailsRespond;

    public GetUserDetailsTask(String id, UserDetailsRespond userDetailsRespond) {
        this.id = id;
        this.userDetailsRespond = userDetailsRespond;
    }

    public UserDetailsRespond getUserDetailsRespond() {
        return userDetailsRespond;
    }

    public void setUserDetailsRespond(UserDetailsRespond userDetailsRespond) {
        this.userDetailsRespond = userDetailsRespond;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void getUserDetails(){

        Map<String,String> fields = new HashMap<String, String>();

        fields.put("id",id);

        PetsiRestClient.get().getUser(fields, this);
    }

    @Override
    public void success(UserDetails userDetails, Response response) {
        if (userDetails != null)
            userDetailsRespond.onGetDetailsSuccess(userDetails);
        else
            userDetailsRespond.onGetDetailsFail();
    }

    @Override
    public void failure(RetrofitError error) {

        userDetailsRespond.onRestCallError(error);
    }
}
