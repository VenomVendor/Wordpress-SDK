/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:39:50 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.builders;

import android.support.annotation.IntRange;

import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import java.util.HashMap;

public class BaseParams extends HashMap<String, String> {
    static final Factory FACTORY = APIFactory.getInstance().getFactory();

    BaseParams() {
    }

    final void createNonNull(String key, String value) {
        if (key != null && value != null) {
            put(key, value);
        }
    }

    static class BaseBuilder {
        String resultSize = String.valueOf(20);
        String page;
        String publishedAfter;
        String publishedBefore;
        String offset;
        String id;
        String search;

        public BaseParams build() {
            return new BaseParams();
        }

        public BaseBuilder setResultSize(@IntRange(from = 1, to = 100) int resultSize) {
            this.resultSize = String.valueOf(resultSize);
            return this;
        }

        public BaseBuilder setPage(@IntRange(from = 1) int page) {
            this.page = String.valueOf(page);
            return this;
        }

        public BaseBuilder setOffset(@IntRange(from = 1) int offset) {
            this.offset = String.valueOf(offset);
            return this;
        }

        public BaseBuilder setId(@IntRange(from = 1) int id) {
            this.id = String.valueOf(id);
            return this;
        }

        public BaseBuilder publishedAfter(String publishedAfter) {
            this.publishedAfter = publishedAfter;
            return this;
        }

        public BaseBuilder publishedBefore(String publishedBefore) {
            this.publishedBefore = publishedBefore;
            return this;
        }

        public BaseBuilder search(String search) {
            this.search = search;
            return this;
        }
    }
}
