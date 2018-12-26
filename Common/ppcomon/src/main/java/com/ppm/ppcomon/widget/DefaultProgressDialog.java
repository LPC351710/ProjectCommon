package com.ppm.ppcomon.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.R2;


public class DefaultProgressDialog extends Dialog {
    @BindView(R2.id.iv_icon)
    ImageView ivIcon;
    @BindView(R2.id.tv_msg)
    TextView tvMsg;

    public DefaultProgressDialog(Context context) {
        super(context, R.style.dialog_theme);
    }

    public DefaultProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_hud);
        ButterKnife.bind(this);
    }

    public void setMessage(CharSequence message) {
        if (tvMsg != null && !TextUtils.isEmpty(message)) {
            tvMsg.setText(message);
        }
    }

    /**
     * 显示加载中
     */
    public void showLoadingProgressHUD() {
        showProgressHUD("加载中...");
    }

    /**
     * 显示加载框
     *
     * @param msg
     */
    public void showProgressHUD(String msg) {
        show();
        setMessage(msg);
        final AnimationDrawable spinner = (AnimationDrawable) ivIcon.getDrawable();
        ivIcon.post(new Runnable() {
            @Override
            public void run() {
                spinner.start();
            }
        });
    }
}
