/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:28:42 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;
import com.venomvendor.sdk.wordpress.network.request.Factory;

public class APIFactory {
    private static final APIFactory mInstance = new APIFactory();
    @Nullable
    private Factory mFactory;

    private APIFactory() {
    }

    @NonNull
    public static APIFactory getInstance() {
        return mInstance;
    }

    @NonNull
    public Factory getFactory() {
        if (mFactory == null) {
            throw new WordpressException("API not yet configured.");
        }
        return mFactory;
    }

    @SuppressWarnings("ConstantConditions")
    public void setFactory(@NonNull Factory factory) {
        if (factory == null) {
            throw new WordpressException("API Cannot be configured.");
        }
        if (mFactory != null) {
            throw new WordpressException("API Already configured.");
        }
        mFactory = factory;
    }

    @VisibleForTesting
    @NonNull
    public String getWPLocation() {
        return (getFactory().isSecure() ? getFactory().getProtocolSecure() :
                getFactory().getProtocolDefault()) + "www." + getFactory().getDomain();
    }

    @NonNull
    public String getBaseUrl() {
        return getWPLocation() + getFactory().getPath().getRoot();
    }

    @NonNull
    public String getPostsUrl() {
        return getBaseUrl() + getFactory().getPath().getPosts();
    }

    @NonNull
    public String getCommentsUrl() {
        return getBaseUrl() + getFactory().getPath().getComments();
    }

    @NonNull
    public String getCategoryUrl() {
        return getBaseUrl() + getFactory().getPath().getCategory();
    }
}
