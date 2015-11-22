package com.bozidar.labas.microdroid.mvp.presenter;

import com.bozidar.microdroid.model.User;

/**
 * Created by Bozidar on 21.11.2015..
 */
public interface CreateTeamPresenter {
    void createTeam(User user, String name, String token);
}
