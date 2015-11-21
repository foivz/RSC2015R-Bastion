package com.bozidar.labas.gcm_microdroid.listener;

/**
 * Created by Bozidar on 16.11.2015..
 */
public class RegistrationId {
    private String id;

    private String message;

    public RegistrationId(String id, String message){this.id = id; this.message = message;}

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
