/*
 * Author		:	VenomVendor
 * Dated		:	12-Mar'17 10:47:10 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */
package com.venomvendor.sdk.wordpress;

import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.exceptions.WordpressException;

import org.junit.Ignore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Base class for all test cases
 */
public class WPRootTest {
    static final String DOMAIN = "VenomVendor.com";
    static final boolean IS_SECURE = true;
    public static boolean isSetUpDone;

    /**
     * Read & return's endpoint json
     */
    @Ignore
    static String getJson() throws IOException {
        File file = new File("src/test/assets/Factory.json");
        if (!file.exists()) {
            file = new File("Wordpress-SDK/src/test/assets/Factory.json");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String text;
        while ((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }
        br.close();
        fr.close();
        return sb.toString();
    }

    /**
     * Initializes SDK
     */
    public static void initSDK() {
        try {
            // unInitializedTest
            APIFactory.getInstance().getFactory();
        } catch (WordpressException ex) {
            assertEquals(ex.getMessage(), "API not yet configured.");
        }

        String invalidJson = "{ key : value }";
        try {
            // Wrong Initialization test.
            WordpressSDK.initConfig(DOMAIN, IS_SECURE, invalidJson);
        } catch (WordpressException ex) {
            assertEquals(ex.getMessage(), "Invalid json " + invalidJson);
        }

        try {
            WordpressSDK.initConfig(DOMAIN, IS_SECURE, getJson());
            isSetUpDone = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
