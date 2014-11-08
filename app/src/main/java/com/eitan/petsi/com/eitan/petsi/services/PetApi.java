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


    @GET("/addUser")
    void addUser(@Query("id") String id,@Query("pass") String pass, @Query("name") String name , Callback<PostActionResponse> callback);

    //Query ads
    @GET("/getAd")
    void getAd(@QueryMap Map<String, String> params,
               Callback<List<AdResponseItem>> callback);

    //Get user details
    @GET("/getUser")
    void getUser(@QueryMap Map<String, String> params, Callback<UserDetails> callback);

    @GET("/updateUser")
    void updateUser(@QueryMap Map<String, String> params,Callback<PostActionResponse> callback);

    @GET("/addAd")
    void addAd(@Query("desc") String desc,
               @Query("size") String size,
               @Query("type") String type,
               @Query("user") String user,
               @Query("petName") String petName,
               @Query("story") String story,
               @Query("photoURL") String photoURL,
               @Query("gender") String gender,
               @Query("age") String age,
               Callback<PostActionResponse> callback);

    @GET("/deleteAd")
    void deleteAd(@Query("adID") String adID, Callback<PostActionResponse> callback);

    @GET("/getLikes")
    void getLikes(@QueryMap Map<String, String> params,Callback<List<FavRespond>> callback);
}
