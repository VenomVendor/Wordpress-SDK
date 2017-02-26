/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:28:42 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.core;

import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;
import com.venomvendor.sdk.wordpress.network.request.Factory;

public class APIFactory {
    private static final APIFactory mInstance = new APIFactory();
    private Factory mFactory;

    private APIFactory() {
    }

    public static APIFactory getInstance() {
        return mInstance;
    }

    public Factory getFactory() {
        if (mFactory == null) {
            throw new WordpressException("API not yet configured.");
        }
        return mFactory;
    }

    public void setFactory(Factory endpoints) {
        if (endpoints == null) {
            throw new WordpressException("API Cannot be configured.");
        }
        if (this.mFactory != null) {
            throw new WordpressException("API Already configured.");
        }
        this.mFactory = endpoints;
    }

    private String getWPLocation() {
        return mFactory.isSecure() ? mFactory.getProtocolSecure() :
                mFactory.getProtocolDefault() + "www." + mFactory.getDomain();
    }

    public String getBaseUrl() {
        return getWPLocation() + mFactory.getPath().getRoot();
    }

    public String getPostsUrl() {
        return getBaseUrl() + mFactory.getPath().getPosts();
    }

    public String getCommentsUrl() {
        return getBaseUrl() + mFactory.getPath().getComments();
    }
}
