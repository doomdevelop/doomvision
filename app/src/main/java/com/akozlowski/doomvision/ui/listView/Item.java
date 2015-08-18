package com.akozlowski.doomvision.ui.listView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by akozlowski on 16/08/15.
 */
public interface Item {
    int getViewType();

    View getView(LayoutInflater inflater, View convertView, ViewGroup parent);

    enum ItemType {
        IMAGE;
    }
}
