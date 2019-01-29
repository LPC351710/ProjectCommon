package com.ppm.ppcomon.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoTouchRecyclerView extends RecyclerView {

    public NoTouchRecyclerView(Context context) {
        super(context);
    }

    public NoTouchRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
