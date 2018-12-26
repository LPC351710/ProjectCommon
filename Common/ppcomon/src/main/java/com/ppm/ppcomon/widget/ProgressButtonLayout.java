package com.ppm.ppcomon.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ppm.ppcomon.R;


/**
 * yanweiqiang
 * 2017/11/17.
 */
public class ProgressButtonLayout extends FrameLayout {
    private final String tag = ProgressButtonLayout.class.getSimpleName();
    private final float density = Resources.getSystem().getDisplayMetrics().density;
    private TextView button;
    private View progress;
    private FrameLayout coverLayout;
    private int bgResId;
    private int coverResId;

    private int buttonWidth;
    private Drawable buttonBg;
    private String buttonText;
    private boolean isProgressShowing;
    private Status status = Status.HIDE;

    enum Status {
        SHOWING, SHOWED, HIDING, HIDE
    }

    public ProgressButtonLayout(@NonNull Context context) {
        super(context);
    }

    public ProgressButtonLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressButtonLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressButtonLayout);
        bgResId = ta.getResourceId(R.styleable.ProgressButtonLayout_background, R.drawable.sc_btn);
        coverResId = ta.getResourceId(R.styleable.ProgressButtonLayout_cover, R.drawable.ic_pbl_cover);
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                button = (TextView) view;
            } else {
                progress = view;
                progress.setAlpha(0f);
                progress.setBackgroundResource(bgResId);
            }
        }
    }

    public TextView getButton() {
        return button;
    }

    public View getProgress() {
        return progress;
    }

    public void setBgResId(int bgResId) {
        this.bgResId = bgResId;
    }

    public void setCoverResId(int coverResId) {
        this.coverResId = coverResId;
    }

    public void showProgress() {
        showProgress(100);
    }

    public void showProgress(int duration) {
        if (isProgressShowing) {
            return;
        }
        isProgressShowing = true;
        status = Status.SHOWING;

        final int bw = button.getMeasuredWidth();
        buttonWidth = bw;
        buttonText = button.getText().toString();
        buttonBg = button.getBackground();

        button.setEnabled(false);
        button.setBackgroundResource(bgResId);
        final int pw = progress.getMeasuredWidth();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.setIntValues(bw, pw);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (status != Status.SHOWING) {
                    return;
                }

                int value = (int) animation.getAnimatedValue();
                button.getLayoutParams().width = value;
                button.requestLayout();
                if (value == pw) {
                    button.setText(null);
                    progress.animate().alpha(1f).start();
                    status = Status.SHOWED;
                }
            }
        });
        valueAnimator.start();
    }

    public void hideProgressImmediately() {
        isProgressShowing = false;
        status = Status.HIDE;
        button.getLayoutParams().width = buttonWidth;
        button.requestLayout();
        button.setBackgroundDrawable(buttonBg);
        button.setText(buttonText);
        button.setEnabled(true);
        progress.setAlpha(0f);
    }

    public void hideProgress() {
        hideProgress(100);
    }

    public void hideProgress(int duration) {
        if (!isProgressShowing) {
            return;
        }
        isProgressShowing = false;

        final int bw = buttonWidth;
        final int pw;

        if (status == Status.SHOWING) {
            pw = button.getMeasuredWidth();
        } else {
            pw = progress.getMeasuredWidth();
        }

        button.setBackgroundDrawable(buttonBg);
        status = Status.HIDING;
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.setIntValues(pw, bw);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (status != Status.HIDING) {
                    return;
                }
                int value = (int) animation.getAnimatedValue();
                button.getLayoutParams().width = value;
                button.requestLayout();
                if (value == bw) {
                    float translateDistance = buttonWidth / 100f;
                    TranslateAnimation translateAnimation = new TranslateAnimation(-translateDistance, translateDistance, 0, 0);
                    translateAnimation.setDuration(50);
                    translateAnimation.setRepeatCount(4);
                    translateAnimation.setRepeatMode(Animation.REVERSE);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            /*status = Status.HIDE;
                            button.setBackgroundDrawable(buttonBg);
                            button.setEnabled(true);*/
                            hideProgressImmediately();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    startAnimation(translateAnimation);
                }
            }
        });
        progress.animate().alpha(0f).start();
        valueAnimator.start();
    }

    public void showCover(View rootView, CoverCallback coverCallback) {
        showCover(rootView, 350, coverCallback);
    }

    public void showCover(final View rootView, int duration, final CoverCallback coverCallback) {
        removeCover(rootView);
        ViewGroup parent = (ViewGroup) rootView;
        float sh = getResources().getDisplayMetrics().heightPixels;
        int pw = progress.getMeasuredWidth();
        int ph = progress.getMeasuredHeight();
        int[] location = new int[2];
        progress.getLocationInWindow(location);
        coverLayout = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(pw / 2, ph / 2);
        ImageView cover = new ImageView(getContext());
        cover.setLayoutParams(layoutParams);
        cover.setImageResource(coverResId);
        cover.setX(location[0] + (progress.getMeasuredWidth() - cover.getMeasuredWidth()) / 4);
        cover.setY(location[1] + (progress.getMeasuredHeight() - cover.getMeasuredHeight()) / 4);
        coverLayout.addView(cover);
        ViewGroup.LayoutParams parentParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parent.addView(coverLayout, parent.getChildCount(), parentParams);
        cover.setAlpha(1f);
        progress.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        cover.animate().setDuration(duration).scaleX(sh / pw * 4).scaleY(sh / ph * 4).alpha(1).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                coverCallback.onCovered();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    public void hideCover() {
        coverLayout.setVisibility(GONE);
    }

    public void removeCover(View rootView) {
        ViewGroup parent = (ViewGroup) rootView;
        if (coverLayout != null && coverLayout.getParent() != null) {
            parent.removeView(coverLayout);
        }
    }

    private int getAlphaColor(float percent, int color) {
        return ((int) (0xff * percent) << 24) & color;
    }

    public interface CoverCallback {
        void onCovered();
    }
}