package com.eitan.petsi.com.eitan.petsi.services;

import retrofit.RetrofitError;

/**
 * Created by eitan on 08/11/2014.
 */
public interface UpdateUserListener {

    public void onUpdateSuccess(PostActionResponse PostActionResponse);

    public void onUpdateFailed(PostActionResponse PostActionResponse);

    public void onRestCallError(RetrofitError error);
}
