package com.bozidar.labas.gcm_microdroid.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.bozidar.labas.gcm_microdroid.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

/**
 * Created by Bozidar on 16.11.2015..
 */
public class GoogleServiceManager {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "Device";
    private static ArrayList<String> screens = new ArrayList<>();

    public static void registerDevice(Activity activity){
        if(checkPlayServices(activity)){
            Intent intent = new Intent(activity, RegistrationIntentService.class);
            activity.startService(intent);
        }
    }

    private static boolean checkPlayServices(Activity activity){
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }

    /**
     * detailScreen hash map will store names of activities which will be opened for particular topic (topic is key)
     * class will be instantiated using reflection
     */
    public static void addTopicScreen(String detailScreen){
        screens.add(detailScreen);
    }

    public static ArrayList<String> getScreens() {
        return screens;
    }
}
