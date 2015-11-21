package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.presenter.LoginPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.LoginPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.LoginView;
import com.bozidar.labas.microdroid.utils.IntentUtil;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends MicroActivity implements LoginView {

    @Bind(R.id.etEmail)
    TextView etEmail;

    @Bind(R.id.etPassword)
    TextView etPassword;

    LoginPresenter presenter;


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        presenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void goToRegistrationActivity(View v) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    //change 0 to social login id
    @Override
    public void navigateToHome(User user) {
        IntentUtil.startMainActivity(this, user, 0, "RSCLogin");
    }

    @Override
    public void setWrongAuthentication() {

    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @OnClick(R.id.btnLogin)
    public void login() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

}
