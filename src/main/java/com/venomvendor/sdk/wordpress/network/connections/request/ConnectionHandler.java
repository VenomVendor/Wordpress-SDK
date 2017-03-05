/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 12:58:31 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venomvendor.sdk.wordpress.BuildConfig;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

final class ConnectionHandler {
    private static Retrofit mRetrofit;
    private static WPRestClient mRestClient;

    private ConnectionHandler() {
    }

    private static Retrofit getRetrofit() {
        // Create REST adapter.
        if (mRetrofit == null) {
            final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.addInterceptor(new HeaderInterceptor());

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(new LoggingInterceptor());
            }

            retrofitBuilder.client(builder.build());
            retrofitBuilder.baseUrl(APIFactory.getInstance().getBaseUrl());
            retrofitBuilder.addConverterFactory(JacksonConverterFactory.create(getObjectMapper()));
            mRetrofit = retrofitBuilder.build();
        }

        return mRetrofit;
    }

    static WPRestClient getRestClient() {
        if (mRestClient == null) {
            mRestClient = getRetrofit().create(WPRestClient.class);
        }
        return mRestClient;
    }

    @NonNull
    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Headers moreHeaders = request.headers().newBuilder()
//                    .set(API_KEY, getSecret())
                    .build();
            request = request.newBuilder().headers(moreHeaders).build();
            return chain.proceed(request);
        }
    }

    private static class LoggingInterceptor implements Interceptor {
        private static final String TAG = "LoggingInterceptor";

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            String requestLog = String.format("Sending request to %s with body %n%s & headers %n%s",
                    request.url(), request.body(), request.headers());
            Log.d(TAG, requestLog);

            final long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            final long t2 = System.nanoTime();

            String bodyString = response.body().string().trim();
            String responseLog = String.format(Locale.ENGLISH,
                    "Received response from %s in %.1fms %n%s %n%s",
                    response.request().url(), (t2 - t1) / 1e6d, bodyString, response.headers());

            Log.d(TAG, responseLog);

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), bodyString))
                    .build();
        }
    }
}
