package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.mvp.model.response.Player;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bozidar on 22.11.2015..
 */
public class MyPlayerItem implements MicroItem {

    private Player player;

    @Bind(R.id.tvPlayerName)
    TextView playerName;

    public MyPlayerItem(Player player){
        this.player = player;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.my_players;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        playerName.setText(player.getName());
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.bind(this, view);
    }

    public Player getPlayer(){
        return this.player;
    }
}
