package com.ppm.ppcomon.widget;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.animation.LinearInterpolator;

/**
 * yanweiqiang
 * 2018/2/9.
 */

public class CircularProgressDrawable extends Drawable {
    final float density = Resources.getSystem().getDisplayMetrics().density;
    Paint paint;
    private float strokeWidth;
    private int intrinsicWidth;
    private int intrinsicHeight;
    private int startDegree;
    private int sweepDegree;
    private int progressColor;


    public CircularProgressDrawable() {
        super();

        progressColor = Color.parseColor("#AA000000");

        paint = new Paint();
        paint.setColor(progressColor);
        paint.setStrokeWidth(strokeWidth = density * 1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        intrinsicWidth = (int) (25 * density);
        intrinsicHeight = (int) (25 * density);
        startDegree = -90;
        sweepDegree = 0;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int left;
        int top;
        left = top = (int) (strokeWidth / 2);

        RectF rectF = new RectF(left, top, intrinsicHeight - left, intrinsicHeight - top);
        canvas.drawArc(rectF, startDegree, sweepDegree, false, paint);
    }

    @Override
    public int getIntrinsicWidth() {
        return intrinsicWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return intrinsicHeight;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    public void setSweepDegree(int sweepDegree) {
        this.sweepDegree = sweepDegree;
        invalidateSelf();
    }

    public int getSweepDegree() {
        return sweepDegree;
    }

    public int getStartDegree() {
        return startDegree;
    }

    public void setStartDegree(int startDegree) {
        this.startDegree = startDegree;
        invalidateSelf();
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        paint.setColor(progressColor);
    }

    ValueAnimator animator;

    public void startProgress() {
        animator = new ValueAnimator();
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.setIntValues(-90, 270);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();
                startDegree = degree;
                invalidateSelf();
            }
        });
        animator.setRepeatCount(-1);
        animator.start();
    }

    public void stopProgress() {
        startDegree = -90;
        if (animator != null) {
            animator.cancel();
        }
    }
}
