package com.akozlowski.doomvision.ui.listView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by akozlowski on 16/08/15.
 */
public class CustomAdapter extends ArrayAdapter<Item> {
    private final Context context;
    private final int resourceID;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.resourceID = resource;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public int getViewTypeCount() {
        return Item.ItemType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, convertView, parent);
    }
}
