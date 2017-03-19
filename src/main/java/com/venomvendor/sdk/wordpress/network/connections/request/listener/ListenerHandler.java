/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 07:20:45 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request.listener;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;

/**
 * Handler for managing request listeners
 *
 * @param <T>
 */
public interface ListenerHandler<T> {

    /**
     * Removes single listener from cached list of listeners
     *
     * @param listener listener to be removed
     */
    boolean remove(@NonNull ResponseHandler<T> listener);

    /**
     * Removes all listeners of same instance from list of listeners
     *
     * @param listener listener to be removed
     */
    boolean removeAll(@NonNull ResponseHandler<T> listener);
}
