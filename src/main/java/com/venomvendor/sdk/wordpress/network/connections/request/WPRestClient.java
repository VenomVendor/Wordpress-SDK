/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 12:54:22 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import com.venomvendor.sdk.wordpress.network.response.GetPost;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

interface WPRestClient {
    /**
     * @param url     url of posts
     * @param filters with filters.
     * @return {@link GetPost}
     */
    @GET
    Call<GetPost[]> getPosts(@Url String url, @QueryMap Map<String, String> filters);
}
