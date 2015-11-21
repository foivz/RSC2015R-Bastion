package com.bozidar.labas.microdroid.mvp.interactor;


import com.bozidar.labas.microdroid.mvp.listener.OnregisterFinishedListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface RegisterInteractor {
    void register(String password, String email, String firstName, String lastName, OnregisterFinishedListener listener);
}


