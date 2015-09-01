package com.akozlowski.doomvision.manager;

import android.content.Context;
import android.os.Build;
import android.util.Base64;

import com.akozlowski.doomvision.BuildConfig;
import com.akozlowski.doomvision.pojo.EchoTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by akozlowski on 16/08/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, manifest = "app/src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.JELLY_BEAN)
public class RestManagerTest {

    private static final String TEXT_TEST = "text test";
    @Mock
    Base64 base64;
    @Mock
    Context mMockContext;
    RestManager restManager;
    private static final String CLIENT_ID = "8f80cdd74ddbef680339";
    private static final String CLIENT_SECRET = "d1cc912295e23669be555fcadb02b4442b53be9e";

    @Before
    public void setUp() throws Exception {
        String source = CLIENT_ID + ":" + CLIENT_SECRET;

        restManager = new RestManager(mMockContext, Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
    }

    @Test
    public void testEcho() {
        EchoTest echoTest = restManager.test(TEXT_TEST);
        Assert.assertNotNull(echoTest);
    }
}