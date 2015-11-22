package com.bozidar.microdroid.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;


public abstract class MicroActivity extends AppCompatActivity {

    private int layoutResource;
    protected Fragment currentFragment;
    private int fragmentContainer;
    protected Bundle savedInstanceState;
    protected Toolbar toolbar;
    protected Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;
        this.layoutResource = setupLayoutRes();
        setContentView(this.layoutResource);
        setToolbar();

        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        if(setupMenuRes() != 0)
            getMenuInflater().inflate(setupMenuRes(), menu);
        return true;
    }

    public void setFragment(int container, Fragment fragment) {
        if (this.savedInstanceState == null && currentFragment != fragment) {
            this.currentFragment = fragment;
            this.fragmentContainer = container;
            getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
        }
    }

    /**
     * Set a Toolbar act as the ActionBar for this Activity window
     */
    public void setToolbar() {
        if (setupToolbar() != 0) {
            Toolbar toolbar = (Toolbar) findViewById(setupToolbar());
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.toolbar = toolbar;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }

    public Typeface setToolbarFont(String font){
        return Typeface.createFromAsset(getAssets(), font);
    }



    /**
     * Get a Toolbar instance which is shown on current Activity
     *
     * @return instance of toolbar
     */
    protected Toolbar getToolbar() {
        return this.toolbar;
    }

    /**
     * Setup Toolbar instance
     *
     * @return
     */
    public abstract int setupToolbar();

    /**
     * Setup resource id for child of this abstract class
     *
     * @return layout resource id
     */
    public abstract int setupLayoutRes();

    /**
     * Setup menu resource id for child of this abstract class
     *
     * @return menu resource id
     */
    public abstract int setupMenuRes();


    /**
     * This is called with activity creation (for example, here is fragment created)
     */
    public abstract void init();

    public Bundle getSavedInstanceState() {
        return this.savedInstanceState;
    }
}

//FragmentManager
// mantains references to all fragments inside Activtiy
//Use findFragmentById() or findFragmentByTag() to get reference to a particular Fragment

//FragmentTransactions
//Used for adding, removing and replacing Fragments
