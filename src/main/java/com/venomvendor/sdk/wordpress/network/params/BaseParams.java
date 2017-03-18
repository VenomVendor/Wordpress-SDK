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
    static class BaseBuilder {
        private String mPage;
        private String mOffset;
        private String mId;
        private String mSearch;
        private String mPublishedAfter;
        private String mPublishedBefore;
        @NonNull
        private String resultSize = String.valueOf(MIN_RESULTS);

        /**
         * Get current page.
         *
         * @return current page between 1 to {@link Integer#MAX_VALUE}
         */
        @Nullable
        protected String getPage() {
            return mPage;
        }

        /**
         * Set Current page min value is 1
         *
         * @param page current page
         */
        @NonNull
        public BaseBuilder setPage(@IntRange(from = 1) int page) {
            this.mPage = String.valueOf(page);
            return this;
        }

        /**
         * Get offset results
         */
        @Nullable
        protected String getOffset() {
            return mOffset;
        }

        /**
         * Set first X results to be ignored.
         *
         * @param offset between 1 to {@link Integer#MAX_VALUE}
         */
        @NonNull
        public BaseBuilder setOffset(@IntRange(from = 1) int offset) {
            this.mOffset = String.valueOf(offset);
            return this;
        }

        /**
         * Get post/comment id.
         */
        @Nullable
        protected String getId() {
            return mId;
        }

        /**
         * Get results for only current id.
         *
         * @param id unique id of post or comment
         */
        @NonNull
        public BaseBuilder setId(@IntRange(from = 1) int id) {
            this.mId = String.valueOf(id);
            return this;
        }

        /**
         * Get number of results.
         */
        @NonNull
        protected String getResultSize() {
            return resultSize;
        }


        /**
         * Set number of results in response
         *
         * @param resultSize number of results between 1 & 100
         */
        @NonNull
        public BaseBuilder setResultSize(@IntRange(from = 1, to = MAX_RESULTS) int resultSize) {
            this.resultSize = String.valueOf(resultSize);
            return this;
        }

        /**
         * Get Date published after
         */
        @Nullable
        protected String getPublishedAfter() {
            return mPublishedAfter;
        }

        /**
         * Get the results published after
         *
         * @param publishedAfter Date format in ISO 8601
         */
        @NonNull
        public BaseBuilder setPublishedAfter(String publishedAfter) {
            this.mPublishedAfter = publishedAfter;
            return this;
        }


        /**
         * Get Date published before
         */
        @Nullable
        protected String getPublishedBefore() {
            return mPublishedBefore;
        }

        /**
         * Get the results published before
         *
         * @param publishedBefore Date format in ISO 8601
         */
        @NonNull
        public BaseBuilder setPublishedBefore(String publishedBefore) {
            this.mPublishedBefore = publishedBefore;
            return this;
        }

        /**
         * Get current search string.
         *
         * @return null or searched query
         */
        @Nullable
        protected String getSearch() {
            return mSearch;
        }

        /**
         * Searches this keyword in post/comments
         *
         * @param search search query
         */
        @NonNull
        public BaseBuilder setSearch(String search) {
            this.mSearch = search;
            return this;
        }
    }
}
