package com.akozlowski.doomvision.util;

import android.util.Log;

/**
 * Created by akozlowski on 14/08/15.
 */
public class DebugLog {
    private static boolean SHOW_LOG = true;
    public static final String LOG_TAG = "DOOM:LOG";

    public static void enableLog(boolean enable) {
        SHOW_LOG = enable;
    }

    /**
     * Logs a debug message in the application log.
     *
     * @param message the message to be logged
     */
    public static void d(String message) {
        if (SHOW_LOG)
            Log.d(LOG_TAG, message);
    }

    /**
     * Logs an error message in the application log.
     *
     * @param message the message to be logged
     */
    public static void e(String message) {
        if (SHOW_LOG)
            Log.e(LOG_TAG, message);
    }

    /**
     * Logs an error message in the application log.
     *
     * @param message   the message to be logged
     * @param throwable the {@link Throwable} to be logged
     */
    public static void e(String message, Throwable throwable) {
        if (SHOW_LOG)
            Log.e(LOG_TAG, message, throwable);
    }

    /**
     * Logs an information message in the application log.
     *
     * @param message the message to be logged
     */
    public static void i(String message) {
        if (SHOW_LOG)
            Log.i(LOG_TAG, message);
    }

    /**
     * Logs a verbose message in the application log.
     *
     * @param message the message to be logged
     */
    public static void v(String message) {
        if (SHOW_LOG)
            Log.v(LOG_TAG, message);
    }

    /**
     * Logs a warning message in the application log.
     *
     * @param message the message to be logged
     */
    public static void w(String message) {
        if (SHOW_LOG)
            Log.w(LOG_TAG, message);
    }

    /**
     * Logs a WTF message in the application log.
     *
     * @param message the message to be logged
     */
    public static void wtf(String message) {
        if (SHOW_LOG)
            Log.wtf(LOG_TAG, message);
    }
}
