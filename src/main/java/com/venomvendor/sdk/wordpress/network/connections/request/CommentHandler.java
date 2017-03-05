/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:48:35 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.connections.response.ResponseHandler;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.params.BaseParams;
import com.venomvendor.sdk.wordpress.network.params.CommentParams.Builder;
import com.venomvendor.sdk.wordpress.network.response.comments.GetComment;
import com.venomvendor.sdk.wordpress.network.util.HttpStatus;

import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class CommentHandler<T> extends CategoryHandler<T> {
    CommentHandler() {
        super();
    }

    @Override
    public void getRecentComments(@NonNull ResponseHandler<T> listener) {
        getComments(new Builder().build(), listener);
    }

    @Override
    public void getComments(BaseParams params, @NonNull ResponseHandler<T> listener) {
        Call<GetComment[]> call = ConnectionHandler.getRestClient()
                .getComments(APIFactory.getInstance().getCommentsUrl(), params);
        if (isNewRequest(call.request(), listener)) {
            getDataFromServer(call);
        }
    }

    private void getDataFromServer(@NonNull Call<GetComment[]> call) {
        call.enqueue(new Callback<GetComment[]>() {
            @Override
            public void onResponse(@NonNull Call<GetComment[]> call, @NonNull Response<GetComment[]> response) {
                handleResponse(call.request(), response);
            }

            @Override
            public void onFailure(@NonNull Call<GetComment[]> call, @NonNull Throwable throwable) {
                handlerFailure(call.request(), throwable);
            }
        });
    }

    private void handleResponse(@NonNull Request request, @NonNull Response<GetComment[]> response) {
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

    private boolean hasNoError(@NonNull GetComment[] response, @NonNull List<ResponseHandler<T>> existingListeners) {
        if (response.length == 1) {
            GetComment res = response[0];
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
