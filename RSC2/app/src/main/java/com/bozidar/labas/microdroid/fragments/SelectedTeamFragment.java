package com.bozidar.labas.microdroid.fragments;

import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.base.MicroFragment;

import butterknife.Bind;


public class SelectedTeamFragment extends MicroFragment {

    SharedPrefs prefs = SharedPrefs.getInstance();

    @Bind(R.id.selected_team)
    TextView tvSelectedTeam;


    @Override
    public int setLayoutResource() {
        return R.layout.fragment_selected_team;
    }

    @Override
    public void init() {
        String selectedTeam = prefs.getValue(getMicroActivity(), "myCreatedTeam");
        tvSelectedTeam.setText(selectedTeam);
    }
}