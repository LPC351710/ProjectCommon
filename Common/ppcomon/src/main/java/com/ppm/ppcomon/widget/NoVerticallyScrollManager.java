package com.ppm.ppcomon.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class NoVerticallyScrollManager extends LinearLayoutManager {

    public NoVerticallyScrollManager(Context context, int oritation) {
        super(context, oritation, false);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
