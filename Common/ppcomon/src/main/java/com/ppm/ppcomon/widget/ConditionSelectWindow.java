package com.ppm.ppcomon.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.R2;
import com.ppm.ppcomon.widget.wheelview.LoopView;
import com.ppm.ppcomon.widget.wheelview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ConditionSelectWindow extends PopupWindow {

    private final View rootView;

    @BindView(R2.id.j_optionspicker)
    LoopView mLoopView;

    @BindView(R2.id.txt_title)
    TextView txtTitle;

    private ArrayList<String> mItems = new ArrayList<>();
    private OnPickCompleteListener mListener;

    public interface OnPickCompleteListener {
        void complete(int selectPosition);
    }

    public ConditionSelectWindow(Context context, int initPosition) {
        super(context);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.popwindow_anim_style);

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.window_condition_selected, null);
        ButterKnife.bind(this, rootView);

        //设置是否循环播放
        mLoopView.setNotLoop();
        //滚动监听
        mLoopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
            }
        });
        mLoopView.setViewPadding(500, 0, 500, 0);
        //设置原始数据
        mLoopView.setItems(mItems);
        mLoopView.setInitPosition(initPosition);
        //设置字体大小
        mLoopView.setTextSize(19);
        mLoopView.setTextSizeSelect(24);
        rootView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK)
                    dismiss();
                return false;
            }
        });
        setContentView(rootView);
    }

    public void setInitPosition(int position) {
        if (null != mLoopView) {
            mLoopView.setInitPosition(position);
        }
    }

    public void setOnCompleteListener(OnPickCompleteListener listener) {
        mListener = listener;
    }

    public void setItems(List<String> items) {
        if (null != mLoopView) {
            mLoopView.setItems(items);
        }
    }

    public int currentPosition() {
        return mLoopView == null ? 0 : mLoopView.getSelectedItem();
    }

    @OnClick(R2.id.j_btnSubmit)
    void complete() {
        if (null != mListener) {
            mListener.complete(mLoopView.getSelectedItem());
        }
        dismiss();
    }

    @OnClick(R2.id.btn_canecl)
    void cancel() {
        dismiss();
    }

    public void setTitle(String title) {
        if (txtTitle != null) {
            txtTitle.setText(title);
        }
    }
}
