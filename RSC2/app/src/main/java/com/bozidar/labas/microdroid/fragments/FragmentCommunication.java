package com.bozidar.labas.microdroid.fragments;

import android.os.Bundle;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class FragmentCommunication extends MicroTabFrag {

    private static final String ARG_PARAM1 = "data1";
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

    }

    public static FragmentCommunication newInstance(String param1) {
        FragmentCommunication fragment = new FragmentCommunication();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
}
