package com.bozidar.labas.microdroid.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.JudgeActivity;
import com.bozidar.labas.microdroid.activities.MainActivity;
import com.bozidar.microdroid.model.User;


/**
 * Created by Bozidar on 12.11.2015..
 */
public class IntentUtil {

    private static final String KEY_USER = "rsc.bozidar.user";
    private static final String KEY_PROJECT = "rsc.bozidar.project";

    public static void startMainActivity(Context context, User user, int socialNetworkId,  String loginType){
        SharedPrefs prefs = SharedPrefs.getInstance();
        prefs.saveObject(context, context.getResources().getString(R.string.user_data), user);
        prefs.save(context, context.getResources().getString(R.string.login), loginType);
        Log.d("tokenLogin", user.getToken());

        String jsonUser = Serializator.serialize(user);
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtra(KEY_USER, jsonUser);
        newIntent.putExtra("networkId", socialNetworkId);
        context.startActivity(newIntent);
    }

    public static void startJudgeActivity(Context context, User user, int socialNetworkId,  String loginType){
        SharedPrefs prefs = SharedPrefs.getInstance();
        prefs.saveObject(context, context.getResources().getString(R.string.user_data), user);
        prefs.save(context, context.getResources().getString(R.string.login), loginType);
        Log.d("tokenLogin", user.getToken());

        String jsonUser = Serializator.serialize(user);
        Intent newIntent = new Intent(context, JudgeActivity.class);
        newIntent.putExtra(KEY_USER, jsonUser);
        newIntent.putExtra("networkId", socialNetworkId);
        context.startActivity(newIntent);
    }

//    public static void startDetailActivity(TvStationModel model, Context context, User user){
//        SharedPrefs prefs = SharedPrefs.getInstance();
//        prefs.saveObject(context, context.getResources().getString(R.string.user_data), user);
//
//        String project = Serializator.serialize(model);
//        Intent newIntent = new Intent(context, DetailsActivity2.class);
//        newIntent.putExtra(KEY_PROJECT, project);
//        context.startActivity(newIntent);
//    }
//
//    public static User fetchUser(Activity activity) {
//        String userJSON = activity.getIntent().getStringExtra(KEY_USER);
//        return Serializator.deserialize(userJSON, User.class);
//    }

}
