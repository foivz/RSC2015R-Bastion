package com.bozidar.labas.microdroid;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.bozidar.labas.gcm_microdroid.BusProvider;
import com.bozidar.labas.gcm_microdroid.R;
import com.bozidar.labas.microdroid.activities.MainActivity;
import com.bozidar.labas.microdroid.activities.WinnerLooserActivity;
import com.google.android.gms.gcm.GcmListenerService;
import com.squareup.otto.Bus;

/**
 * Created by Bozidar on 16.11.2015..
 */
public class MicroGcmListenerService extends GcmListenerService {

    private Bus bus = BusProvider.getInstance();

    public MicroGcmListenerService() {
        bus.register(this);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        sendNotification(from, data.getString("message"));
    }

    private void sendNotification(String from, String message) {
        Intent intent;
        PendingIntent pendingIntent = null;

        Log.d("poruka", message);

        if(message.equals("Pobjednik") || message.equals("Gubitnik")){
            intent = new Intent(this, WinnerLooserActivity.class);
            intent.putExtra("indicator", message);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }else{
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("game", "game");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("GCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }

}
