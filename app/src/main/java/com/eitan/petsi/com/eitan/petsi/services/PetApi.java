package com.eitan.petsi.com.eitan.petsi.services;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Created by eitan on 24/10/2014.
 */
public interface PetApi {

    //Insert new user
    @GET("/InsertUser")
    void insertUser(@Query("id") String id ,@Query("pass") String pass, Callback<PostActionResponse> callback);


    //Query ads
    @GET("/getAd")
    void getAd(@QueryMap Map<String, String> params,
               Callback<List<AdResponseItem>> callback);

    //Authenticate user
    @GET("/authUser")
    void authUser(@Query("id") String id ,@Query("pass") String pass, Callback<AuthResponse> callback);

    //Get user details
    @GET("/getUserDetails")
    void getUserDetails(@Query("id") String id, Callback<UserDetails> callback);

    @GET("/updateUser")
    void updateUser(@QueryMap Map<String, String> params,Callback<PostActionResponse> insertRespond);
}
