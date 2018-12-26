package com.ppm.ppcomon.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class NoVerticallyScrollManager extends LinearLayoutManager {

    public NoVerticallyScrollManager(Context context, int oritation) {
        super(context, oritation, false);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
