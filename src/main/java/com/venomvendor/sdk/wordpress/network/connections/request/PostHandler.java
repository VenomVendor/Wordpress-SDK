/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:44:41 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.util.Log;

import com.venomvendor.sdk.wordpress.network.builders.PostsParams;
import com.venomvendor.sdk.wordpress.network.connections.response.PostsHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.response.GetPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PostHandler extends CommentHandler {

    PostHandler() {
        super();
    }

    public void getRecentPosts(PostsHandler listener) {
        getRecentPosts(new PostsParams.Builder().build(), listener);
    }

    public void getRecentPosts(PostsParams params, PostsHandler listener) {
        Call<GetPost[]> postsCall = ConnectionHandler.getRestClient()
                .getPosts(APIFactory.getInstance().getPostsUrl(), params);
        postsCall.enqueue(new Callback<GetPost[]>() {
            @Override
            public void onResponse(Call<GetPost[]> call, Response<GetPost[]> response) {
                Log.d("PostsHandler", "response.body().length:" + response.body().length);
            }

            @Override
            public void onFailure(Call<GetPost[]> call, Throwable t) {

            }
        });
    }
}
