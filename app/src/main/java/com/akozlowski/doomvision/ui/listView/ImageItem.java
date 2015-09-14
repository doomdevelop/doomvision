package com.akozlowski.doomvision.ui.listView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.ui.ListViewGalleryFragment;
import com.akozlowski.doomvision.ui.SlidesGalleryFragment;
import com.akozlowski.doomvision.util.DebugLog;
import com.squareup.picasso.Picasso;

/**
 * Created by and on 14.09.15.
 */
public class ImageItem implements Item {
    public String getUrl() {
        return url;
    }

    public int getIndex() {
        return index;
    }

    final private String url;
    final private int index;
    final private FragmentActivity activity;

    public static class ViewItemHolder {
        ViewItemHolder(ImageView imageView) {
            this.imageView = imageView;
        }

        public final ImageView imageView;
    }

    public ImageItem(FragmentActivity activity,String url, int index) {
        this.activity = activity;
        this.url = url;
        this.index = index;

    }

    @Override
    public int getViewType() {
        return Item.ItemType.values().length;
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        ViewItemHolder viewItemHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_item, parent, false);
            viewItemHolder = new ViewItemHolder((ImageView) convertView.findViewById(R.id.image));
            convertView.setTag(viewItemHolder);
        }
        viewItemHolder = (ViewItemHolder) convertView.getTag();
        Picasso.with(activity).load(url).error(R.mipmap.ic_launcher).into(viewItemHolder.imageView);
        viewItemHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt(SlidesGalleryFragment.PAGE_INDEX_KEY, index);
                SlidesGalleryFragment slidesGalleryFragment = new SlidesGalleryFragment();
                slidesGalleryFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container, slidesGalleryFragment, SlidesGalleryFragment.class.getSimpleName()).addToBackStack(null).commit();
            }
        });
        return convertView;
    }
}

