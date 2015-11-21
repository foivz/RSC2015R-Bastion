package com.bozidar.labas.microdroid.mvp.presenter.impl;

import com.bozidar.labas.microdroid.mvp.interactor.CreateTeamInteractor;
import com.bozidar.labas.microdroid.mvp.interactor.impl.CreateTeamInteractorImpl;
import com.bozidar.labas.microdroid.mvp.listener.OnTeamCreatedListener;
import com.bozidar.labas.microdroid.mvp.presenter.CreateTeamPresenter;
import com.bozidar.labas.microdroid.mvp.view.CreateTeamView;
import com.bozidar.microdroid.model.User;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class CreateTeamPresenterImpl implements CreateTeamPresenter, OnTeamCreatedListener{

    CreateTeamView view;
    CreateTeamInteractor interactor;
    private User user;

    public CreateTeamPresenterImpl(CreateTeamView view){
        this.view = view;
        interactor = new CreateTeamInteractorImpl();
    }
    @Override
    public void createTeam(User user, String name, String token) {
        interactor.createTeam(name, token, this);
        this.user = user;
    }

    @Override
    public void teamCreated(String newToken) {
        this.user.setToken(newToken);
        view.teamCreated(user);
    }
}
