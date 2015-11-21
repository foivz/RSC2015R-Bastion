package com.bozidar.labas.microdroid.network;

import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by macbook on 18.10.2015..
 */
public interface RequestAPI {

    @POST("/api/login")
    void login(
            @Body LoginModel loginModel,
            Callback<Response<LoginResponse>> response);

    @FormUrlEncoded
    @POST("/api/logout")
    void logout(
            @Field("token") String token,
            Callback<String> response
    );


}
//api.register(username, email, password, firstName, lastName, city, birthDate, this);