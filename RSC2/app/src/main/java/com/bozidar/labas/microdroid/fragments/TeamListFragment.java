package com.bozidar.labas.microdroid.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.activities.CreateTeamDialogActivity;
import com.bozidar.labas.microdroid.activities.MainActivity;
import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.labas.microdroid.mvp.model.item.CreatedTeamItem;
import com.bozidar.labas.microdroid.mvp.model.response.Response;
import com.bozidar.labas.microdroid.mvp.presenter.TeamListPresenter;
import com.bozidar.labas.microdroid.mvp.presenter.impl.TeamListPresenterImpl;
import com.bozidar.labas.microdroid.mvp.view.CreatedTeamsView;
import com.bozidar.labas.microdroid.network.RequestAPI;
import com.bozidar.labas.microdroid.utils.Constants;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.labas.microdroid.utils.TokenManager;
import com.bozidar.microdroid.base.MicroFragment;
import com.bozidar.microdroid.model.User;
import com.bozidar.microdroid.network.ServiceFactory;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class TeamListFragment extends MicroFragment implements MicroRecyclerAdapter.onMicroItemCLickListener, CreatedTeamsView, Callback<Response<String>> {

    @Bind(R.id.list)
    RecyclerView list;

    CreatedTeamModel selectedModel;


    private MicroRecyclerAdapter adapter;
    private TeamListPresenter presenter;
    User user;

    SharedPrefs prefs = SharedPrefs.getInstance();

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_team_list;
    }

    @Override
    public void init() {
//        setRecyclerView(mockTeams());

        user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());

        presenter = new TeamListPresenterImpl(this);
        User user = prefs.loadObject(getResources().getString(R.string.user_data), getMicroActivity());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        presenter.getTeamList(user);
    }

    private ArrayList<CreatedTeamModel> mockTeams(){
        ArrayList<CreatedTeamModel> data = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            CreatedTeamModel model = new CreatedTeamModel();
            model.setName("test");
            model.setTeamLeader("Bozidar Labas");
            model.setId(1);
            data.add(model);
        }
        return data;
    }

    public void setRecyclerView(List<CreatedTeamModel> data) {
        this.list.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        // ArrayList<MockModel> data = MockModel.getData();

        for (CreatedTeamModel model : data) {
            CreatedTeamItem teamItem = new CreatedTeamItem(model);
            adapter.addItem(teamItem);
        }
    }

    @Override
    public void microItemClicked(View view, MicroItem item) {
        selectedModel = ((CreatedTeamItem)item).getModel();
        prefs.save(getMicroActivity(), "myCreatedTeam", selectedModel.getName());
        goToTeam(selectedModel.getName(), selectedModel.getId());
    }

    private void goToSelectedTeamFragment(CreatedTeamModel selectedModel){
        ((MainActivity)getMicroActivity()).setFragment(R.id.content, new SelectedTeamFragment());
    }

    @OnClick(R.id.fab)
    public void createTeam(){
        startActivity(new Intent(getMicroActivity(), CreateTeamDialogActivity.class));
    }

    @Override
    public void showTvStations(List<CreatedTeamModel> model, User user) {
        //TokenManager.storeNewTokenLocaly(getMicroActivity(), user);
        Log.d("tokenLista", user.getToken());
        setRecyclerView(model);
    }

    public void goToTeam(String teamName, int id){
        RequestAPI api = ServiceFactory.createRetrofitService(RequestAPI.class, Constants.ENDPOINT);
        String tokenFormat = TokenManager.formatToken(user.getToken());
        api.goToTeam(tokenFormat, id, id, this);
    }

    @Override
    public void success(Response<String> stringResponse, retrofit.client.Response response) {
        Log.d("success", "success");
       // String newToken = TokenManager.getTokenFromHeader(response);
        //this.user.setToken(newToken);
        //TokenManager.storeNewTokenLocaly(getMicroActivity(), this.user);
        goToSelectedTeamFragment(selectedModel);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("error", "success");
    }
}
