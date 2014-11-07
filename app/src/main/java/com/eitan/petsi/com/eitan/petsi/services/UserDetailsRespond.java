package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 07/11/2014.
 */
public interface UserDetailsRespond {

    public void onGetDetailsSuccess(UserDetails userDetails);

    public void onGetDetailsFail();

    public void onRestCallError(RetrofitError error);
}
