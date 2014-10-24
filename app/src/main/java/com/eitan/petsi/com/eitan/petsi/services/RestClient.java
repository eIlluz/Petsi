package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 24/10/2014.
 */
public class RestClient {
    private static RestClient ourInstance = new RestClient();

    public static RestClient getInstance() {
        return ourInstance;
    }

    private RestClient() {
    }


}
