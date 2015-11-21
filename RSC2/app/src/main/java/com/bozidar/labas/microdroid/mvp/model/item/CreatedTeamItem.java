package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.CreatedTeamModel;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class CreatedTeamItem implements MicroItem {

    @Bind(R.id.tv_team_leader)
    TextView tvTeamleader;

    @Bind(R.id.tv_team_name)
    TextView tvTeamName;

    private CreatedTeamModel teamModel;

    public CreatedTeamItem(CreatedTeamModel teamModel){
        this.teamModel = teamModel;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.created_team_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvTeamName.setText(teamModel.getName());
        tvTeamleader.setText("Voda tima: " + teamModel.getTeamLeader());
        //add iamge with glide later (mandatory)
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.bind(this, view);
    }
}
