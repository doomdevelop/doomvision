package com.akozlowski.doomvision.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.pojo.Category;
import com.akozlowski.doomvision.pojo.Data;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.util.DebugLog;

import java.util.ArrayList;
import java.util.List;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by akozlowski on 17/08/15.
 */
public class SlidesGalleryFragment extends RoboFragment {
    private static final String TAG = SlidesGalleryFragment.class.getSimpleName();
    @InjectView(R.id.gallery_view_pager)
    private ViewPager viewPager;
    private List<roboguice.fragment.RoboFragment> slides = new ArrayList<>();

    @InjectView(R.id.gallery_image_indicator_layout)
    private LinearLayout indicatorsLayout;
    private List<IndicatorView> circleIndicators = new ArrayList<IndicatorView>();
    private int currentPagePosition = 0;
    public static final String PAGE_INDEX_KEY = "page_index_key";
    @InjectView(R.id.details_description)
    private TextView textViewDescription;
    @InjectView(R.id.details_added_date)
    private TextView textViewAddedDate;
    @InjectView(R.id.details_image_type)
    private TextView textViewImageType;
    @InjectView(R.id.details_categories)
    private TextView textViewCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.slides_gallery_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            currentPagePosition = getArguments().getInt(PAGE_INDEX_KEY);
        }
        createViewPager();
    }


    private void createViewPager() {
        CustomScreenSlideAdapter pagerAdapter = new CustomScreenSlideAdapter(getChildFragmentManager());
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
        Response response = DataManager.getInstance().getResponse();
        if (response == null) {
            return;
        }
        indicatorsLayout.removeAllViews();
        slides.clear();

        for (int i = 0; i < response.getData().size(); i++) {
            createCircleIndicator(i == currentPagePosition, (LinearLayout.LayoutParams) indicatorsLayout.getLayoutParams());
            slides.add(ImageViewPageFragment.createInstance(i));
        }
        viewPager.setOnPageChangeListener(createPageListener());
        viewPager.setCurrentItem(currentPagePosition);
        pagerAdapter.notifyDataSetChanged();
        updateDetails(response.getData().get(currentPagePosition));
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

    private ViewPager.OnPageChangeListener createPageListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                circleIndicators.get(currentPagePosition).setIsSelected(false);
                circleIndicators.get(position).setIsSelected(true);
                currentPagePosition = position;
                updateDetails(DataManager.getInstance().getResponse().getData().get(currentPagePosition));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { /* unused */ }

            @Override
            public void onPageScrollStateChanged(int state) { /* unused */ }
        };
    }

    private void updateDetails(Data data) {
        textViewAddedDate.setText(data.getAddedDate());
        setCategories(data.getCategories());
        textViewDescription.setText(data.getDescription());
        textViewImageType.setText(data.getImageType());

    }

    private void setCategories(List<Category> categories) {
        if (categories == null) return;

        StringBuilder sb = new StringBuilder();
        for (Category category : categories) {
            sb.append(category.getName());
            sb.append(" ");
        }
        textViewCategories.setText(sb.toString());
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
