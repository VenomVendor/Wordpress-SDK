/*
 * Author		:	VenomVendor
 * Dated		:	11-Mar'17 11:53:09 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test cases for SDK
 */
public class WordpressSDKTest {

    @Test(expected = WordpressException.class)
    public void unInitializedTest() {
        APIFactory.getInstance().getFactory();
        Assert.fail();
    }

    public void initializeNullTest() {
        try {
            WordpressSDK.initialize(null, true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
        }
    }

    @Test
    public void initializeWWWTest() {
        try {
            WordpressSDK.initialize("www.VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
        }
    }

    @Test
    public void initializeHttpTest() {
        try {
            WordpressSDK.initialize("http://VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
        }
    }

    @Test
    public void initializeHttpsTest() {
        try {
            WordpressSDK.initialize("https://VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
        }
    }

    @Test
    public void initializeObjectMapper() {
        DeserializationConfig config = WordpressSDK.getObjectMapper().getDeserializationConfig();
        assertTrue(config.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY));
        assertTrue(config.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
        assertTrue(config.hasDeserializationFeatures(DeserializationFeature
                .ACCEPT_SINGLE_VALUE_AS_ARRAY.getMask()));
        assertFalse(config.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }
}
