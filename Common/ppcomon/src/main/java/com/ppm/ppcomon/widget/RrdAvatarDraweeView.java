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

public class RrdAvatarDraweeView extends SimpleDraweeView {

    public RrdAvatarDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        setup();
    }

    public RrdAvatarDraweeView(Context context) {
        super(context);
        setup();
    }

    public RrdAvatarDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public RrdAvatarDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    public RrdAvatarDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    private void setup() {
        setScaleType(ImageView.ScaleType.FIT_XY);
        GenericDraweeHierarchy hierarchy = getHierarchy();
        hierarchy.setFadeDuration(300);
        hierarchy.setPlaceholderImage(R.mipmap.head_pic_default);
        hierarchy.setFailureImage(R.mipmap.head_pic_default);
        setHierarchy(hierarchy);
    }
}
