/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:28:42 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.core;

import com.venomvendor.sdk.wordpress.network.Endpoints;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

public class APIFactory {
    private static final APIFactory mInstance = new APIFactory();
    private Endpoints mEndpoint;

    private APIFactory() {
    }

    public static APIFactory getInstance() {
        return mInstance;
    }

    public Endpoints getEndpoint() {
        if (mEndpoint == null) {
            throw new WordpressException("API not yet configured.");
        }
        return mEndpoint;
    }

    public void setEndpoint(Endpoints endpoints) {
        if (endpoints == null) {
            throw new WordpressException("API Cannot be configured.");
        }
        if (this.mEndpoint != null) {
            throw new WordpressException("API Already configured.");
        }
        this.mEndpoint = endpoints;
    }

    private String getWPLocation() {
        return mEndpoint.isSecure() ? mEndpoint.getProtocolSecure() :
                mEndpoint.getProtocolDefault() + "www." + mEndpoint.getDomain();
    }

    public String getBaseUrl() {
        return getWPLocation() + mEndpoint.getPath();
    }

    public String getPostsUrl() {
        return getBaseUrl() + mEndpoint.getPosts().getPath();
    }
}
