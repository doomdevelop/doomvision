package com.akozlowski.doomvision.service;

import android.content.Intent;

import roboguice.service.RoboIntentService;

/**
 * Created by akozlowski on 13/08/15.
 */
public class ApiService extends RoboIntentService {

    public ApiService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
