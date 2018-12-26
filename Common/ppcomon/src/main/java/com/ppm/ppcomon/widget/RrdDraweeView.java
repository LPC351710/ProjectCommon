package com.ppm.ppcomon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ppm.ppcomon.R;

/**
 * yanweiqiang
 * 2018/2/1.
 */

public class RrdDraweeView extends SimpleDraweeView {
    public RrdDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        setup();
    }

    public RrdDraweeView(Context context) {
        super(context);
        setup();
    }

    public RrdDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public RrdDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    public RrdDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    private void setup() {
        setScaleType(ImageView.ScaleType.FIT_XY);
        GenericDraweeHierarchy hierarchy = getHierarchy();
        hierarchy.setFadeDuration(300);
        hierarchy.setPlaceholderImage(R.mipmap.ic_default_photo);
        hierarchy.setFailureImage(R.mipmap.ic_default_photo);
        setHierarchy(hierarchy);
    }
}
