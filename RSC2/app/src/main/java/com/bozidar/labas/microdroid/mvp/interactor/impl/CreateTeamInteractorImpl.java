package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.CreateTeamInteractor;
import com.bozidar.labas.microdroid.mvp.listener.OnTeamCreatedListener;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.network.ServiceFactory;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class CreateTeamInteractorImpl implements CreateTeamInteractor, Callback<Response<String>> {

    OnTeamCreatedListener listener;

    @Override
    public void createTeam(String name, String token, OnTeamCreatedListener listener) {
        Log.d("sdkfjds", token);
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(token);
        api.createTeam(tokenFormat, name, this);
    }

    @Override
    public void success(Response<String> stringResponse, retrofit.client.Response response) {
        Log.d("succcess", "success");
        String newToken = TokenManager.getTokenFromHeader(response);
        Log.d("tokencickREIRAJ", newToken);
        listener.teamCreated(newToken);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());
    }
}
