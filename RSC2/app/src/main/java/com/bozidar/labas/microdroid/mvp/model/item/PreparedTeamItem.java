package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.response.Player;
import com.bozidar.labas.microdroid.mvp.model.response.PreparedTeamResponse;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class PreparedTeamItem implements MicroItem {

    @Bind(R.id.tv_team_name)
    TextView tvIdem;

    private PreparedTeamResponse model;
    private Player player;

    public PreparedTeamItem(Player player){
        this.player = player;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.communication_list_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvIdem.setText(player.getName());
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.bind(this, view);
    }
}
