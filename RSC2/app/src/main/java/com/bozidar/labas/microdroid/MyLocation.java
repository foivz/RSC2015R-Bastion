package com.bozidar.labas.microdroid;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class MyLocation implements GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener{

    private static MyLocation INSTANCE;

    private GoogleApiClient googleApiClient;
    private Activity activity;

    private MyLocation(){}

    private Location currentLocation;

    public MyLocation getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MyLocation();

            googleApiClient = new GoogleApiClient.Builder(activity)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

        }
        return INSTANCE;
    }

    public void getCurrentLocation(){
        currentLocation = LocationServices
                .FusedLocationApi
                .getLastLocation(googleApiClient);
        Log.d("location", currentLocation.getLongitude() + "");
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
