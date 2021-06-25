package com.tommywebdesigns.phonestore.util;

import android.util.Log;

public class Logger {

    public static final String ERROR_TAG="TAG_ERROR";
    public static final String DEBUG_TAG="TAG_DEBUG";

    public static void logDebug(String message){ Log.d(DEBUG_TAG, message); }
    public static void logError(String message){
        Log.d(ERROR_TAG, message);
    }

}
