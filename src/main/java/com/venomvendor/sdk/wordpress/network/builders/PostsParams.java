/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:34:36 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.builders;

import com.venomvendor.sdk.wordpress.network.request.FetchPost;

public class PostsParams extends BaseParams {
    private PostsParams(Builder builder) {
        FetchPost filter = FACTORY.getFilter().getFetchPost();
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
        return params instanceof PostsParams && super.equals(params);
    }

    public static class Builder extends BaseBuilder {
        private String slug;
        private String category;

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public PostsParams build() {
            return new PostsParams(this);
        }
    }
}
