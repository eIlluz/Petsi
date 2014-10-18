package com.eitan.petsi.data;

/**
 * Created by eitan on 25/07/2014.
 */
public class PetDetails {

    private String name;
    private String Gender;
    private int age;
    private String type;
    private String otherInfo;
    private String story;
    private String photoUrl;
    private String size;

    public PetDetails(String name, String gender, int age, String type, String otherInfo, String story, String photoUrl, String size) {
        this.name = name;
        Gender = gender;
        this.age = age;
        this.type = type;
        this.otherInfo = otherInfo;
        this.story = story;
        this.photoUrl = photoUrl;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }
}
