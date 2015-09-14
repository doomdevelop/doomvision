package com.akozlowski.doomvision.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.akozlowski.doomvision.BuildConfig;
import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.pojo.Assets;
import com.akozlowski.doomvision.pojo.Data;
import com.akozlowski.doomvision.pojo.EchoTest;
import com.akozlowski.doomvision.pojo.Preview;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.ui.ListViewGalleryFragment;
import com.akozlowski.doomvision.ui.MainActivity;
import com.akozlowski.doomvision.util.DataCreator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by akozlowski on 16/08/15.
 * manifest = "app/src/main/AndroidManifest.xml"
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class RestManagerTest {

    private static final String TEXT_TEST = "text test";

    private static final String ECHO_RESPONSE  ="ok";
    @Mock
    Base64 base64;
    @Mock
    Context mMockContext;
    Response response;
    RestManager restManager;
    private static final String CLIENT_ID = "8f80cdd74ddbef680339";
    private static final String CLIENT_SECRET = "d1cc912295e23669be555fcadb02b4442b53be9e";

    @Before
    public void setUp() throws Exception {
        String source = CLIENT_ID + ":" + CLIENT_SECRET;

        restManager = new RestManager(mMockContext, Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP));
    }

    @Test
    public void testActivity(){
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);
        assertTrue(activity instanceof FragmentActivity);
    }

    @Test
    public void testEcho() {
        EchoTest echoTest = restManager.test(TEXT_TEST);
        Assert.assertNotNull(echoTest);
        Assert.assertNotNull(echoTest.getText());
        Assert.assertEquals(echoTest.getText(), ECHO_RESPONSE);
    }

    @Test
    public void testSearchImage() {
        Observable<Response> responseObservable= restManager.searchImage("go");
        Assert.assertNotNull(responseObservable);

    }
    @Test
    public void testOnSearch(){
        Activity activity = Robolectric.setupActivity(MainActivity.class);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.search_progress);
        Assert.assertNotNull(progressBar);
        assertFalse(progressBar.getVisibility() == View.VISIBLE);
        ImageView btn = (ImageView)activity.findViewById(R.id.search_btn);
        btn.performClick();
        assertTrue(progressBar.getVisibility() == View.VISIBLE);
    }



}