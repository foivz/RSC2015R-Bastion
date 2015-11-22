package com.bozidar.labas.microdroid.mvp.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class PreparedTeamResponse implements Serializable {
    @SerializedName("score")
    private String score;

    @SerializedName("id")
    private String teamId;

    @SerializedName("game_id")
    private String gameiD;

    @SerializedName("team_leader")
    private String teamLeader;

    @SerializedName("name")
    private String teamName;





    @SerializedName("players")
    ArrayList<Player> players;


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getGameiD() {
        return gameiD;
    }

    public void setGameiD(String gameiD) {
        this.gameiD = gameiD;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}