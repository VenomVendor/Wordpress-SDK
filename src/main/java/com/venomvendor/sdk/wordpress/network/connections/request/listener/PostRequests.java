/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:07:28 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request.listener;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.response.posts.GetPost;

/**
 * Get List of posts from server
 *
 * @param <T> Type of Collection
 */
public interface PostRequests<T> extends ListenerHandler<T> {

    public static final String IDENTIFIER = "PostRequests";

    /**
     * Get list of recent posts
     *
     * @param listener callback
     */
    void getRecentPosts(@NonNull ResponseHandler<GetPost[]> listener);

    /**
     * Get list of posts with filter applied
     *
     * @param params   filters
     * @param listener callback
     */
    void getPosts(BaseParams params, @NonNull ResponseHandler<GetPost[]> listener);
}
