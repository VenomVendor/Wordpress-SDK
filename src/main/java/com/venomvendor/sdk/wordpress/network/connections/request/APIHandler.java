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

import com.venomvendor.sdk.wordpress.network.connections.request.listener.ListenerHandler;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.internal.Util;

/**
 * Handles all api response
 *
 * @param <T> Type of listener
 */
abstract class APIHandler<T> implements ListenerHandler<T> {
    /**
     * Holds reference of all requests sent.
     */
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

    /**
     * Creates {@link WordpressException} from error string
     *
     * @param error             Message in {@link WordpressException}
     * @param existingListeners callback for errors
     */
    final void handleError(String error, @NonNull List<ResponseHandler<T>> existingListeners) {
        for (ResponseHandler<T> listener : existingListeners) {
            listener.onResponse(null, new WordpressException(error));
        }
    }

    /**
     * Checks if current request is same as any of pending request.
     *
     * @param request  current network request
     * @param listener callback for the request.
     * @return true if queued is same as current request
     */
    final boolean isNewRequest(@NonNull Request request, @NonNull ResponseHandler<T> listener) {
        String listenerKey = getListenerKey(request);
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners == null) {
            existingListeners = new ArrayList<>(1);
            if (listener != null) {
                existingListeners.add(listener);
            }
            mListenerQueue.put(listenerKey, existingListeners);
            return true;
        }
        if (listener != null) {
            existingListeners.add(listener);
        }
        return false;
    }

    /**
     * Generates Unique key from request
     *
     * @param request current request
     * @return unique key
     */
    @NonNull
    final String getListenerKey(@NonNull Request request) {
        return base64Encrypt(request.url().toString()
                + request.method()
                + request.tag().toString()
                + request.headers().toString()
                + String.valueOf(request.body()));
    }

    /**
     * Handles failure response, sends error message.
     *
     * @param request   current request
     * @param throwable Exception caused
     */
    final void handlerFailure(@NonNull Request request, @NonNull Throwable throwable) {
        String listenerKey = getListenerKey(request);
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners != null) {
            handleError(throwable.getLocalizedMessage(), existingListeners);
        }
    }

    /**
     * Generate encrypted {@link Base64#encodeToString(byte[], int)}
     *
     * @param data string to be encrypted
     * @return Hashed string or same data if encoding fails
     */
    @NonNull
    private String base64Encrypt(@NonNull String data) {
        try {
            return Base64.encodeToString(data.getBytes(Util.UTF_8.name()), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            return data;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(@NonNull ResponseHandler<T> listener) {
        for (List<ResponseHandler<T>> responseHandlers : mListenerQueue.values()) {
            Iterator<ResponseHandler<T>> innerListeners = responseHandlers.iterator();
            while (innerListeners.hasNext()) {
                if (innerListeners.next() == listener) {
                    innerListeners.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(@NonNull ResponseHandler<T> listener) {
        boolean removed = false;
        for (List<ResponseHandler<T>> responseHandlers : mListenerQueue.values()) {
            Iterator<ResponseHandler<T>> innerListeners = responseHandlers.iterator();
            while (innerListeners.hasNext()) {
                if (innerListeners.next() == listener) {
                    innerListeners.remove();
                    removed = true;
                }
            }
        }
        return removed;
    }
}
