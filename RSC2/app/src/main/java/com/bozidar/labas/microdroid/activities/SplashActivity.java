package com.bozidar.labas.microdroid.activities;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroSplashActivity;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class SplashActivity extends MicroSplashActivity {

    private static final int TIME = 1850;


    //TODO check if user is loged
    @Override
    public Class getNextClassActivity() {
        return LoginActivity.class;
    }

    @Override
    public int getSplashIntroTime() {
        return TIME;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.activity_splash;
    }

//    public boolean isUserLogged(){
//        String loggedIn  = SharedPrefs.getInstance().getValue(this, getResources().getString(R.string.login));
//        return !loggedIn.equals("");
//    }
}
