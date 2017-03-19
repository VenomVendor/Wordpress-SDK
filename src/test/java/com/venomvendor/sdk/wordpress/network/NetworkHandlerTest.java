/*
 * Author		:	VenomVendor
 * Dated		:	19-Mar'17 06:15:51 PM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress.network;

import com.venomvendor.sdk.wordpress.WPRootTest;

import org.junit.BeforeClass;

public class NetworkHandlerTest extends WPRootTest {

    @BeforeClass
    public static void setUp() {
        if (!isSetUpDone) {
            initSDK();
        }
    }
}
