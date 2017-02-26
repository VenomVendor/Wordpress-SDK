/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:48:48 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.util.ArrayMap;
import android.util.Base64;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.internal.Util;

abstract class APIHandler<T> implements WordpressRequests<T> {
    final Map<String, List<ResponseHandler<T>>> mListenerQueue;

    APIHandler() {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            mListenerQueue = Collections.synchronizedMap(new ArrayMap<String,
                    List<ResponseHandler<T>>>());
        } else {
            mListenerQueue = Collections.synchronizedMap(new HashMap<String,
                    List<ResponseHandler<T>>>());
        }
    }

    final void handleError(String error, List<ResponseHandler<T>> existingListeners) {
        for (ResponseHandler<T> listener : existingListeners) {
            listener.onResponse(null, new WordpressException(error));
        }
    }

    final boolean isNewRequest(Request request, @NonNull ResponseHandler<T> listener) {
        String listenerKey = getListenerKey(request);
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners == null) {
            existingListeners = new ArrayList<>(1);
            existingListeners.add(listener);
            mListenerQueue.put(listenerKey, existingListeners);
            return true;
        }
        existingListeners.add(listener);
        return false;
    }

    final String getListenerKey(Request request) {
        return base64Encrypt(request.tag().toString()
                + request.headers().toString()
                + String.valueOf(request.body()));
    }

    final void handlerFailure(Request request, Throwable throwable) {
        String listenerKey = getListenerKey(request);
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners != null) {
            handleError(throwable.getLocalizedMessage(), existingListeners);
        }
    }

    private String base64Encrypt(String data) {
        try {
            return Base64.encodeToString(data.getBytes(Util.UTF_8.name()), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            return data;
        }
    }
}
