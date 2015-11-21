package com.bozidar.labas.microdroid.activity;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;

public class MainActivity extends MicroActivity {


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

    }
}
