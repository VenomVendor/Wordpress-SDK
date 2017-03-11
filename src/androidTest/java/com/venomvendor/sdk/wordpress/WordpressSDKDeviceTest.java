/*
 * Author		:	VenomVendor
 * Dated		:	12-Mar'17 02:19:54 AM
 * URL			:	https://www.google.co.in/search?q=VenomVendor
 * Copyright(c):	2017 - Present, VenomVendor.
 * License		:	Apache License Version 2.0
 */

package com.venomvendor.sdk.wordpress;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.venomvendor.sdk.wordpress.network.core.APIFactory;
import com.venomvendor.sdk.wordpress.network.request.Factory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.venomvendor.sdk.wordpress.WordpressSDK.getObjectMapper;
import static junit.framework.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class WordpressSDKDeviceTest {
    private static final String DOMAIN = "VenomVendor.com";
    private static final boolean IS_SECURE = true;

    @BeforeClass
    public static void setUp() {
        WordpressSDK.initialize(DOMAIN, IS_SECURE);
    }

    @Test
    public void isBaseCorrect() {
        assertEquals(APIFactory.getInstance().getFactory().getDomain(), DOMAIN);
        assertEquals(APIFactory.getInstance().getFactory().isSecure(), IS_SECURE);
    }

    @Test
    public void isFactoryCorrect() throws IOException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        InputStream is = context.getAssets().open("Factory.json");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(b)) != -1) {
            bos.write(b, 0, bytesRead);
        }
        byte[] bytes = bos.toByteArray();
        is.close();
        bos.close();

        Factory jniEndpoints = APIFactory.getInstance().getFactory();
        Factory assetEndpoints = getObjectMapper().readValue(bytes, Factory.class);
        assetEndpoints.setDomain(DOMAIN);
        assetEndpoints.setSecure(IS_SECURE);

        String jniEndPointString = getObjectMapper().writeValueAsString(jniEndpoints);
        String assetEndPointString = getObjectMapper().writeValueAsString(assetEndpoints);
        assertEquals(jniEndPointString, assetEndPointString);
    }
}
