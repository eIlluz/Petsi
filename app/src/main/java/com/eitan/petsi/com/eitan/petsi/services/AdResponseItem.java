package com.eitan.petsi.com.eitan.petsi.services;

import java.util.Date;

/**
 * Created by eitan on 01/11/2014.
 */
public class AdResponseItem {
    private int id;
    private Date createdOn;
    private String status;
    private String description;
    private String story;
    private String photoURL;
    private String size;
    private int NumOfLikes;
    private String type;
    private String user;
    private String petName;
    private String gender;
    private float age;

    public AdResponseItem(int id, Date createdOn, String status, String description, String story, String photoURL, String size, int numOfLikes, String type, String user, String petName, String gender, float age) {
        this.id = id;
        this.createdOn = createdOn;
        this.status = status;
        this.description = description;
        this.story = story;
        this.photoURL = photoURL;
        this.size = size;
        NumOfLikes = numOfLikes;
        this.type = type;
        this.user = user;
        this.petName = petName;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumOfLikes() {
        return NumOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        NumOfLikes = numOfLikes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }
}
