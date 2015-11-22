package com.bozidar.labas.microdroid.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.item.CommunicationItem;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class FragmentCommunication extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener, Callback<Response<String>> {

    private static final String ARG_PARAM1 = "data1";

    @Bind(R.id.list)
    RecyclerView list;
    private MicroRecyclerAdapter adapter;

    @Bind(R.id.btn_change_message)
    Button btn;

    String message;

    private String judge = "Poruke suca";
    private String player = "Poruke igraca";

    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();

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
        user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());
    }

    private ArrayList<String> mockComItems(){
        Resources res = getMicroActivity().getResources();
        String[] communication = res.getStringArray(R.array.salji_igracima);
        return new ArrayList<String>(Arrays.asList(communication));
    }

    private ArrayList<String> mockToJudgeItems(){
        Resources res = getMicroActivity().getResources();
        String[] communication = res.getStringArray(R.array.salji_sudcu);
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
        message = ((CommunicationItem)item).getModel();
        if(btn.getText().equals(player)){
            sendPushToMisterJudge();

        }else{
            sendPushToMyTeam();
        }
    }

    @OnClick(R.id.btn_change_message)
    public void changeMessageType(View v){
        Button b = (Button)v;
        if(((Button) v).getText().equals(judge)) {
            b.setText(player);
            adapter.delete();
            setRecyclerView(mockToJudgeItems());

        }else{
            b.setText(judge);
            adapter.delete();
            setRecyclerView(mockComItems());

        }
    }

    public void sendPushToMisterJudge(){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.sendPushToJudge(tokenFormat, message, this);
    }


    public void sendPushToMyTeam(){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.sendPushToMyComrade(tokenFormat, message, this);
    }

    @Override
    public void success(Response<String> stringResponse, retrofit.client.Response response) {
        Log.d("success", "success");
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", "error");
    }
}
