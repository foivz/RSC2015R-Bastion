package com.bozidar.labas.microdroid.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.labas.microdroid.mvp.model.item.CreatedTeamItem;
import com.bozidar.microdroid.base.MicroFragment;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class TeamListFragment extends MicroFragment implements MicroRecyclerAdapter.onMicroItemCLickListener {

    @Bind(R.id.list)
    RecyclerView list;

    private MicroRecyclerAdapter adapter;

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_team_list;
    }

    @Override
    public void init() {
        setRecyclerView(mockTeams());
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

    public void setRecyclerView(ArrayList<CreatedTeamModel> data) {
        this.list.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.list.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);

        // ArrayList<MockModel> data = MockModel.getData();

        for (CreatedTeamModel model : data) {
            Log.d("test", model.getName());
            CreatedTeamItem teamItem = new CreatedTeamItem(model);
            adapter.addItem(teamItem);
        }
    }

    @Override
    public void microItemClicked(View view, MicroItem item) {
        CreatedTeamModel selectedModel = ((CreatedTeamItem)item).getModel();

    }

    private void goToSelectedTeamFragment(CreatedTeamModel selectedModel){

    }
}
