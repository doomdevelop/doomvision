package com.akozlowski.doomvision.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.util.DebugLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by akozlowski on 17/08/15.
 */
public class GalleryFragmentLandscape extends RoboFragment {
    private static final String TAG = GalleryFragmentLandscape.class.getSimpleName();
    @InjectView(R.id.gallery_image_view_pager)
    private ViewPager viewPager;
    @Inject
    private DataManager dataManager;
    private List<roboguice.fragment.RoboFragment> slides = new ArrayList<>();

    @InjectView(R.id.gallery_image_indicator_layout)
    private LinearLayout indicatorsLayout;
    private List<IndicatorView> circleIndicators = new ArrayList<IndicatorView>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.gallery_fragment_landscape, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        createViewPager();
    }

    private void createViewPager() {
        CustomScreenSlideAdapter pagerAdapter = new CustomScreenSlideAdapter(getActivity().getSupportFragmentManager());
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
        Response response = dataManager.getResponse();
        if (response == null) {
            return;
        }
        indicatorsLayout.removeAllViews();
        slides.clear();

        for (int i = 0; i < response.getData().size(); i++) {
            createCircleIndicator(true, (LinearLayout.LayoutParams) indicatorsLayout.getLayoutParams());
            slides.add(ImageViewPageFragment.createInstance(i));
        }
    }

    private void createCircleIndicator(boolean isSelected, LinearLayout.LayoutParams params) {
        DebugLog.d(TAG + " createCircleIndicator()..isSelected: " + isSelected);
        IndicatorView circleIndicatorView = new IndicatorView(getActivity(), R.color.getting_started_circle_indicator_stroke_color);
        indicatorsLayout.addView(circleIndicatorView);
        circleIndicatorView.getLayoutParams().width = (int) getActivity().getResources().getDimension(R.dimen.view_pager_page_width);//40;
        circleIndicatorView.getLayoutParams().height = (int) getActivity().getResources().getDimension(R.dimen.view_pager_page_height);//40;
        circleIndicatorView.setIsSelected(isSelected);
        circleIndicators.add(circleIndicatorView);
    }

    class CustomScreenSlideAdapter extends FragmentStatePagerAdapter {

        public CustomScreenSlideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return slides.get(position);
        }

        @Override
        public int getCount() {
            return slides.size();
        }
    }

}
