package com.bozidar.labas.microdroid.activities;

import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.presenter.RegisterPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.RegisterPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.RegisterView;
import com.bozidar.microdroid.base.MicroActivity;

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
    }

    @OnClick(R.id.email_sign_in_button)
    public void register() {
        presenter.register(tvPassword.getText().toString(), tvEmail.getText().toString(), tvFirstName.getText().toString(), tvLastName.getText().toString());
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }
}
