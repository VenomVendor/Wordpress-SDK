/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 04:24:09 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.connections.response;

import com.venomvendor.sdk.wordpress.network.response.GetPost;

public interface PostsHandler extends ResponseHandler {
    void onSuccess(GetPost[] posts);
}
