package com.zoe.dongxia.betterme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction()
                .add(R.id.frame_layout, SettingsFragment.newInstance()).commit();
    }

}
