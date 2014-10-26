package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by eitan on 24/10/2014.
 */
public interface PetApi {

    @GET("/getUser")
    void getUser(@Query("id") String id ,@Query("pass") String pass, Callback<LoginResponse> callback);

    @GET("/InsertUser")
    void insertUser(@Query("id") String id ,@Query("pass") String pass, Callback<RegisterResponse> callback);
}
