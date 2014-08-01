package com.eitan.petsi.com.eitan.petsi.services;

import com.eitan.petsi.App;
import com.eitan.petsi.R;

/**
 * Created by eitan on 26/07/2014.
 */
public class RegisterNewUser {

    private String userName;
    private String password;

    public RegisterNewUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void RegisterUser() throws RegFailedException
    {

        try {
            wait(2000);
        } catch (InterruptedException e) {
            throw new RegFailedException(App.getContext().getString(R.string.error_occurred));
        }

        if (password != "123456")
        {
            throw new RegFailedException(App.getContext().getString(R.string.user_exist_already));
        }
    }
}
