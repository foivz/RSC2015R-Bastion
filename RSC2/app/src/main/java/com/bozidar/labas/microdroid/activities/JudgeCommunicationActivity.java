package com.bozidar.labas.microdroid.activities;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.item.CommunicationItem;
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

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;

public class JudgeCommunicationActivity extends MicroActivity implements MicroRecyclerAdapter.onMicroItemCLickListener, Callback<Response<String>> {

    @Bind(R.id.list)
    RecyclerView list;
    private MicroRecyclerAdapter adapter;
    String id;

    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_judge_communication;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        user = prefs.loadObject(getResources().getString(R.string.user_data), this);
        setRecyclerView(mockComItems());
        id = getIntent().getStringExtra("person");

    }

    private ArrayList<String> mockComItems(){
        Resources res = getResources();
        String[] communication = res.getStringArray(R.array.sudac_salji_timovima);
        return new ArrayList<>(Arrays.asList(communication));
    }

    public void setRecyclerView(ArrayList<String> data) {
        this.list.setLayoutManager(new LinearLayoutManager(this));
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
        String message = ((CommunicationItem)item).getModel();
        sendPush(message);
    }

    public void sendPush(String message){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.sendPush(tokenFormat, id, message, this);
    }

    @Override
    public void success(Response<String> stringResponse, retrofit.client.Response response) {
        Log.d("success", "success");
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());
    }
}
