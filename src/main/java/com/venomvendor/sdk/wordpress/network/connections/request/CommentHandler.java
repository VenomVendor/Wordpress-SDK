/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:48:35 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.builders.BaseParams;
import com.venomvendor.sdk.wordpress.network.builders.CommentParams.Builder;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;

abstract class CommentHandler<T> extends APIHandler<T> {
    CommentHandler() {
        super();
    }

    @Override
    public void getRecentComments(@NonNull ResponseHandler<T> listener) {
        getComments(new Builder().build(), listener);
    }

    @Override
    public void getComments(BaseParams params, @NonNull ResponseHandler<T> listener) {

    }
}
