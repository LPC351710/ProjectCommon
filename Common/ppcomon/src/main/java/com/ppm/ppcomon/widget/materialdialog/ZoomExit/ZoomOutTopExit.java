package com.ppm.ppcomon.widget.materialdialog.ZoomExit;

import android.view.View;
import android.view.View.MeasureSpec;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ppm.ppcomon.widget.materialdialog.BaseAnimatorSet;

public class ZoomOutTopExit extends BaseAnimatorSet {
    public ZoomOutTopExit() {
        duration = 600;
    }

    @Override
    public void setAnimation(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        int h = view.getMeasuredHeight();

        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "alpha", 1, 1, 0),//
                ObjectAnimator.ofFloat(view, "scaleX", 1, 0.475f, 0.1f),//
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0.475f, 0.1f),//
                ObjectAnimator.ofFloat(view, "translationY", 0, 60, -h));
    }
}
