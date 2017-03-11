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

public class BaseParams extends HashMap<String, String> {
    @NonNull
    static final Factory FACTORY = APIFactory.getInstance().getFactory();

    final void createNonNull(@Nullable String key, @Nullable String value) {
        if (key != null && value != null) {
            put(key, value);
        }
    }

    static class BaseBuilder {
        @NonNull
        String resultSize = String.valueOf(20);
        String page;
        String publishedAfter;
        String publishedBefore;
        String offset;
        String id;
        String search;

        @NonNull
        public BaseBuilder setResultSize(@IntRange(from = 1, to = 100) int resultSize) {
            this.resultSize = String.valueOf(resultSize);
            return this;
        }

        @NonNull
        public BaseBuilder setPage(@IntRange(from = 1) int page) {
            this.page = String.valueOf(page);
            return this;
        }

        @NonNull
        public BaseBuilder setOffset(@IntRange(from = 1) int offset) {
            this.offset = String.valueOf(offset);
            return this;
        }

        @NonNull
        public BaseBuilder setId(@IntRange(from = 1) int id) {
            this.id = String.valueOf(id);
            return this;
        }

        @NonNull
        public BaseBuilder publishedAfter(String publishedAfter) {
            this.publishedAfter = publishedAfter;
            return this;
        }

        @NonNull
        public BaseBuilder publishedBefore(String publishedBefore) {
            this.publishedBefore = publishedBefore;
            return this;
        }

        @NonNull
        public BaseBuilder search(String search) {
            this.search = search;
            return this;
        }
    }
}
