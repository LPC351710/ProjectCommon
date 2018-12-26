package com.ppm.ppcomon.widget.materialdialog.FlipEnter;

import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ppm.ppcomon.widget.materialdialog.BaseAnimatorSet;

public class FlipVerticalEnter extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                // ObjectAnimator.ofFloat(view, "rotationX", -90, 0));
                ObjectAnimator.ofFloat(view, "rotationX", 90, 0));
    }
}
