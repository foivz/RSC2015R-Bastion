package com.bozidar.labas.microdroid.network;

import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.labas.microdroid.mvp.model.LoginModel;
import com.bozidar.labas.microdroid.mvp.model.response.LoginResponse;
import com.bozidar.labas.microdroid.mvp.model.response.RegistrationResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
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

    @FormUrlEncoded
    @POST("/api/register")
    void register(
            @Field("full_name") String username,
            @Field("email") String email,
            @Field("password") String password,
            Callback<Response<RegistrationResponse>> response
    );

    @GET("/api/team")
    void fetchCreatedTeams(
            @Header("Authorization") String token,
            Callback<Response<List<CreatedTeamModel>>> response);

    @FormUrlEncoded
    @POST("/api/team")
    void createTeam(@Header("Authorization") String token,
                       @Field("name") String teamName,
                       Callback<Response<String>> response);

    @FormUrlEncoded
    @POST("/api/join")
    void goToTeam(@Header("Authorization") String token,
                    @Field("name") String teamName,
                    Callback<Response<String>> response);
}
//api.register(username, email, password, firstName, lastName, city, birthDate, this);