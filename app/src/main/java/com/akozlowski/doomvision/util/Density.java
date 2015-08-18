package com.akozlowski.doomvision.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by akozlowski on 17/08/15.
 */
public class Density {
    public static float getDensityMultiplier(Context context) {
        float densityMultiplier;
        float densityScale = 1.0f / DisplayMetrics.DENSITY_DEFAULT;

        try {
            densityMultiplier = densityScale * context.getResources().getDisplayMetrics().densityDpi;
        } catch (NullPointerException e) {
            DebugLog.e("Nullpointer", e);
            return 1;
        }

        DebugLog.i("DENSITY: multiplier: " + densityMultiplier);

        return densityMultiplier;
    }
}
