package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.text.TextUtils;
import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.RegisterInteractor;
import com.bozidar.labas.microdroid.mvp.listener.OnregisterFinishedListener;
import com.bozidar.labas.microdroid.mvp.model.response.RegistrationResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.microdroid.network.ServiceFactory;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by kanta on 23.09.15..
 */
public class RegisterInteractorImpl implements RegisterInteractor, Callback<Response<RegistrationResponse>> {
    private OnregisterFinishedListener listener;


    @Override
    public void register(String password, String email, String firstName, String lastName, OnregisterFinishedListener listener) {
        boolean error = false;
        this.listener = listener;
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
            api.register(firstName + " " + lastName, email, password, "android", this);
        }
    }


    @Override
    public void success(Response<RegistrationResponse> registrationResponseResponse, retrofit.client.Response response) {
        Log.d("success", registrationResponseResponse.getMessage());
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());

    }

}

