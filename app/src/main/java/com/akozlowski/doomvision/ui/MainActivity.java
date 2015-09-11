package com.akozlowski.doomvision.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.util.DebugLog;
import com.google.inject.Key;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.context.event.OnCreateEvent;
import roboguice.context.event.OnDestroyEvent;
import roboguice.event.EventManager;
import roboguice.inject.ContentView;
import roboguice.inject.ContentViewListener;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements RoboContext {

    private static final String TAG = MainActivity.class.getSimpleName();
    protected EventManager eventManager;
    protected HashMap<Key<?>, Object> scopedObjects = new HashMap<Key<?>, Object>();
    private static final String STATE_ORIENTATION_KEY = "STATE_ORIENTATION_KEY";

    @Inject
    ContentViewListener ignored; // BUG find a better place to put this

    private void changeFragment(Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, name).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG + " onCreate()...");
        if (DataManager.getInstance().getResponse() == null) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
        final RoboInjector injector = RoboGuice.getInjector(this);
        eventManager = injector.getInstance(EventManager.class);
        injector.injectMembersWithoutViews(this);
        super.onCreate(savedInstanceState);
        eventManager.fire(new OnCreateEvent<Activity>(this, savedInstanceState));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            DebugLog.d(TAG + " onCreate()..savedInstanceState is null");
            transaction.replace(R.id.fragment_container, new ListViewGalleryFragment()).addToBackStack(null).commit();
        } else {
            if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                DebugLog.d(TAG + " SCREEN_ORIENTATION_PORTRAIT");
                changeFragment(new ListViewGalleryFragment(), ListViewGalleryFragment.class.getSimpleName());
            } else {
                DebugLog.d(TAG + " SCREEN_ORIENTATION_LANDSCAPE");
                changeFragment(new SlidesGalleryFragment(), SlidesGalleryFragment.class.getSimpleName());
            }
        }
    }

    public void changeRequestedOrientation(boolean sensorOn) {
        if (sensorOn) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onStop() {
        try {
            eventManager.fire(new OnStopEvent(this));
        } finally {
            super.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            eventManager.fire(new OnDestroyEvent<Activity>(this));
        } finally {
            try {
                RoboGuice.destroyInjector(this);
            } finally {
                super.onDestroy();
            }
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        RoboGuice.getInjector(this).injectViewMembers(this);
        eventManager.fire(new OnContentChangedEvent(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        DebugLog.d(TAG + " ***** onSaveInstanceState()  ");
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            outState.putInt(STATE_ORIENTATION_KEY, Configuration.ORIENTATION_PORTRAIT);
        } else {
            outState.putInt(STATE_ORIENTATION_KEY, Configuration.ORIENTATION_LANDSCAPE);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }
}


