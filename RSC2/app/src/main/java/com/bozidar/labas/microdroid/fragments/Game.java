package com.bozidar.labas.microdroid.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroFragment;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;

import butterknife.Bind;

/**
 * Created by Bozidar on 21.11.2015..
 */
public class Game extends MicroFragment {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.pagerMy)
    ViewPager viewPager;

    public static Game newInstance() {
        Game fragment = new Game();
        return fragment;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_game;
    }

    @Override
    public void init() {
        setUpTabs();
    }

    public void setUpTabs() {
        MicroTabManager microTabManager = new MicroTabManager(getChildFragmentManager(), viewPager, tabLayout);
        MapFragment mapFragment = MapFragment.newInstance("Mapa");
        FragmentCommunication fragmentCommunication = FragmentCommunication.newInstance("Komunikacija");
        microTabManager.addTab(mapFragment);
        microTabManager.addTab(fragmentCommunication);
        microTabManager.init();
    }

}
