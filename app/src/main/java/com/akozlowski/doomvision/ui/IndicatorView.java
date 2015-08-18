package com.akozlowski.doomvision.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.util.Density;

/**
 * Created by akozlowski on 17/08/15.
 */
public class IndicatorView extends View {
    private Context context;
    private float RADIUS = 4f;
    private boolean isSelected = false;
    private Paint selectedPaint;
    private Paint normalPaint;
    private int selectedResourceColorID = -1;

    /**
     * @param context
     */
    public IndicatorView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public IndicatorView(Context context, int selectedResourceColorID) {
        super(context);
        this.context = context;
        this.selectedResourceColorID = selectedResourceColorID;
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        RADIUS *= Density.getDensityMultiplier(context);

        selectedPaint = new Paint();
        if (selectedResourceColorID > 0) {
            selectedPaint.setColor(getResources().getColor(selectedResourceColorID));
        } else {
            selectedPaint.setColor(getResources().getColor(R.color.getting_started_circle_indicator_fill_color));
        }

        selectedPaint.setAntiAlias(true);
        selectedPaint.setStyle(Paint.Style.FILL);

        normalPaint = new Paint();
        normalPaint.setColor(getResources().getColor(R.color.getting_started_circle_indicator_page_color));
        normalPaint.setAntiAlias(true);
        normalPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (!isSelected) {
            canvas.drawCircle(width / 2, height / 2, RADIUS, normalPaint);
        } else {
            canvas.drawCircle(width / 2, height / 2, RADIUS, selectedPaint);
        }
    }

    /**
     * @param isSelected
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
        invalidate();
    }
}
