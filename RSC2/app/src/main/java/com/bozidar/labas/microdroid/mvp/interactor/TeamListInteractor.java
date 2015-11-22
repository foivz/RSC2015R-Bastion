package com.bozidar.labas.microdroid.mvp.interactor;

import com.bozidar.labas.microdroid.mvp.listener.TeamListListener;

/**
 * Created by macbook on 18.10.2015..
 */
public interface TeamListInteractor {
    void loadTeamList(TeamListListener listener, String token);
}
