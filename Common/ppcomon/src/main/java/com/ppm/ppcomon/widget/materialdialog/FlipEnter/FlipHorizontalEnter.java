package com.ppm.ppcomon.widget.materialdialog.FlipEnter;

import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ppm.ppcomon.widget.materialdialog.BaseAnimatorSet;

public class FlipHorizontalEnter extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                // ObjectAnimator.ofFloat(view, "rotationY", -90, 0));
                ObjectAnimator.ofFloat(view, "rotationY", 90, 0));
    }
}
