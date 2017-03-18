/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 12:54:22 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.response.categories.GetCategory;
import com.venomvendor.sdk.wordpress.network.response.comments.GetComment;
import com.venomvendor.sdk.wordpress.network.response.posts.GetPost;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Rest Client which has all endpoints
 */
interface WPRestClient {
    /**
     * Fetches list of posts
     *
     * @param url     full url of posts
     * @param filters with filters.
     * @return requested call {@link Call<GetPost>}
     */
    @NonNull
    @GET
    Call<GetPost[]> getPosts(@Url String url, @QueryMap Map<String, String> filters);

    /**
     * Fetches list of comments
     *
     * @param url     full url of comment
     * @param filters with filters.
     * @return requested call {@link Call<GetComment>}
     */
    @NonNull
    @GET
    Call<GetComment[]> getComments(@Url String url, @QueryMap Map<String, String> filters);

    /**
     * Fetches list of categories
     *
     * @param url     full url of category
     * @param filters with filters.
     * @return requested call {@link Call<GetCategory>}
     */
    @NonNull
    @GET
    Call<GetCategory[]> getCategories(@Url String url, @QueryMap Map<String, String> filters);
}
