package com.bozidar.labas.microdroid.mvp.model.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class CommunicationItem implements MicroItem {

    @Bind(R.id.tv_team_name)
    TextView tvCommunication;

    private String communicationModel;

    public CommunicationItem(String communicationModel) {
        this.communicationModel = communicationModel;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.communication_list_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        tvCommunication.setText(communicationModel);
    }

    @Override
    public void setItemView(View view) {
            ButterKnife.bind(this, view);
    }

    public String getModel(){
        return this.communicationModel;
    }
}
