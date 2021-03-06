package com.eitan.petsi.data;

/**
 * Created by eitan on 25/07/2014.
 */
public class OwnerDetails {

    private String name;
    private String tel;
    private String address;
    private String email;
    private String birthDate;

    public OwnerDetails(String name, String tel, String address, String email) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.email = email;
    }

    public OwnerDetails(String name, String tel, String address, String email, String birthDate) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
