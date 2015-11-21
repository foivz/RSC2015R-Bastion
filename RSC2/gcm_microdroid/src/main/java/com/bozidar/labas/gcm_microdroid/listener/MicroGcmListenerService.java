package com.bozidar.labas.gcm_microdroid.listener;


import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.bozidar.labas.gcm_microdroid.BusProvider;
import com.bozidar.labas.gcm_microdroid.R;
import com.google.android.gms.gcm.GcmListenerService;
import com.squareup.otto.Bus;

/**
 * Created by Bozidar on 16.11.2015..
 */
public class MicroGcmListenerService extends GcmListenerService{

    private Bus bus = BusProvider.getInstance();

    public MicroGcmListenerService(){
        bus.register(this);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        sendNotification(from, data.getString("message"));
    }

    private void sendNotification(String from, String message) {
//            Class activity = Class.forName("MyCreatedTeam");
//            Intent intent = new Intent(this, activity);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_ic_notification)
                    .setContentTitle("GCM Message")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());




    }

}