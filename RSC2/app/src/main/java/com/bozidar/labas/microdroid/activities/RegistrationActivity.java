package com.bozidar.labas.microdroid.activities;

import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;

import butterknife.Bind;

public class RegistrationActivity extends MicroActivity {

    @Bind(R.id.email)
    TextView tvEmail;

    @Bind(R.id.password)
    TextView tvPassword;

    @Bind(R.id.first_name)
    TextView tvFirstName;

    @Bind(R.id.last_name)
    TextView tvLastName;


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

    }
}
