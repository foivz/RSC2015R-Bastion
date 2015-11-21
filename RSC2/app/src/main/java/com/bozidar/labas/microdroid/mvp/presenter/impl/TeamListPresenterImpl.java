package com.bozidar.labas.microdroid.mvp.presenter.impl;

import com.bozidar.labas.microdroid.mvp.interactor.TeamListInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.TeamListInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.TeamListListener;
import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.labas.microdroid.mvp.presenter.TeamListPresenter;
import com.bozidar.labas.microdroid.mvp.view.CreatedTeamsView;
import com.bozidar.microdroid.model.User;

import java.util.List;

/**
 * Created by macbook on 18.10.2015..
 */
public class TeamListPresenterImpl implements TeamListPresenter, TeamListListener{

    private TeamListInteractor interactor;
    private CreatedTeamsView view;
    private User user;

    public TeamListPresenterImpl(CreatedTeamsView view){
        interactor = new TeamListInteractorImpl();
        this.view = view;
    }

    @Override
    public void getTeamList(User user) {
        this.user = user;
        interactor.loadTeamList(this, user.getToken());
    }

    @Override
    public void onSuccess(List<CreatedTeamModel> model, String token) {
        this.user.setToken(token);
        view.showTvStations(model, user);
    }
}
