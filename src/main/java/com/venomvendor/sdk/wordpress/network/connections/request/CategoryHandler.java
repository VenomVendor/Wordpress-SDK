/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:57:06 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.builders.BaseParams;
import com.venomvendor.sdk.wordpress.network.builders.CommentParams;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;

abstract class CategoryHandler<T> extends APIHandler<T> {
    CategoryHandler() {
        super();
    }

    @Override
    public void getAllCategories(@NonNull ResponseHandler<T> listener) {
        getCategories(new CommentParams.Builder().build(), listener);
    }

    @Override
    public void getCategories(BaseParams params, @NonNull ResponseHandler<T> listener) {

    }
}
