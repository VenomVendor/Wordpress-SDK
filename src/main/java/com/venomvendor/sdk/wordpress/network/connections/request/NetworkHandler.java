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

import com.venomvendor.sdk.wordpress.network.connections.request.listener.CategoryRequests;
import com.venomvendor.sdk.wordpress.network.connections.request.listener.CommentRequests;
import com.venomvendor.sdk.wordpress.network.connections.request.listener.ListenerHandler;
import com.venomvendor.sdk.wordpress.network.connections.request.listener.PostRequests;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Handles all network operations
 */
public class NetworkHandler {
    private static Map<String, ListenerHandler> mNetworkMap;

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

    /**
     * Get Instance of current network operation
     * <p><b>Ex: To Get RecentPosts</b></p>
     * <pre>
     *     PostRequests req = (PostRequests) NetworkHandler.getInstance(PostRequests.IDENTIFIER);
     *     req.getRecentPosts(new ResponseHandler&lt;GetPost[]>() {
     *         {@literal @}Override
     *         public void onResponse(Response&lt;GetPost[]> response, WordpressException ex) {
     *             if (response != null) {
     *                 // handle response
     *             } else {
     *                 Log.d(TAG, ex.getMessage());
     *             }
     *         });
     *     });
     * </pre>
     *
     * @param identifier must be one of {@link Requests}
     * @return Instance of {@link ListenerHandler} if available else throws
     * {@link WordpressException}
     */
    @NonNull
    public static ListenerHandler getInstance(@Requests String identifier) {
        ListenerHandler instance = mNetworkMap.get(getKey(identifier));

        if (instance != null) {
            return instance;
        }
        switch (identifier) {
            case PostRequests.IDENTIFIER:
                instance = new PostHandler();
                break;
            case CommentRequests.IDENTIFIER:
                instance = new CommentHandler();
                break;
            case CategoryRequests.IDENTIFIER:
                instance = new CategoryHandler();
                break;
            default:
                throw new WordpressException("Unknown request type");
        }

        mNetworkMap.put(getKey(identifier), instance);
        return instance;
    }

    /**
     * Generate unique Key.
     *
     * @param identifier One @{@link Requests}
     * @return unique key
     */
    @NonNull
    private static String getKey(@Requests String identifier) {
        return "REQUEST_" + identifier;
    }

    @Retention(SOURCE)
    @StringDef({
            CategoryRequests.IDENTIFIER,
            CommentRequests.IDENTIFIER,
            PostRequests.IDENTIFIER
    })
    @interface Requests {
    }
}
