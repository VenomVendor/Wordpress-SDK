/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:56:48 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.internal.Util;

public final class WordpressSDK {
    private static final String TAG = WordpressSDK.class.getSimpleName();
    private static boolean mInitialized;

    private WordpressSDK() {
    }

    /**
     * Initializes SDK, this should be called before using any methods of SDK
     *
     * @param domain  Url of domain. Ex: <b>example.com</b>
     * @param isHttps is SSL enabled.
     * @throws WordpressException if domain name is <b>null</b> or starts with any of the following
     *                            <b>www</b> | <b>http:</b> | <b>https:</b>
     */
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

    /**
     * Initializes SDK by decrypting configuration from auto generated JNI
     *
     * @param domain   Url of domain. Ex: <b>example.com</b>
     * @param isSecure is SSL enabled.
     * @throws UnsupportedEncodingException if decoding fails
     * @see Factory
     */
    private static void init(String domain, boolean isSecure) throws UnsupportedEncodingException {
        System.loadLibrary("wordpress-android");

        byte[] decodedBytes = Base64.decode(endPoints(), Base64.DEFAULT);
        String endpointJson = new String(decodedBytes, Util.UTF_8.name());

        initConfig(domain, isSecure, endpointJson);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, endpointJson);
            Log.d(TAG, cpu());
        }
    }

    /**
     * Initializes SDK by decrypting configuration
     *
     * @param domain       Url of domain. Ex: <b>example.com</b>
     * @param isSecure     is SSL enabled.
     * @param endpointJson plain configuration containing endpoints & params
     * @see Factory
     */
    private static void initConfig(String domain, boolean isSecure, String endpointJson) {
        try {
            Factory endpoints = getObjectMapper().readValue(endpointJson, Factory.class);
            endpoints.setDomain(domain);
            endpoints.setSecure(isSecure);
            APIFactory.getInstance().setFactory(endpoints);
            mInitialized = true;
        } catch (IOException ignored) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, ignored.getMessage());
            }
            throw new WordpressException("Invalid json " + endpointJson);
        }
    }

    /**
     * Creates {@link ObjectMapper} for converting JSON to Objects
     *
     * @return customized {@link ObjectMapper}
     */
    @NonNull
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    /**
     * Get encrypted/encoded endpoints & params string
     *
     * @return Base64 encoded string
     */
    @NonNull
    private static native String endPoints();

    /**
     * Get architecture of device
     *
     * @return JNI architecture being consumed
     */
    @NonNull
    private static native String cpu();
}
