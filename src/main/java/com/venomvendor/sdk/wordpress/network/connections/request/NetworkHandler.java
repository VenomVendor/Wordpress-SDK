/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:20:04 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.request;

public class NetworkHandler extends PostHandler {
    private static final NetworkHandler mInstance = new NetworkHandler();

    private NetworkHandler() {
        super();
    }

    public static WordpressRequests getInstance() {
        return mInstance;
    }
}
