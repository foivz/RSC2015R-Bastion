package com.bozidar.labas.microdroid.mvp.interactor;

import com.bozidar.labas.microdroid.mvp.listener.OnTeamCreatedListener;

/**
 * Created by Bozidar on 21.11.2015..
 */
public interface CreateTeamInteractor {
    void createTeam(String name, String token, OnTeamCreatedListener listener);
}
