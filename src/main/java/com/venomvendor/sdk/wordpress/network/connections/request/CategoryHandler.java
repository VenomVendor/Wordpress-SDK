/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:57:06 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.params.CommentParams;
import com.venomvendor.sdk.wordpress.network.response.categories.GetCategory;
import com.venomvendor.sdk.wordpress.network.util.HttpStatus;

import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class CategoryHandler<T> extends APIHandler<T> {
    CategoryHandler() {
        super();
    }

    @Override
    public void getAllCategories(@NonNull ResponseHandler<T> listener) {
        getCategories(new CommentParams.Builder().build(), listener);
    }

    @Override
    public void getCategories(BaseParams params, @NonNull ResponseHandler<T> listener) {
        Call<GetCategory[]> call = ConnectionHandler.getRestClient()
                .getCategories(APIFactory.getInstance().getCategoryUrl(), params);
        if (isNewRequest(call.request(), listener)) {
            getDataFromServer(call);
        }
    }

    private void getDataFromServer(@NonNull Call<GetCategory[]> call) {
        call.enqueue(new Callback<GetCategory[]>() {
            @Override
            public void onResponse(@NonNull Call<GetCategory[]> call, @NonNull Response<GetCategory[]> response) {
                handleResponse(call.request(), response);
            }

            @Override
            public void onFailure(@NonNull Call<GetCategory[]> call, @NonNull Throwable throwable) {
                handlerFailure(call.request(), throwable);
            }
        });
    }

    private void handleResponse(@NonNull Request request, @NonNull Response<GetCategory[]> response) {
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

    private boolean hasNoError(@NonNull GetCategory[] response, @NonNull List<ResponseHandler<T>> existingListeners) {
        if (response.length == 1) {
            GetCategory res = response[0];
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
