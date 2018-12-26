package com.ppm.ppcomon.widget.materialdialog.FallEnter;

import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ppm.ppcomon.widget.materialdialog.BaseAnimatorSet;

public class FallEnter extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(duration));
    }
}
