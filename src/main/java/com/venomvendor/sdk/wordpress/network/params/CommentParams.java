/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:34:36 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.params;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.request.FetchComment;

/**
 * Request params to get comments
 */
public class CommentParams extends BaseParams {

    private CommentParams(@NonNull Builder builder) {
        FetchComment filter = FACTORY.getFilter().getFetchComment();
        createNonNull(filter.getPostId(), builder.postId);
        createNonNull(filter.getUserId(), builder.userId);
        createNonNull(filter.getInReplyTo(), builder.inReplyTo);
        createNonNull(filter.getId(), builder.getId());
        createNonNull(filter.getResultSize(), builder.getResultSize());
        createNonNull(filter.getPage(), builder.getPage());
        createNonNull(filter.getSearch(), builder.getSearch());
        createNonNull(filter.getPublishedAfter(), builder.getPublishedAfter());
        createNonNull(filter.getPublishedBefore(), builder.getPublishedBefore());
        createNonNull(filter.getOffset(), builder.getOffset());
    }

    @Override
    public boolean equals(Object params) {
        return params instanceof CommentParams && super.equals(params);
    }

    /**
     * Comment params builder
     */
    public static class Builder extends BaseBuilder {
        private String userId;
        private String inReplyTo;
        private String postId;

        /**
         * Get comments from this user
         *
         * @param userId result for the user
         */
        @NonNull
        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Get comment that was replied to this comment
         *
         * @param inReplyTo id of the parent comment
         */
        @NonNull
        public Builder setInReplyTo(String inReplyTo) {
            this.inReplyTo = inReplyTo;
            return this;
        }

        /**
         * Get comment that was replied to this post
         *
         * @param postId id of the post
         */
        @NonNull
        public BaseBuilder setPostId(String postId) {
            this.postId = postId;
            return this;
        }

        /**
         * Build params for comment
         */
        @NonNull
        public CommentParams build() {
            return new CommentParams(this);
        }
    }
}
