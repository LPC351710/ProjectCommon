package com.ppm.ppcomon.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.ppm.ppcomon.utils.DisplayUtils;

/**
 * @author lpc
 */
public class FloatingImageView extends AppCompatImageView {

    private int mDownX, mDownY;
    private long mPressDownTime;
    private float mClickX, mClickY;

    public FloatingImageView(@NonNull Context context) {
        super(context);
    }

    public FloatingImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingImageView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();

                int offsetX = moveX - mDownX;
                int offsetY = moveY - mDownY;

                int left = getLeft() + offsetX;
                int top = getTop() + offsetY;
                int right = getRight() + offsetX;
                int bottom = getBottom() + offsetY;

                ViewGroup viewGroup = (ViewGroup) getParent();
                if (right <= viewGroup.getWidth()
                        && bottom <= viewGroup.getHeight()
                        && left >= 0 && top >= 0) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();


                Log.d("FloatingImageView", "DownX:" + mDownX + " DownY:" + mDownY
                        + " DownRawX: " + event.getRawX() + " DownRawY: " + event.getRawY());
                mPressDownTime = System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int moveY = (int) event.getY();

                int offsetX = moveX - mDownX;
                int offsetY = moveY - mDownY;

                int left = getLeft() + offsetX;
                int top = getTop() + offsetY;
                int right = getRight() + offsetX;
                int bottom = getBottom() + offsetY;

                ViewGroup viewGroup = (ViewGroup) getParent();

                Log.d("FloatingImageView", "left: " + left + "  top: " + top
                        + "  right: " + right + "  bottom: " + bottom);
                if (right <= viewGroup.getWidth()
                        && bottom <= viewGroup.getHeight()
                        && left >= 0 && top >= 0) {
                    layout(left, top, right, bottom);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (System.currentTimeMillis() - mPressDownTime < 120) {
                    mClickX = event.getRawX();
                    mClickY = event.getRawY();
                    performClick();
                }
                slidToSide();
                break;
        }

        return super.onTouchEvent(event);
    }

    private void slidToSide() {
        float startX = getX();
        ViewGroup viewGroup = (ViewGroup) getParent();
        float endX = startX * 2 + getWidth() > viewGroup.getWidth() ?
                (viewGroup.getWidth() - getWidth()) - DisplayUtils.dp2px(16)
                : DisplayUtils.dp2px(16);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startX, endX);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (Float) animation.getAnimatedValue();
                layout((int) (animatedValue), getTop(),
                        (int) (animatedValue + getWidth()), getBottom());
            }
        });
    }

    public float getClickRawX() {
        return mClickX;
    }

    public float getClickRawY() {
        return mClickY;
    }

    public void showWithAnim() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 0, 1);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    public void dismissWithAnim() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1, 0);
        objectAnimator.setDuration(300);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
