/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:39:50 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.params;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import java.util.HashMap;

/**
 * Common Request params
 */
public class BaseParams extends HashMap<String, String> {

    @NonNull
    static final Factory FACTORY = APIFactory.getInstance().getFactory();

    private static final int MIN_RESULTS = 20;
    private static final int MAX_RESULTS = 100;

    BaseParams() {
    }

    /**
     * Checks for null in either key or value & stops insertion
     *
     * @param key   params key
     * @param value params value
     */
    final void createNonNull(@Nullable String key, @Nullable String value) {
        if (key != null && value != null) {
            put(key, value);
        }
    }

    /**
     * Common Request params builder
     */
    public static class BaseBuilder {
        String mPage;
        String mOffset;
        String mId;
        String mSearch;
        String mPublishedAfter;
        String mPublishedBefore;
        @NonNull
        String mResultSize = String.valueOf(MIN_RESULTS);

        /**
         * Set Current page min value is 1
         *
         * @param page current page
         */
        public void setPage(@IntRange(from = 1) int page) {
            this.mPage = String.valueOf(page);
        }

        /**
         * Set first X results to be ignored.
         *
         * @param offset between 1 to {@link Integer#MAX_VALUE}
         */
        public void setOffset(@IntRange(from = 1) int offset) {
            this.mOffset = String.valueOf(offset);
        }

        /**
         * Get results for only current id.
         *
         * @param id unique id of post or comment
         */
        public void setId(@IntRange(from = 1) int id) {
            this.mId = String.valueOf(id);
        }

        /**
         * Set number of results in response
         *
         * @param resultSize number of results between 1 &amp; 100
         */
        public void setResultSize(@IntRange(from = 1, to = MAX_RESULTS) int resultSize) {
            this.mResultSize = String.valueOf(resultSize);
        }

        /**
         * Get the results published after
         *
         * @param publishedAfter Date format in ISO 8601
         */
        public void setPublishedAfter(String publishedAfter) {
            this.mPublishedAfter = publishedAfter;
        }

        /**
         * Get the results published before
         *
         * @param publishedBefore Date format in ISO 8601
         */
        public void setPublishedBefore(String publishedBefore) {
            this.mPublishedBefore = publishedBefore;
        }

        /**
         * Searches this keyword in post/comments
         *
         * @param search search query
         */
        public void setSearch(String search) {
            this.mSearch = search;
        }
    }
}
