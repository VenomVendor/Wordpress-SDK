/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:23:47 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.response;

import android.support.annotation.Nullable;

import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import retrofit2.Response;

/**
 * Global response handler.
 * Handles all type of response
 * Every call should pass an instance of @{@link ResponseHandler}
 * of expected {@link java.lang.reflect.Type}
 *
 * @param <T> Response Type
 */
public interface ResponseHandler<T> {
    /**
     * Response from the request
     * Always check of exception before proceeding with response.
     * <pre>
     *     if (ex == null) {
     *         // do something with response
     *     } else {
     *         Log.d(TAG, ex.getMessage());
     *     }
     * </pre>
     * <b> Even better way of handling.</b>
     * <pre>
     *     if (response != null) {
     *         // do something with response
     *     } else if (ex != null){
     *         Log.d(TAG, ex.getMessage());
     *     } else {
     *         Log.d(TAG, "Unknown error.");
     *     }
     * </pre>
     *
     * @param response response or null if any exception
     * @param ex       Emits exception or null if valid response
     */
    void onResponse(@Nullable Response<T> response, @Nullable WordpressException ex);
}
