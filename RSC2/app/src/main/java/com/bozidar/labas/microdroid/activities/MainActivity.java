package com.bozidar.labas.microdroid.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.labas.microdroid.fragments.Game;
import com.bozidar.labas.microdroid.fragments.JudgeFragment;
import com.bozidar.labas.microdroid.fragments.TeamListFragment;
import com.bozidar.labas.microdroid.utils.SharedPrefs;
import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.iphoneization.circleimageview.CircleImageView;
import com.bozidar.microdroid.model.User;
import com.bumptech.glide.Glide;

import butterknife.Bind;

public class MainActivity extends MicroActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar_title)
    TextView tvToolbar;

    @Bind(R.id.ivLoggedUser)
    CircleImageView imageView;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;

    TeamListFragment fragment;
    JudgeFragment judgeFragment;

    public enum OpenedFragment {
        FRAGMENT_CREATED_TEAMS,
        FRAGMENT_SELECTED_TEAM,
    }

    OpenedFragment openedFragment;

    User user;
    SharedPrefs prefs = SharedPrefs.getInstance();


    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

        setUpDrawer();
        fragment = new TeamListFragment();
        openedFragment = OpenedFragment.FRAGMENT_CREATED_TEAMS;
        judgeFragment = new JudgeFragment();

        String check = getIntent().getStringExtra("game");

        if (check != null) {
            setFragment(R.id.content, Game.newInstance());
        } else {
            setFragment(R.id.content, fragment);
        }

        Glide.with(this)
                .load("https://upload.wikimedia.org/wikipedia/commons/7/79/Yang_Liwei.jpg")
                .into(imageView);


    }


    /**
     * ------------DRAWER--------------------
     */
    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_logout:
                SharedPrefs.getInstance().removeUser(this);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.scan:
                drawerLayout.closeDrawers();
                startActivity(new Intent(this, QRCodeActivity.class));
                break;
            case R.id.igra:
                drawerLayout.closeDrawers();
                setFragment(R.id.content, Game.newInstance());
                break;
            case R.id.game_list:

                drawerLayout.closeDrawers();
                setFragment(R.id.content, fragment);
                break;
        }

        return false;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("test", "test");
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == com.bozidar.microdroid.R.id.action_search) {
            Log.d("Search", "Search");
            // menuSearch();
        }

        return super.onOptionsItemSelected(item);
    }



}
