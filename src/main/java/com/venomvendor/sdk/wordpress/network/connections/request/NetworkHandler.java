/*
 * Author		:	VenomVendor
 * Dated		:	25-Feb'17 11:20:04 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network.connections.request;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.util.ArrayMap;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class NetworkHandler {

    private static Map<String, APIHandler> mNetworkMap;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mNetworkMap = new ArrayMap<>();
        } else {
            mNetworkMap = new HashMap<>();
        }
    }

    private NetworkHandler() {
        super();
    }

    @NonNull
    public static APIHandler getInstance(@Requests String identifier) {
        APIHandler instance = mNetworkMap.get(identifier);

        if (instance != null) {
            return instance;
        }
        switch (identifier) {
            case RequestFor.POSTS:
                instance = new PostHandler();
                break;
            case RequestFor.COMMENTS:
                instance = new CommentHandler();
                break;
            case RequestFor.CATEGORIES:
                instance = new CategoryHandler();
                break;
            default:
                throw new IllegalArgumentException("Unknown request type");
        }

        mNetworkMap.put(identifier, instance);
        return instance;
    }

    @Retention(SOURCE)
    @StringDef({
            RequestFor.POSTS,
            RequestFor.COMMENTS,
            RequestFor.CATEGORIES
    })
    @interface Requests {
    }

    public class RequestFor {
        public static final String POSTS = "POSTS";
        public static final String COMMENTS = "COMMENTS";
        public static final String CATEGORIES = "CATEGORIES";
    }
}
