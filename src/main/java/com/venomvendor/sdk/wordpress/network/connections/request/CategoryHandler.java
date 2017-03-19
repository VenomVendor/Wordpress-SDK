/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:57:06 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.request.listener.CategoryRequests;
import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.params.CommentParams;
import com.venomvendor.sdk.wordpress.network.response.categories.GetCategory;

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
public class CategoryHandler<T> extends APIHandler<T> implements CategoryRequests<T> {
    CategoryHandler() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getAllCategories(@NonNull ResponseHandler<GetCategory[]> listener) {
        getCategories(new CommentParams.Builder().build(), listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getCategories(BaseParams params, @NonNull ResponseHandler<GetCategory[]> listener) {
        Call<GetCategory[]> call = ConnectionHandler.getRestClient()
                .getCategories(APIFactory.getInstance().getCategoryUrl(), params);
        if (isNewRequest(call.request(), (ResponseHandler<T>) listener)) {
            getDataFromServer(call);
        }
    }

    /**
     * Fetches Categories from server.
     *
     * @param call Request
     */
    private void getDataFromServer(@NonNull Call<GetCategory[]> call) {
        call.enqueue(new Callback<GetCategory[]>() {
            @Override
            public void onResponse(@NonNull Call<GetCategory[]> call,
                                   @NonNull Response<GetCategory[]> response) {
                handleResponse(call.request(), response);
            }

            @Override
            public void onFailure(@NonNull Call<GetCategory[]> call,
                                  @NonNull Throwable throwable) {
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
    private void handleResponse(@NonNull Request request,
                                @NonNull Response<GetCategory[]> response) {
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
    private boolean hasNoError(@NonNull GetCategory[] response,
                               @NonNull List<ResponseHandler<T>> existingListeners) {
        if (response.length == 1) {
            GetCategory res = response[0];
            if (res.getMessage() != null) {
                String error = "Status Code :" + res.getData().getStatus() + " " + res.getMessage();
                handleError(error, existingListeners);
                return false;
            }
        }
        return true;
    }
}
