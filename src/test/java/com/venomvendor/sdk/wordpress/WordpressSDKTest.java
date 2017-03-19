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
import com.venomvendor.sdk.wordpress.network.request.Factory;

import org.hamcrest.core.StringContains;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test cases for SDK
 */
public class WordpressSDKTest extends WPRootTest {

    @BeforeClass
    public static void setUp() {
        if (!isSetUpDone) {
            initSDK();
        }
    }

    @Test
    public void reInitilize() {
        try {
            WordpressSDK.initConfig(DOMAIN, IS_SECURE, getJson());
        } catch (WordpressException | IOException ex) {
            assertEquals(ex.getMessage(), "API Already configured.");
        }
    }

    @Test
    public void reInitilizeSDK() {
        try {
            WordpressSDK.initialize(DOMAIN, IS_SECURE);
        } catch (WordpressException ex) {
            assertEquals(ex.getMessage(), "SDK already initialized");
        }
    }

    @Test
    public void isBaseCorrect() {
        assertEquals(APIFactory.getInstance().getFactory().getDomain(), DOMAIN);
        assertEquals(APIFactory.getInstance().getFactory().isSecure(), IS_SECURE);
    }

    @Test
    public void isFactoryCorrect() throws IOException {
        Factory jniEndpoints = APIFactory.getInstance().getFactory();
        Factory assetEndpoints = WordpressSDK.getObjectMapper().readValue(getJson(), Factory.class);
        assetEndpoints.setDomain(DOMAIN);
        assetEndpoints.setSecure(IS_SECURE);

        String jniEndPointString = WordpressSDK.getObjectMapper().writeValueAsString(jniEndpoints);
        String assetEndPointString = WordpressSDK.getObjectMapper()
                .writeValueAsString(assetEndpoints);
        assertEquals(jniEndPointString, assetEndPointString);
    }

    @Test
    public void initializeNullTest() {
        try {
            WordpressSDK.initialize(null, true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
            assertThat(ex.getMessage(), StringContains.containsString("set your domain where"));
        }
    }

    @Test
    public void initializeWWWTest() {
        try {
            WordpressSDK.initialize("www.VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
            assertThat(ex.getMessage(), StringContains.containsString("Do not prefix"));
        }
    }

    @Test
    public void initializeHttpTest() {
        try {
            WordpressSDK.initialize("http://VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
            assertThat(ex.getMessage(), StringContains.containsString("Do not prefix"));
        }
    }

    @Test
    public void initializeHttpsTest() {
        try {
            WordpressSDK.initialize("https://VenomVendor.com", true);
        } catch (WordpressException ex) {
            assertThat(ex.getMessage(), StringContains.containsString("Wrong domain name"));
            assertThat(ex.getMessage(), StringContains.containsString("Do not prefix"));
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
