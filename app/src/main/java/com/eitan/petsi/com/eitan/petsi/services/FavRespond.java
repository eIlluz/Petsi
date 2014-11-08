package com.eitan.petsi.com.eitan.petsi.services;

/**
 * Created by eitan on 09/11/2014.
 */
public class FavRespond {
    private String user;
    private String adID;

    public FavRespond(String user, String adID) {
        this.user = user;
        this.adID = adID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdID() {
        return adID;
    }

    public void setAdID(String adID) {
        this.adID = adID;
    }
}
