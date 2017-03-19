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

/**
 * Holds all private data to perform network operations
 *
 * @see Factory
 */
public class APIFactory {
    private static final APIFactory mInstance = new APIFactory();
    @Nullable
    private Factory mFactory;

    private APIFactory() {
    }

    /**
     * Singleton Instance of {@link APIFactory}
     */
    @NonNull
    public static APIFactory getInstance() {
        return mInstance;
    }

    /**
     * Return copy of {@link Factory} if factory is initilized
     *
     * @throws WordpressException in uninitialized
     */
    @NonNull
    public Factory getFactory() {
        if (mFactory == null) {
            throw new WordpressException("API not yet configured.");
        }
        return mFactory;
    }


    /**
     * Update the current factory, this has all the necessary params & endpoint for requests
     * <i>Factory can be updated only once.</i>
     *
     * @param factory Factory to be updated
     * @throws WordpressException if already configured
     */
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

    /**
     * Get root url of wordpress
     */
    @VisibleForTesting
    @NonNull
    public String getWPLocation() {
        return (getFactory().isSecure() ? getFactory().getProtocolSecure() :
                getFactory().getProtocolDefault()) + "www." + getFactory().getDomain();
    }

    /**
     * Get root url of wordpress rest api
     */
    @NonNull
    public String getBaseUrl() {
        return getWPLocation() + getFactory().getPath().getRoot();
    }

    /**
     * Get root url of wordpress rest api for posts
     */
    @NonNull
    public String getPostsUrl() {
        return getBaseUrl() + getFactory().getPath().getPosts();
    }

    /**
     * Get root url of wordpress rest api for comments
     */
    @NonNull
    public String getCommentsUrl() {
        return getBaseUrl() + getFactory().getPath().getComments();
    }

    /**
     * Get root url of wordpress rest api for categories
     */
    @NonNull
    public String getCategoryUrl() {
        return getBaseUrl() + getFactory().getPath().getCategory();
    }
}
