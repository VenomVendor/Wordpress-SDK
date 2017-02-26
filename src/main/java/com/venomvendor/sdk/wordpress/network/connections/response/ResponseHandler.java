/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:23:47 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.response;

import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import retrofit2.Response;

public interface ResponseHandler<T> {
    void onResponse(Response<T> response, WordpressException ex);
}
