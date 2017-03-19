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
        createNonNull(filter.getPostId(), builder.mPostId);
        createNonNull(filter.getUserId(), builder.mUserId);
        createNonNull(filter.getInReplyTo(), builder.mInReplyTo);

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
        return params instanceof CommentParams && super.equals(params);
    }

    /**
     * Comment params builder
     */
    public static class Builder extends BaseBuilder {
        private String mUserId;
        private String mInReplyTo;
        private String mPostId;

        /**
         * Get comments from this user
         *
         * @param userId result for the user
         */
        public void setUserId(String userId) {
            this.mUserId = userId;
        }

        /**
         * Get comment that was replied to this comment
         *
         * @param inReplyTo id of the parent comment
         */
        public void setInReplyTo(String inReplyTo) {
            this.mInReplyTo = inReplyTo;
        }

        /**
         * Get comment that was replied to this post
         *
         * @param postId id of the post
         */
        public void setPostId(String postId) {
            this.mPostId = postId;
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
