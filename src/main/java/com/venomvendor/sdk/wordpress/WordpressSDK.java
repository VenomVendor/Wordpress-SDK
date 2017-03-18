/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:56:48 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Base64;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import java.io.IOException;

import okhttp3.internal.Util;

public final class WordpressSDK {
    private static final String TAG = WordpressSDK.class.getSimpleName();
    private static boolean mInitialized;

    private WordpressSDK() {
    }

    @SuppressWarnings("ConstantConditions")
    public static void initialize(@NonNull String domain, boolean isHttps) {
        if (domain == null || domain.startsWith("www")
                || domain.startsWith("http:") || domain.startsWith("https:")) {
            throw new WordpressException("Wrong domain name, set your domain where wordpress " +
                    "is hosted.\nExample \"VenomVendor.com\" or \"wp.VenomVendor.com\"  or " +
                    "\"VenomVendor.com\\wp \nDo not prefix \"www, http, https\" \"");
        }
        if (mInitialized) {
            throw new WordpressException("SDK already initialized");
        }
        try {
            init(domain, isHttps);
        } catch (IOException ex) {
            throw new WordpressException("Error decoding encrypted json. " + ex.getMessage());
        }
    }

    private static void init(String domain, boolean isSecure) throws IOException {
        System.loadLibrary("wordpress-android");

        byte[] decodedBytes = Base64.decode(endPoints(), Base64.DEFAULT);
        String endpointJson = new String(decodedBytes, Util.UTF_8.name());

        initConfig(domain, isSecure, endpointJson);
    }

    @VisibleForTesting
    public static void initConfig(String domain, boolean isSecure, String endpointJson)
            throws IOException {
        try {
            Factory endpoints = getObjectMapper().readValue(endpointJson, Factory.class);
            endpoints.setDomain(domain);
            endpoints.setSecure(isSecure);
            APIFactory.getInstance().setFactory(endpoints);
            mInitialized = true;
        } catch (IOException ex) {
            throw new IOException("Invalid json " + endpointJson + "\n" + ex.getMessage());
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, endpointJson);
            Log.d(TAG, cpu());
        }
    }

    @NonNull
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    @VisibleForTesting
    @NonNull
    public static native String endPoints();

    @NonNull
    private static native String cpu();
}
