/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:56:48 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress;

import android.util.Base64;
import android.util.Log;

import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import java.io.UnsupportedEncodingException;

import okhttp3.internal.Util;

public final class WordpressSDK {
    private static final String TAG = WordpressSDK.class.getSimpleName();
    private static boolean initialized;

    private WordpressSDK() {
    }

    public static void initialize() throws WordpressException {
        if (initialized) {
            throw new WordpressException("SDK already initialized");
        }
        try {
            init();
            initialized = true;
        } catch (UnsupportedEncodingException ex) {
            throw new WordpressException("Error decoding encrypted json. " + ex.getMessage());
        }
    }

    private static void init() throws UnsupportedEncodingException {
        System.loadLibrary("wordpress-android");

        byte[] decodedBytes = Base64.decode(endPoints(), Base64.DEFAULT);
        String endpointsJson = new String(decodedBytes, Util.UTF_8.name());
        if (BuildConfig.DEBUG) {
            Log.d(TAG, endpointsJson);
            Log.d(TAG, cpu());
        }
    }

    private static native String endPoints();

    private static native String cpu();
}
