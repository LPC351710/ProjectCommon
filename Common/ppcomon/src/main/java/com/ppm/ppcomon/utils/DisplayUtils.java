package com.ppm.ppcomon.utils;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import com.ppm.ppcomon.base.app.App;

import java.lang.reflect.Method;


public class DisplayUtils {

    public static int getStatusBarHeight() {
        int height = 0;
        int resourceId = App.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = App.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    public static int getActionBarHeight() {
        // Calculate ActionBar height
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (App.getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, App.getContext().getResources()
                    .getDisplayMetrics());
        }
        if (actionBarHeight == 0) {
            actionBarHeight = 45;
        }
        return actionBarHeight;
    }

    public static int dp2px(float dp) {
        return (int) (dp * getDensity() + 0.5F);
    }

    public static int px2dip(float px) {
        return (int) (px / getDensity() + 0.5F);
    }

    private static float density = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;


    public static float getDensity() {
        if (density <= 0F) {
            density = App.getContext().getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int getScreenWidth() {
        if (widthPixels <= 0) {
            widthPixels = App.getContext().getResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight() {
        if (heightPixels <= 0) {
            heightPixels = App.getContext().getResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }

    /**
     * 获取控件的高度
     */
    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度
     */
    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     */
    private static void calculateViewMeasure(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(w, h);
    }

    /**
     * 通过反射，获取包含虚拟键的高度
     *
     * @return
     */
    public static int getVirtualKeyHeight(Display display) {
        int dpi = 0;
        if (display == null) {
            return dpi;
        }

        Point point = new Point();
        display.getSize(point);
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dpi - point.y;
    }
}
