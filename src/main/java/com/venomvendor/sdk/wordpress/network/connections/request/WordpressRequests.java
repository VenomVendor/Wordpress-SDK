/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:07:28 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.builders.PostsParams;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;

public interface WordpressRequests<T> {
    void getRecentPosts(@NonNull ResponseHandler<T> listener);

    void getPosts(PostsParams params, @NonNull ResponseHandler<T> newListener);
}
