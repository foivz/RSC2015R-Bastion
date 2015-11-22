package com.bozidar.labas.microdroid.mvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class CreatedTeamModel {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("team_leader")
    private String teamLeader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }
}
