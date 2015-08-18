package com.akozlowski.doomvision.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akozlowski on 15/08/15.
 */
public class InternetConnectivityReceiver {
    private List<InternetConnectivityListener> callbacks = new ArrayList<InternetConnectivityListener>();
    private volatile boolean isReceiverRegistered = false;
    private ConnectivityManager connectivityManager;
    private Context context;
    private static InternetConnectivityReceiver instance;

    public static InternetConnectivityReceiver getInstance() {
        return instance;
    }

    public static synchronized InternetConnectivityReceiver createInstance(Context context) {
        instance = new InternetConnectivityReceiver(context);
        return instance;
    }

    private InternetConnectivityReceiver(Context context) {
        this.context = context;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void addInternetConnectivityListener(InternetConnectivityListener internetConnectivityListener) {
        synchronized (callbacks) {
            if (!callbacks.contains(internetConnectivityListener)) {
                callbacks.add(internetConnectivityListener);
            }
        }
    }

    public boolean isOnline() {
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    public void removeInternetConnectivityListener(InternetConnectivityListener connectivityListener) {
        synchronized (callbacks) {
            callbacks.remove(connectivityListener);
        }
    }

    private void notifyListeners() {
        synchronized (callbacks) {
            for (InternetConnectivityListener internetConnectivityListener : callbacks) {
                internetConnectivityListener.onInternetConnectivityCallback();
            }
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                boolean lackOfConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                if (!lackOfConnectivity && isOnline()) {
                    notifyListeners();
                }
            }
        }
    };

    /**
     * register receiver CONNECTIVITY_ACTION from {@link ConnectivityManager}
     *
     * @param internetConnectivityListener pass it to get call if connectivity status will get change
     */
    public void registerConnectivityReceiver(InternetConnectivityListener
                                                     internetConnectivityListener) {
        addInternetConnectivityListener(internetConnectivityListener);
        if (isReceiverRegistered) {
            return;
        }
        IntentFilter filterInternet = new IntentFilter();
        filterInternet.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, filterInternet);
        isReceiverRegistered = true;
    }

    public interface InternetConnectivityListener {
        void onInternetConnectivityCallback();
    }
}
