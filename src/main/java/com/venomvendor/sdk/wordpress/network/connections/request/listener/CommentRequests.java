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
import com.venomvendor.sdk.wordpress.network.response.comments.GetComment;

/**
 * Get List of posts from server
 *
 * @param <T> Type of Collection
 */
public interface CommentRequests<T> extends ListenerHandler<T> {

    public static final String IDENTIFIER = "CommentRequests";

    /**
     * Get list of recent comments
     *
     * @param listener callback
     */
    void getRecentComments(@NonNull ResponseHandler<GetComment[]> listener);

    /**
     * Get list of comments with filter applied
     *
     * @param params   filters
     * @param listener callback
     */
    void getComments(BaseParams params, @NonNull ResponseHandler<GetComment[]> listener);
}
