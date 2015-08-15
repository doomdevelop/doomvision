package com.akozlowski.doomvision.ui;

import android.os.Bundle;

import com.akozlowski.doomvision.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectFragment;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectFragment(R.id.fragment)
    private MainActivityFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
