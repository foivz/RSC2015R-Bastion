package com.bozidar.labas.microdroid.activities;

import android.widget.TextView;

import com.bozidar.labas.microdroid.R;
import com.bozidar.microdroid.base.MicroActivity;

import butterknife.Bind;

public class WinnerLooserActivity extends MicroActivity {

    @Bind(R.id.indicator)
    TextView indicator;


    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_winner_looser;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        String tv = getIntent().getStringExtra("indicator");
        indicator.setText(tv);
    }
}
