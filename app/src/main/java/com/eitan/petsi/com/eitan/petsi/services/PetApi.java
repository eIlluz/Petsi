package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by eitan on 24/10/2014.
 */
public interface PetApi {

    @GET("/getUser")
    void getUser(@Query("id") String id ,@Query("pass") String pass, Callback<LoginResponse> callback);

    @GET("/InsertUser")
    void insertUser(@Query("id") String id ,@Query("pass") String pass, Callback<RegisterResponse> callback);

    @GET("/getAd")
    void getAd(@Query("age") int age,
               @Query("size") String size,
               @Query("gender") String gender,
               @Query("type") String type,
               @Query("user") String user,
               Callback<AdResponseList> callback);
}
