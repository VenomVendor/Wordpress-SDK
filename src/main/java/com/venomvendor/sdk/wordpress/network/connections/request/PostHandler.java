/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:44:41 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.params.PostsParams.Builder;
import com.venomvendor.sdk.wordpress.network.response.posts.GetPost;
import com.venomvendor.sdk.wordpress.network.util.HttpStatus;

import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class PostHandler<T> extends CommentHandler<T> {
    PostHandler() {
        super();
    }

    @Override
    public void getRecentPosts(@NonNull ResponseHandler<T> listener) {
        getPosts(new Builder().build(), listener);
    }

    @Override
    public void getPosts(BaseParams params, @NonNull ResponseHandler<T> listener) {
        Call<GetPost[]> call = ConnectionHandler.getRestClient()
                .getPosts(APIFactory.getInstance().getPostsUrl(), params);
        if (isNewRequest(call.request(), listener)) {
            getDataFromServer(call);
        }
    }

    private void getDataFromServer(@NonNull Call<GetPost[]> call) {
        call.enqueue(new Callback<GetPost[]>() {
            @Override
            public void onResponse(@NonNull Call<GetPost[]> call, @NonNull Response<GetPost[]> response) {
                handleResponse(call.request(), response);
            }

            @Override
            public void onFailure(@NonNull Call<GetPost[]> call, @NonNull Throwable throwable) {
                handlerFailure(call.request(), throwable);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void handleResponse(@NonNull Request request, @NonNull Response<GetPost[]> response) {
        String listenerKey = getListenerKey(request);
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

    private boolean hasNoError(@NonNull GetPost[] response, @NonNull List<ResponseHandler<T>> existingListeners) {
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
}
