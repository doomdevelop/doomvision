package com.akozlowski.doomvision;

import android.app.Application;

import com.akozlowski.doomvision.service.InternetConnectivityReceiver;
import com.akozlowski.doomvision.util.DebugLog;

/**
 * Created by akozlowski on 14/08/15.
 */
public class DoomVisionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.d("DoomVisionApplication onCreate()");
        InternetConnectivityReceiver.createInstance(this);
    }
}
