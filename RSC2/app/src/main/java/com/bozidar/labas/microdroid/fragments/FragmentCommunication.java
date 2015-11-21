package com.bozidar.labas.microdroid.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.item.CommunicationItem;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class FragmentCommunication extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener {

    private static final String ARG_PARAM1 = "data1";

    @Bind(R.id.list)
    RecyclerView list;
    private MicroRecyclerAdapter adapter;

    @Override
    public String setTabTitle() {
        return getArguments().getString(ARG_PARAM1);
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_communication;
    }

    @Override
    public void init() {
        setRecyclerView(mockComItems());
    }

    private ArrayList<String> mockComItems(){
        Resources res = getMicroActivity().getResources();
        String[] communication = res.getStringArray(R.array.salji_igracima);
        return new ArrayList<String>(Arrays.asList(communication));
    }

    public static FragmentCommunication newInstance(String param1) {
        FragmentCommunication fragment = new FragmentCommunication();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void setRecyclerView(ArrayList<String> data) {
        this.list.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        // ArrayList<MockModel> data = MockModel.getData();

        for (String model : data) {
            CommunicationItem teamItem = new CommunicationItem(model);
            adapter.addItem(teamItem);
        }
    }

    @Override
    public void microItemClicked(View view, MicroItem item) {

    }
}
