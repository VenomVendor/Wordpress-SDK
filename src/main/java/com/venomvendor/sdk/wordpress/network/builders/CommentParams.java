/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:34:36 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.builders;

import android.support.annotation.IntRange;

import com.venomvendor.sdk.wordpress.network.request.Filter;

public class CommentParams extends BaseParams {
    private CommentParams(Builder builder) {
        Filter filter = FACTORY.getPosts().getFilter();
        createNonNull(filter.getCategory(), builder.category);
        createNonNull(filter.getPublishedAfter(), builder.publishedAfter);
        createNonNull(filter.getPublishedBefore(), builder.publishedBefore);
        createNonNull(filter.getSearch(), builder.search);
        createNonNull(filter.getResultSize(), builder.resultSize);
        createNonNull(filter.getPage(), builder.page);
        createNonNull(filter.getOffset(), builder.offset);
        createNonNull(filter.getId(), builder.id);
        createNonNull(filter.getSlug(), builder.slug);
    }

    @Override
    public boolean equals(Object params) {
        return params instanceof CommentParams && super.equals(params);
    }

    public static class Builder {
        private String resultSize = String.valueOf(20);
        private String id;
        private String slug;
        private String category;
        private String publishedAfter;
        private String publishedBefore;
        private String search;
        private String page;
        private String offset;

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setPublishedAfter(String publishedAfter) {
            this.publishedAfter = publishedAfter;
            return this;
        }

        public Builder setPublishedBefore(String publishedBefore) {
            this.publishedBefore = publishedBefore;
            return this;
        }

        public Builder setSearch(String search) {
            this.search = search;
            return this;
        }

        public Builder setResultSize(@IntRange(from = 1, to = 100) int resultSize) {
            this.resultSize = String.valueOf(resultSize);
            return this;
        }

        public Builder setPage(@IntRange(from = 1) int page) {
            this.page = String.valueOf(page);
            return this;
        }

        public Builder setOffset(@IntRange(from = 1) int offset) {
            this.offset = String.valueOf(offset);
            return this;
        }

        public Builder setId(@IntRange(from = 1) int id) {
            this.id = String.valueOf(id);
            return this;
        }

        public Builder setSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public CommentParams build() {
            return new CommentParams(this);
        }
    }
}
