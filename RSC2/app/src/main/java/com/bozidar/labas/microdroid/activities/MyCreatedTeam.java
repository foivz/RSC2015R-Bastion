package com.bozidar.labas.microdroid.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bozidar.labas.gcm_microdroid.BusProvider;
import com.bozidar.labas.gcm_microdroid.listener.RegistrationId;
import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.item.MyPlayerItem;
import com.bozidar.labas.microdroid.mvp.model.response.Player;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MyCreatedTeam extends MicroActivity implements MicroRecyclerAdapter.onMicroItemCLickListener, Callback<Response<List<Player>>> {

    private Bus bus = BusProvider.getInstance();

    @Bind(R.id.list)
    RecyclerView list;

    private MicroRecyclerAdapter adapter;

    SharedPrefs prefs = SharedPrefs.getInstance();
    private User user;
    private String teamName;

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
        user = prefs.loadObject(getResources().getString(R.string.user_data), this);

        //1. Register device
        //GoogleServiceManager.registerDevice(this);

        //send request for fetching users of my new group

        fetchMyPlayers();
    }

    private void fetchMyPlayers(){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.fetchMyPlayers(tokenFormat, this);
    }

    @Subscribe
    public void getToken(RegistrationId token){
        Log.d("tokenn", token.getId());
    }

    public void setRecyclerView(List<Player> data) {
        this.list.setLayoutManager(new LinearLayoutManager(this));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        // ArrayList<MockModel> data = MockModel.getData();

        for (Player model : data) {
            Log.d("test", model.getName());
            MyPlayerItem teamItem = new MyPlayerItem(model);
            adapter.addItem(teamItem);
        }
    }

    @Override
    public void microItemClicked(View view, MicroItem item) {

    }

    @Override
    public void success(Response<List<Player>> listResponse, retrofit.client.Response response) {
        Log.d("dohvaceno", "dohvaceno");
        List<Player> players = listResponse.getData();
        //teamName = players.get(0).getTeamName();
        setRecyclerView(players);
    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.fab)
    public void lockTeam(){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.lockTeam(tokenFormat, teamName, new Callback<Response<String>>() {

            @Override
            public void success(Response<String> stringResponse, retrofit.client.Response response) {
                Log.d("success", stringResponse.getMessage());
                teamLocked();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("error", error.toString());
            }
        });
    }

    public void teamLocked(){
        Toast.makeText(this, "Team Created", Toast.LENGTH_LONG).show();
    }
}
