package com.bozidar.labas.microdroid.mvp.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class Player implements Serializable {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
