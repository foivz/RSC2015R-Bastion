package com.bozidar.labas.microdroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.bozidar.labas.microdroid.R;

public class CreateTeamDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_team_dialog_activiti);
    }
}
