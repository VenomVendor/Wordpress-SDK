/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:34:36 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.params;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.request.FetchPost;

public class PostsParams extends BaseParams {

    private PostsParams(@NonNull Builder builder) {
        FetchPost filter = FACTORY.getFilter().getFetchPost();
        createNonNull(filter.getCategory(), builder.category);
        createNonNull(filter.getSlug(), builder.slug);
        createNonNull(filter.getId(), builder.getId());
        createNonNull(filter.getPage(), builder.getPage());
        createNonNull(filter.getOffset(), builder.getOffset());
        createNonNull(filter.getSearch(), builder.getSearch());
        createNonNull(filter.getResultSize(), builder.getResultSize());
        createNonNull(filter.getPublishedAfter(), builder.getPublishedAfter());
        createNonNull(filter.getPublishedBefore(), builder.getPublishedBefore());
    }

    @Override
    public boolean equals(Object params) {
        return params instanceof PostsParams && super.equals(params);
    }

    /**
     * Post params builder
     */
    public static class Builder extends BaseBuilder {
        private String slug;
        private String category;

        /**
         * Get post only for this category
         *
         * @param category category name
         */
        @NonNull
        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        /**
         * Get post only for current slug
         *
         * @param slug id of the slug
         */
        @NonNull
        public Builder setSlug(String slug) {
            this.slug = slug;
            return this;
        }

        /**
         * Build params for comment
         */
        @NonNull
        public PostsParams build() {
            return new PostsParams(this);
        }
    }
}
