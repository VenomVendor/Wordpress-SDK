/*
 * Author		:	VenomVendor
 * Dated		:	18-Mar'17 09:54:58 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress.network.params;

import com.venomvendor.sdk.wordpress.WPRootTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test cases for request params.
 */
public class ParamsTest extends WPRootTest {

    @BeforeClass
    public static void setUp() {
        if (!isSetUpDone) {
            initSDK();
        }
    }

    @Test
    public void postParamsTest() {
        PostsParams.Builder postParams = new PostsParams.Builder();
        postParams.setCategory("Mobile");
        postParams.setSlug("Android");
        postParams.setId(1);
        postParams.setPage(5);
        postParams.setOffset(10);
        postParams.setResultSize(20);
        postParams.setSearch("Video");
        postParams.setPublishedBefore("2017-03-18T13:18:35");
        postParams.setPublishedAfter("2017-03-16T13:18:35");

        postParams.setPublishedBefore(null);

        PostsParams params = postParams.build();

        Assert.assertEquals(params.size(), 8);
        Assert.assertEquals(params.get("categories"), "Mobile");
        Assert.assertEquals(params.get("slug"), "Android");
        Assert.assertEquals(params.get("per_page"), "20");
        Assert.assertEquals(params.get("search"), "Video");
        Assert.assertEquals(params.get("offset"), "10");

        Assert.assertNotNull(params.get("page"));

        Assert.assertNull(params.get("before"));

        Assert.assertEquals(params.clone(), params);
        Assert.assertTrue(params.clone().equals(params));
    }

    @Test
    public void commentParamsTest() {
        CommentParams.Builder commentParams = new CommentParams.Builder();
        commentParams.setId(1);
        commentParams.setPage(5);
        commentParams.setOffset(10);
        commentParams.setResultSize(20);
        commentParams.setSearch("Mario");
        commentParams.setPublishedBefore("2017-03-18T13:18:35");
        commentParams.setPublishedAfter("2017-03-16T13:18:35");

        commentParams.setPostId("12345");
        commentParams.setInReplyTo("653");
        commentParams.setUserId("007");

        commentParams.setPublishedAfter(null);

        CommentParams params = commentParams.build();

        Assert.assertEquals(params.size(), 9);
        Assert.assertEquals(params.get("offset"), "10");
        Assert.assertEquals(params.get("page"), "5");
        Assert.assertEquals(params.get("per_page"), "20");
        Assert.assertEquals(params.get("before"), "2017-03-18T13:18:35");
        Assert.assertEquals(params.get("search"), "Mario");
        Assert.assertEquals(params.get("post"), "12345");
        Assert.assertEquals(params.get("author"), "007");

        Assert.assertNotNull(params.get("include"));

        Assert.assertNull(params.get("after"));

        Assert.assertEquals(params.clone(), params);
        Assert.assertTrue(params.clone().equals(params));
    }
}
