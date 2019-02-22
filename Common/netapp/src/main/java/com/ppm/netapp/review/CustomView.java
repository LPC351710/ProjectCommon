package com.ppm.netapp.review;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class CustomView extends ViewGroup {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void init() {
        //
        offsetLeftAndRight(20);
        offsetTopAndBottom(20);

        //
        layout(1, 1, 1, 1);

        //
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin = getLeft() + 1;
        layoutParams.topMargin = getTop() + 1;
        setLayoutParams(layoutParams);

        //scrollBy 最终调用 ScrollTo
        scrollBy(1, 1);
        scrollTo(1, 1);

        //
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(this, "translationX", 0, 1f);

        //Scroller 配合 View.computeScroll(); 弹性滑动
        scroller = new Scroller(getContext());
        scroller.startScroll(1, 1, 1, 1);

        velocityTracker = VelocityTracker.obtain();
        velocityTracker.computeCurrentVelocity(1000);

        //获取水平方向滑动速率
        velocityTracker.getXVelocity();

        //获取垂直方向滑动速率
        velocityTracker.getYVelocity();

        //重置
        velocityTracker.clear();

    }

    private Scroller scroller;

    private VelocityTracker velocityTracker;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {  //如果滑动还没结束 中断此次滑动
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //demo
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }
}
