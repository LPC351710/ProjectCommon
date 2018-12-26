package com.ppm.ppcomon.widget.window;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.R2;
import com.ppm.ppcomon.utils.AnimationUtils;
import com.ppm.ppcomon.utils.DisplayUtils;
import com.ppm.ppcomon.widget.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class TypeSelectWindow extends PopupWindow {

    @BindView(R2.id.id_flowlayout)
    TagFlowLayout tagFlowLayout;

    @BindView(R2.id.ll_background)
    LinearLayout mLLBackground;

    private int offset;

    private List<TypeBean> mItems = new ArrayList<>();
    private OnItemSelectedListener mListener;
    private FlowTagAdapter flowTagAdapter;

    public interface OnItemSelectedListener {
        void onItemSelected(TypeBean typeBean, int position);
    }

    public void setListener(OnItemSelectedListener mListener) {
        this.mListener = mListener;
    }

    public TypeSelectWindow(Context context, int initPosition) {
        super(context);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View rootView = mLayoutInflater.inflate(R.layout.window_flow_tag, null);
        ButterKnife.bind(this, rootView);

        mLLBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rootView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK)
                    dismiss();
                return false;
            }
        });

        setContentView(rootView);
    }

    public void setItems(List<TypeBean> mItems) {
        this.mItems = mItems;
        flowTagAdapter = new FlowTagAdapter(mItems);
        tagFlowLayout.setAdapter(flowTagAdapter);
        flowTagAdapter.setListener(new FlowTagAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectPosition) {
                dismiss();
                if (mListener != null) {
                    mListener.onItemSelected(TypeSelectWindow.this.mItems.get(selectPosition), selectPosition);
                }
            }
        });
    }

    public void setCurrentPosition(int position) {
        if (flowTagAdapter != null) {
            flowTagAdapter.setCurrentPosition(position);
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        offset = DisplayUtils.getViewMeasuredHeight(anchor);
        mLLBackground.startAnimation(AnimationUtils.createAlphaAnimation(0, 1));
        tagFlowLayout.startAnimation(AnimationUtils.createTranslateAnimation(-offset, 0));
        if (Build.VERSION.SDK_INT >= 24) {   //修复适配问题
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }


    @Override
    public void dismiss() {
        dismissWithAnim();
    }

    private void dismissWithAnim() {
        mLLBackground.startAnimation(AnimationUtils.createAlphaAnimation(1, 0));
        tagFlowLayout.startAnimation(AnimationUtils.createTranslateAnimation(0, -offset));
        getContentView().postDelayed(new Runnable() {
            @Override
            public void run() {
                TypeSelectWindow.super.dismiss();
            }
        }, AnimationUtils.ANIMATION_OUT_TIME);
    }
}
