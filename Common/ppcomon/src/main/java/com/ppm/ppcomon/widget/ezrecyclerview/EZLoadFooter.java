package com.ppm.ppcomon.widget.ezrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ppm.ppcomon.R;


/**
 * Author dodoca_android.
 * Date 2017/6/5.
 * <p>
 * footer for load more with {@link EZRecyclerView}
 */

class EZLoadFooter {
    private Context context;
    private View view;

    EZLoadFooter(Context context) {
        super();
        this.context = context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.ez_load_footer, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public View getView() {
        return this.view;
    }
}
