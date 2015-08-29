package com.akozlowski.doomvision.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.akozlowski.doomvision.R;

/**
 * Created by akozlowski on 27/08/15.
 */
public class SearchSettingDialog {
    public static AlertDialog createDialog(Activity activity, final DialogInterface.OnClickListener onClick) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final LayoutInflater inflater = activity.getLayoutInflater();
        final View view = inflater.inflate(R.layout.search_dialog, null);
        
        final AlertDialog alertDialog = builder.create();
        builder.setView(view);
        builder.setTitle(R.string.search_setting)
                .setPositiveButton(R.string.btn_ok, onClick)
                .setNegativeButton(R.string.btn_cancel, onClick);
        return builder.show();
    }
}
