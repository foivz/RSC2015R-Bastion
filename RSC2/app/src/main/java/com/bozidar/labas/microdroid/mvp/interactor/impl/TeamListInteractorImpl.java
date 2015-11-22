package com.bozidar.labas.microdroid.mvp.interactor.impl;

import android.util.Log;

import com.bozidar.labas.microdroid.mvp.interactor.TeamListInteractor;
import com.bozidar.labas.microdroid.mvp.listener.TeamListListener;
import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.network.ServiceFactory;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by macbook on 18.10.2015..
 */
public class TeamListInteractorImpl implements TeamListInteractor, Callback<Response<List<CreatedTeamModel>>>{

    TeamListListener listener;

    @Override
    public void loadTeamList(TeamListListener listener, String token) {
        this.listener = listener;
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(token);
        api.fetchCreatedTeams(tokenFormat, this);
    }

    @Override
    public void success(Response<List<CreatedTeamModel>> listResponse, retrofit.client.Response response) {
        String newToken = TokenManager.getTokenFromHeader(response);
        Log.d("tokencicLista", newToken);
      //  Log.d("ispis", listResponse.getData().get(0).getImgUrl());
        listener.onSuccess(listResponse.getData(), newToken);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());
    }
}
