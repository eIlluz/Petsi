package com.eitan.petsi.com.eitan.petsi.services;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by eitan on 24/10/2014.
 */

/* Singleton class to get the rest api to call any method. */
public class PetsiRestClient {

    private static PetApi REST_CLIENT;
    private static String ROOT =
            "http://54.69.124.99:8080/Service/main/";

    static {
        setupRestClient();
    }

    private PetsiRestClient(){};

    public static PetApi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT)
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(PetApi.class);
    }
}
