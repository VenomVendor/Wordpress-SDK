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
        createNonNull(filter.getCategory(), builder.mCategory);
        createNonNull(filter.getSlug(), builder.mSlug);

        createNonNull(filter.getId(), builder.mId);
        createNonNull(filter.getPage(), builder.mPage);
        createNonNull(filter.getOffset(), builder.mOffset);
        createNonNull(filter.getSearch(), builder.mSearch);
        createNonNull(filter.getResultSize(), builder.mResultSize);
        createNonNull(filter.getPublishedAfter(), builder.mPublishedAfter);
        createNonNull(filter.getPublishedBefore(), builder.mPublishedBefore);
    }

    @Override
    public boolean equals(Object params) {
        return params instanceof PostsParams && super.equals(params);
    }

    /**
     * Post params builder
     */
    public static class Builder extends BaseBuilder {
        private String mSlug;
        private String mCategory;

        public String getSlug() {
            return mSlug;
        }

        /**
         * Get post only for current slug
         *
         * @param slug id of the slug
         */
        public void setSlug(String slug) {
            this.mSlug = slug;
        }

        public String getCategory() {
            return mCategory;
        }

        /**
         * Get post only for this category
         *
         * @param category category name
         */
        public void setCategory(String category) {
            this.mCategory = category;
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
