package com.akozlowski.doomvision.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.pojo.Data;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by akozlowski on 17/08/15.
 */
public class ImageViewPageFragment extends RoboFragment {
    @InjectView(R.id.view_pager_image)
    private ImageView imageView;
    @Inject
    private DataManager dataManager;


    public static final String EXTRA_INDEX_KEY = "extra_index_key";

    public static ImageViewPageFragment createInstance(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INDEX_KEY, index);
        ImageViewPageFragment imageViewPageFragment = new ImageViewPageFragment();
        imageViewPageFragment.setArguments(bundle);
        return imageViewPageFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment_landscape, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null && getArguments().containsKey(EXTRA_INDEX_KEY)) {
            int index = getArguments().getInt(EXTRA_INDEX_KEY);
            Data data = dataManager.getResponse().getData().get(index);
            Picasso.with(getActivity()).load(data.getAssets().getPreview().getUrl()).into(imageView);
        }
    }
}
