package com.bozidar.labas.microdroid.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.JudgeFragmentMap;
import com.bozidar.labas.microdroid.fragments.JudgeFragmentTeamOne;
import com.bozidar.labas.microdroid.mvp.model.response.PreparedTeamResponse;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;

public class JudgeActivity extends MicroActivity implements Callback<Response<List<PreparedTeamResponse>>> {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.pagerMy)
    ViewPager viewPager;

    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_judge2;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

        user = prefs.loadObject(getResources().getString(R.string.user_data), this);
        sendRequestToServer();
        setUpTabs();
    }

    private void sendRequestToServer() {
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.fetchPreparedTeams(tokenFormat, this);
    }

    public void setUpTabs() {
        MicroTabManager microTabManager = new MicroTabManager(getSupportFragmentManager(), viewPager, tabLayout);
        JudgeFragmentMap mapFragment = JudgeFragmentMap.newInstance("Mapa");
        JudgeFragmentTeamOne fragmentTeamOne = JudgeFragmentTeamOne.newInstance("Tim 1");
        JudgeFragmentTeamOne fragmentTeamTwo = JudgeFragmentTeamOne.newInstance("Tim 2");
        microTabManager.addTab(mapFragment);
        microTabManager.addTab(fragmentTeamOne);
        microTabManager.addTab(fragmentTeamTwo);
//        microTabManager.addTab(fragmentCommunication);
        microTabManager.init();
    }

    @Override
    public void success(Response<List<PreparedTeamResponse>> listResponse, retrofit.client.Response response) {
        Log.d("success", "success");
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", error.toString());
    }
}
