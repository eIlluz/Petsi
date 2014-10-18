package com.eitan.petsi.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by eitan on 25/07/2014.
 */
public class AdData {


    private String adID;
    private String userName;
    private Calendar lastUpdate;
    private Calendar createdOn;
    private String adStatus;
    private int numOfLikes;

    public AdData(String adID, String userName, Calendar lastUpdate, Calendar createdOn, String adStatus, int numOfLikes) {
        this.adID = adID;
        this.userName = userName;
        this.lastUpdate = lastUpdate;
        this.createdOn = createdOn;
        this.adStatus = adStatus;
        this.numOfLikes = numOfLikes;
    }

    public String getAdID() {
        return adID;
    }

    public void setAdID(String adID) {
        this.adID = adID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Calendar getCreatedOn() {return createdOn; }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }
}
