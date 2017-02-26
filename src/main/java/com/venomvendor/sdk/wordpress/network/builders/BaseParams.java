/*
 * Author		:	VenomVendor
 * Dated		:	26-Feb'17 01:39:50 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.builders;

import com.venomvendor.sdk.wordpress.network.Endpoints;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;

import java.util.HashMap;

public class BaseParams extends HashMap<String, String> {
    static final Endpoints ENDPOINT = APIFactory.getInstance().getEndpoint();

    private BaseParams() {
    }

    final void createNonNull(String key, String value) {
        if (key != null && value != null) {
            put(key, value);
        }
    }

    public static class Builder {
        public BaseParams build() {
            return new BaseParams();
        }
    }
}
