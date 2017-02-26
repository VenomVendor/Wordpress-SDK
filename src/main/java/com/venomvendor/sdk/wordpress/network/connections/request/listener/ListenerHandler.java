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

public interface ListenerHandler<T> {
    void remove(@NonNull ResponseHandler<T> listener);

    void removeAll(@NonNull ResponseHandler<T> listener);
}
