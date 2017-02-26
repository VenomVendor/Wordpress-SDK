/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:44:41 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.builders.PostsParams;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.response.GetPost;
import com.venomvendor.sdk.wordpress.network.util.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class PostHandler<T> extends CommentHandler<T> {
    PostHandler() {
        super();
    }

    @Override
    public void getRecentPosts(@NonNull ResponseHandler<T> listener) {
        getPosts(new PostsParams.Builder().build(), listener);
    }

    @Override
    public void getPosts(PostsParams params, @NonNull ResponseHandler<T> newListener) {
        Call<GetPost[]> call = ConnectionHandler.getRestClient()
                .getPosts(APIFactory.getInstance().getPostsUrl(), params);

        String listenerKey = getListenerKey(call.request());
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners == null) {
            existingListeners = new ArrayList<>(1);
            mListenerQueue.put(listenerKey, existingListeners);
            getPostsFromServer(call);
        }

        existingListeners.add(newListener);
    }

    private void getPostsFromServer(Call<GetPost[]> call) {
        call.enqueue(new Callback<GetPost[]>() {
            @Override
            public void onResponse(Call<GetPost[]> call, Response<GetPost[]> response) {
                handleResponse(call, response);
            }

            @Override
            public void onFailure(Call<GetPost[]> call, Throwable throwable) {
                handlerFailure(call, throwable);
            }
        });
    }

    private void handleResponse(Call<GetPost[]> call, Response<GetPost[]> response) {
        String listenerKey = getListenerKey(call.request());
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners != null) {
            if (hasNoError(response.body(), existingListeners)) {
                for (ResponseHandler<T> listener : existingListeners) {
                    listener.onResponse(((Response<T>) response), null);
                }
            }
            mListenerQueue.remove(listenerKey);
        }
    }

    private boolean hasNoError(GetPost[] response, List<ResponseHandler<T>> existingListeners) {
        if (response.length == 1) {
            GetPost res = response[0];
            if (res.getMessage() != null) {
                String error = res.getMessage() + ". " + res.getData().getStatus() + ": "
                        + HttpStatus.getStatusEquivalent(res.getData().getStatus());
                handleError(error, existingListeners);
                return false;
            }
        }
        return true;
    }

    private void handlerFailure(Call<GetPost[]> call, Throwable throwable) {
        String listenerKey = getListenerKey(call.request());
        List<ResponseHandler<T>> existingListeners = mListenerQueue.get(listenerKey);
        if (existingListeners != null) {
            handleError(throwable.getLocalizedMessage(), existingListeners);
        }
    }
}
