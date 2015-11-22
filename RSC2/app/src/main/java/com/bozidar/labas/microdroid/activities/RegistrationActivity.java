package com.bozidar.labas.microdroid.activities;

import android.util.Log;
import android.widget.TextView;

import com.bozidar.labas.gcm_microdroid.BusProvider;
import com.bozidar.labas.gcm_microdroid.listener.RegistrationId;
import com.bozidar.labas.gcm_microdroid.utils.GoogleServiceManager;
import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.presenter.RegisterPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.RegisterPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.RegisterView;
import com.bozidar.microdroid.base.MicroActivity;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.OnClick;

public class RegistrationActivity extends MicroActivity implements RegisterView {

    @Bind(R.id.email)
    TextView tvEmail;

    @Bind(R.id.password)
    TextView tvPassword;

    @Bind(R.id.first_name)
    TextView tvFirstName;

    @Bind(R.id.last_name)
    TextView tvLastName;

    RegisterPresenter presenter;

    private String pushid;

    private Bus bus = BusProvider.getInstance();


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_registration;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        presenter = new RegisterPresenterImpl(this);

        //1. Register device
        GoogleServiceManager.registerDevice(this);

    }

    @OnClick(R.id.email_sign_in_button)
    public void register() {
        presenter.register(getPushid(), tvPassword.getText().toString(), tvEmail.getText().toString(), tvFirstName.getText().toString(), tvLastName.getText().toString());
    }

    @Override
    protected void onStart() {
        bus.register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        bus.unregister(this);
        super.onStop();
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Subscribe
    public void getToken(RegistrationId token){
        Log.d("dohvaen", token.getId());
        setPushid(token.getId());
    }

    public String getPushid() {
        return pushid;
    }

    public void setPushid(String pushid) {
        this.pushid = pushid;
    }
}
