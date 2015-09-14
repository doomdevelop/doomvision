package com.akozlowski.doomvision.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;

import com.akozlowski.doomvision.BuildConfig;
import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.manager.RestManager;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.ui.listView.CustomAdapter;
import com.akozlowski.doomvision.ui.listView.ImageItem;
import com.akozlowski.doomvision.ui.listView.Item;
import com.akozlowski.doomvision.util.DataCreator;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by and on 14.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 21)
public class ListViewGalleryFragmentTest extends TestCase {

    @Mock
    Base64 base64;
    @Mock
    Context mMockContext;
    RestManager restManager;
    Response response;
    FragmentActivity activity;

    public void setUp() throws Exception {
        super.setUp();
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testFragment(){
        response = DataCreator.generateResponse();
        Assert.assertNotNull(response);
        DataManager.getInstance().setResponse(response);
        Assert.assertNotNull(DataManager.getInstance().getResponse());
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        assertNotNull(fragments);
        assertTrue(fragments.size() > 0);
        assertTrue(fragments.size() == 1);
        ListViewGalleryFragment listViewGalleryFragment = (ListViewGalleryFragment) fragments.get(0);
        assertNotNull(listViewGalleryFragment);
        assertTrue(listViewGalleryFragment instanceof ListViewGalleryFragment);

    }

    @Test
    public void testListView(){
        int index = 0;
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        assertNotNull(fragments);
        assertTrue(fragments.size() > 0);
        assertTrue(fragments.size() == 1);
        ListViewGalleryFragment listViewGalleryFragment = (ListViewGalleryFragment) fragments.get(0);
        CustomAdapter customAdapter = (CustomAdapter) listViewGalleryFragment.getListView().getAdapter();
        assertNotNull(customAdapter);
        customAdapter.add(new ImageItem(activity, DataCreator.TEST_IMAGE_URL, index));

        View view = customAdapter.getView(0,null,null);
        assertNotNull(view);
        ImageItem.ViewItemHolder viewItemHolder = (ImageItem.ViewItemHolder) view.getTag();
        assertTrue(viewItemHolder.imageView.getId() == R.id.image);

    }
}