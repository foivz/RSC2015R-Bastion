package com.bozidar.labas.gcm_microdroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bozidar.labas.gcm_microdroid.listener.RegistrationId;
import com.bozidar.labas.gcm_microdroid.utils.StringConstants;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.squareup.otto.Bus;


/**
 * Created by Bozidar on 16.11.2015..
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};
    private String token;
    private Bus bus = BusProvider.getInstance();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public RegistrationIntentService() {
        super(TAG);
        bus.register(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            //Get registration token
            InstanceID id = InstanceID.getInstance(this);
            token = id.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            //Send registration token to subscriber
            bus.post(new RegistrationId(token, "success"));

            //Send token to server
            sendRegistrationToServer(token);

            //Store boolean flag if token is received
            storeTokenNotifierData(true);
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            storeTokenNotifierData(false);
            bus.post(new RegistrationId(token, "failed"));
        } finally {
            bus.unregister(this);
        }
    }

    private void sendRegistrationToServer(String token) {}

    private void storeTokenNotifierData(boolean isTokenStored){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(StringConstants.SENT_TOKEN_TO_SERVER, isTokenStored).apply();
    }
}
