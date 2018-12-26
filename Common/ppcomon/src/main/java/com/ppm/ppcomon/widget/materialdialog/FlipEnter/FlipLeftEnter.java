package com.ppm.ppcomon.widget.materialdialog.FlipEnter;

import android.util.DisplayMetrics;
import android.view.View;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ppm.ppcomon.widget.materialdialog.BaseAnimatorSet;

public class FlipLeftEnter extends BaseAnimatorSet {
    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();

        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "rotationY", 90, 0),//
                ObjectAnimator.ofFloat(view, "translationX", -200 * dm.density, 0), //
                ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1));
    }
}
