package com.bozidar.labas.microdroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.JudgeCommunicationActivity;
import com.bozidar.labas.microdroid.mvp.model.item.PreparedTeamItem;
import com.bozidar.labas.microdroid.mvp.model.response.Player;
import com.bozidar.labas.microdroid.mvp.model.response.PreparedTeamResponse;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class JudgeFragmentTeamOne extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener{

    @Bind(R.id.list)
    RecyclerView list;

    private MicroRecyclerAdapter adapter;

    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();

    private static final String ARG_PARAM1 = "data1";

    List<Player> data;

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_team_one;
    }

    @Override
    public void init() {
        user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());
        setRecyclerView(data);
    }

    public JudgeFragmentTeamOne(PreparedTeamResponse team){
        data = team.getPlayers();
    }

    public static JudgeFragmentTeamOne newInstance(String param1, PreparedTeamResponse team) {
        JudgeFragmentTeamOne fragment = new JudgeFragmentTeamOne(team);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void setRecyclerView(List<Player> data) {
        this.list.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        // ArrayList<MockModel> data = MockModel.getData();


        for (Player model : data) {
            PreparedTeamItem teamItem = new PreparedTeamItem(model);
            adapter.addItem(teamItem);
        }
    }

    @Override
    public void microItemClicked(View view, MicroItem item) {
        Intent intent = new Intent(getMicroActivity(), JudgeCommunicationActivity.class);
        intent.putExtra("person", ((PreparedTeamItem)item).getPlayer().getId());
        startActivity(intent);
    }
}
