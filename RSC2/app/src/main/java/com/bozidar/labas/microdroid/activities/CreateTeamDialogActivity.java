package com.bozidar.labas.microdroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.EditText;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.presenter.CreateTeamPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.CreateTeamPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.CreateTeamView;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateTeamDialogActivity extends Activity implements CreateTeamView{

    @Bind(R.id.et_team_name)
    EditText teamName;

    SharedPrefs prefs = SharedPrefs.getInstance();

    CreateTeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_team_dialog_activiti);
        ButterKnife.bind(this);
        presenter = new CreateTeamPresenterImpl(this);
    }

    @OnClick(R.id.btn_create_team)
    public void createTeam(){
        Log.d("klik", "klik");
        User user = prefs.loadObject(getResources().getString(R.string.user_data), this);
        String name = teamName.getText().toString();
        presenter.createTeam(user, name, user.getToken());
    }

    @Override
    public void teamCreated(User user) {
        TokenManager.storeNewTokenLocaly(this, user);
        Log.d("kreiran", "oki doki");
    }
}
