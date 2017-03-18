/*
 * Author		:	VenomVendor
 * Dated		:	12-Mar'17 10:46:33 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress;

import android.support.annotation.NonNull;

import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class APIFactoryTest extends WPRootTest {
    @BeforeClass
    public static void setUp() {
        if (!isSetUpDone) {
            initSDK();
        }
    }

    @Test
    public void instanceTest() {
        Assert.assertNotNull(APIFactory.getInstance());
    }

    @Test
    public void factoryTest() {
        Assert.assertNotNull(APIFactory.getInstance().getFactory());
    }

    @Test
    public void setFactoryTest() {
        try {
            APIFactory.getInstance().setFactory(null);
        } catch (WordpressException ex) {
            Assert.assertEquals(ex.getMessage(), "API Cannot be configured.");
        }
    }

    @Test
    public void updateFactoryTest() {
        try {
            APIFactory.getInstance().setFactory(new Factory());
        } catch (WordpressException ex) {
            Assert.assertEquals(ex.getMessage(), "API Already configured.");
        }
    }

    @Test
    public void locationTest() {
        String wpLocation = getDomain();
        Assert.assertEquals(wpLocation, APIFactory.getInstance().getWPLocation());
    }

    @Test
    public void baseUrlTest() {
        String wpLocation = getDomain();
        wpLocation += "/wp-json/wp/v2/";
        Assert.assertEquals(wpLocation, APIFactory.getInstance().getBaseUrl());
    }

    @Test
    public void postUrlTest() {
        String wpLocation = getDomain();
        wpLocation += "/wp-json/wp/v2/";
        wpLocation += "posts/";
        Assert.assertEquals(wpLocation, APIFactory.getInstance().getPostsUrl());
    }

    @Test
    public void commentsUrlTest() {
        String wpLocation = getDomain();
        wpLocation += "/wp-json/wp/v2/";
        wpLocation += "comments/";
        Assert.assertEquals(wpLocation, APIFactory.getInstance().getCommentsUrl());
    }

    @Test
    public void CategoryTest() {
        String wpLocation = getDomain();
        wpLocation += "/wp-json/wp/v2/";
        wpLocation += "categories/";
        Assert.assertEquals(wpLocation, APIFactory.getInstance().getCategoryUrl());
    }

    @NonNull
    private String getDomain() {
        Factory factory = APIFactory.getInstance().getFactory();
        String wpLocation = "";
        if (factory.isSecure()) {
            wpLocation += "https://";
        } else {
            wpLocation += "http://";
        }
        wpLocation += "www." + DOMAIN;
        return wpLocation;
    }
}
