package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class MyTeamUsersItem implements MicroItem {

    @Override
    public int getLayoutResource() {
        return R.layout.users_my_team;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {

    }

    @Override
    public void setItemView(View view) {

    }
}
