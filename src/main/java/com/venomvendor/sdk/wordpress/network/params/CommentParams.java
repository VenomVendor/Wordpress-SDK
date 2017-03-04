/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:34:36 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.params;

import com.venomvendor.sdk.wordpress.network.request.FetchComment;

public class CommentParams extends BaseParams {
    public CommentParams(Builder builder) {
        FetchComment filter = FACTORY.getFilter().getFetchComment();
        createNonNull(filter.getPostId(), builder.postId);
        createNonNull(filter.getUserId(), builder.userId);
        createNonNull(filter.getId(), builder.id);
        createNonNull(filter.getReplies(), builder.replies);
        createNonNull(filter.getInReplyTo(), builder.inReplyTo);
        createNonNull(filter.getResultSize(), builder.resultSize);
        createNonNull(filter.getPage(), builder.page);
        createNonNull(filter.getSearch(), builder.search);
        createNonNull(filter.getPublishedAfter(), builder.publishedAfter);
        createNonNull(filter.getPublishedBefore(), builder.publishedBefore);
        createNonNull(filter.getOffset(), builder.offset);
    }

    @Override
    public boolean equals(Object params) {
        return params instanceof CommentParams && super.equals(params);
    }

    public static class Builder extends BaseBuilder {
        private String userId;
        private String replies;
        private String inReplyTo;
        private String postId;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder replies(String replies) {
            this.replies = replies;
            return this;
        }

        public Builder inReplyTo(String inReplyTo) {
            this.inReplyTo = inReplyTo;
            return this;
        }

        public BaseBuilder postId(String postId) {
            this.postId = postId;
            return this;
        }

        public CommentParams build() {
            return new CommentParams(this);
        }
    }
}
