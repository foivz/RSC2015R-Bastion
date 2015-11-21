package com.bozidar.labas.microdroid.activities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bozidar.labas.gcm_microdroid.BusProvider;
import com.bozidar.labas.gcm_microdroid.listener.RegistrationId;
import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import butterknife.Bind;

public class MyCreatedTeam extends MicroActivity {

    private Bus bus = BusProvider.getInstance();

    @Bind(R.id.list)
    RecyclerView list;

    private MicroRecyclerAdapter adapter;

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.fragment_my_teams;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        //1. Register device
        //GoogleServiceManager.registerDevice(this);
    }

    @Subscribe
    public void getToken(RegistrationId token){
        Log.d("tokenn", token.getId());
    }

//    public void setRecyclerView(List<CreatedTeamModel> data) {
//        this.list.setLayoutManager(new LinearLayoutManager(this));
//        if (this.adapter == null) adapter = new MicroRecyclerAdapter();
//
//        this.list.setAdapter(adapter);
//
//        adapter.setOnMicroCLickListener(this);
//
//        // ArrayList<MockModel> data = MockModel.getData();
//
//        for (CreatedTeamModel model : data) {
//            Log.d("test", model.getName());
//            CreatedTeamItem teamItem = new CreatedTeamItem(model);
//            adapter.addItem(teamItem);
//        }
//    }

}
