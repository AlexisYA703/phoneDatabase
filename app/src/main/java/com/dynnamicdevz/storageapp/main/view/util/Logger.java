package com.dynnamicdevz.storageapp.main.view.util;

import android.util.Log;

public class Logger {
    private static final String ERROR_TAG ="TAG_ERROR";
    private static final String DEBUG_TAG ="TAG_DEBUG";

    public static void logDebug(String message){
        Log.d(DEBUG_TAG, message);
    }
    public static void logError(String message){
        Log.e(ERROR_TAG, message);
    }
}
