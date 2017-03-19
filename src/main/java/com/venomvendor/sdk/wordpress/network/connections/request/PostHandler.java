/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:44:41 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.request.listener.PostRequests;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.params.PostsParams.Builder;
import com.venomvendor.sdk.wordpress.network.response.posts.GetPost;

import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Manages all post calls &amp; callbacks
 *
 * @param <T> Callback object
 */
public class PostHandler<T> extends APIHandler<T> implements PostRequests<T> {
    PostHandler() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getRecentPosts(@NonNull ResponseHandler<GetPost[]> listener) {
        getPosts(new Builder().build(), listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getPosts(BaseParams params, @NonNull ResponseHandler<GetPost[]> listener) {
        Call<GetPost[]> call = ConnectionHandler.getRestClient()
                .getPosts(APIFactory.getInstance().getPostsUrl(), params);
        if (isNewRequest(call.request(), (ResponseHandler<T>) listener)) {
            getDataFromServer(call);
        }
    }

    /**
     * Fetches Posts from server.
     *
     * @param call Request
     */
    private void getDataFromServer(@NonNull Call<GetPost[]> call) {
        call.enqueue(new Callback<GetPost[]>() {
            @Override
            public void onResponse(@NonNull Call<GetPost[]> call,
                                   @NonNull Response<GetPost[]> response) {
                handleResponse(call.request(), response);
            }

            @Override
            public void onFailure(@NonNull Call<GetPost[]> call, @NonNull Throwable throwable) {
                handlerFailure(call.request(), throwable);
            }
        });
    }

    /**
     * Handles response from server & notifies the callbacks.
     *
     * @param request  Request url & data
     * @param response Response from server
     */
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

    /**
     * Checks if response has error within data & handles if error is present
     *
     * @param response          response object form server.
     * @param existingListeners callbacks
     * @return true if there are no errors.
     */
    private boolean hasNoError(@NonNull GetPost[] response,
                               @NonNull List<ResponseHandler<T>> existingListeners) {
        if (response.length == 1) {
            GetPost res = response[0];
            if (res.getMessage() != null) {
                String error = "Status Code :" + res.getData().getStatus() + " " + res.getMessage();
                handleError(error, existingListeners);
                return false;
            }
        }
        return true;
    }
}
