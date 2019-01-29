package com.ppm.ppcomon.widget.ezrecyclerview;

import android.graphics.Rect;
import androidx.appcompat.widget.RecyclerView;
import android.view.View;

/**
 * Author dodoca_android.
 * Date 2017/6/9.
 */
public class EZLineDecoration extends RecyclerView.ItemDecoration {

    /**
     * @param outRect 边界
     * @param view    recyclerView ItemView
     * @param parent  recyclerView
     * @param state   recycler 内部数据管理
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定边距为1dp
        if (parent.getLayoutManager().canScrollVertically()) {
            outRect.set(0, 0, 0, (int) view.getResources().getDisplayMetrics().density);
        } else {
            outRect.set(0, 0, (int) view.getResources().getDisplayMetrics().density, 0);
        }
    }
}