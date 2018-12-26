package com.ppm.ppcomon.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.ppm.ppcomon.widget.materialdialog.BounceEnter.BounceBottomEnter;
import com.ppm.ppcomon.widget.materialdialog.listener.OnBtnClickL;
import com.ppm.ppcomon.widget.materialdialog.widget.MaterialDialog;


/**
 * 带按钮对话框，snackBar
 *
 * @author lvpengcheng
 */
public class CommonDialogs {

    public void displayMsgWithOneBtnDialog(Activity context, String msg) {
        displayMsgWithOneBtnDialog(context, null, msg, null, false, null, null);
    }

    public void displayMsgWithOneBtnDialog(Activity context, String msg, OnBtnClickL onBtnClickL) {
        displayMsgWithOneBtnDialog(context, null, msg, null, true, onBtnClickL, null);
    }

    public void displayMsgWithOneBtnDialog(Activity context, String title, String msg, boolean cancelable, OnBtnClickL onBtnClickL) {
        displayMsgWithOneBtnDialog(context, title, msg, null, cancelable, onBtnClickL, null);
    }

    public void displayMsgWithOneBtnDialog(Activity context, String title, String msg, OnBtnClickL onBtnClickL, DialogInterface.OnCancelListener onCancelListener) {
        displayMsgWithOneBtnDialog(context, title, msg, null, true, onBtnClickL, onCancelListener);
    }


    /**
     * 一个按钮对话框
     *
     * @param title            标题
     * @param msg              内容
     * @param btnName          按钮名
     * @param onBtnClickL      按键事件，可以为null
     * @param onCancelListener 物理取消事件，可以为null
     */
    public void displayMsgWithOneBtnDialog(Activity context, String title, String msg, String btnName, boolean cancelable,
                                           OnBtnClickL onBtnClickL, DialogInterface.OnCancelListener onCancelListener) {
        final MaterialDialog materialDialog = new MaterialDialog(context);
        materialDialog.setCanceledOnTouchOutside(false);
        materialDialog.btnNum(1)
                .content(msg)
                .title(title)
                .btnText(TextUtils.isEmpty(btnName) ? "确定" : btnName)
                .showAnim(new BounceBottomEnter())
                .show();
        if (onBtnClickL != null) {
            materialDialog.setOnBtnClickL(onBtnClickL);
        } else {
            materialDialog.setOnBtnClickL(new OnBtnClickL() {
                @Override
                public void onBtnClick(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
        }
        if (onCancelListener != null) {
            materialDialog.setOnCancelListener(onCancelListener);
        } else {
            materialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
        }
        materialDialog.setCancelable(cancelable);
        materialDialog.setCanceledOnTouchOutside(false);
    }


    public void displayTwoBtnDialog(Activity context, String title, String leftText, String rightText, String content,
                                    OnBtnClickL leftListener, OnBtnClickL rightListener) {
        displayTwoBtnDialog(context, title, leftText, rightText, content, leftListener, rightListener, null);
    }

    /**
     * 两个按钮的弹框
     *
     * @param leftText      左边按钮的文字
     * @param rightText     右边按钮的文字
     * @param content       提示内容
     * @param leftListener  左边按钮的点击事件
     * @param rightListener 右边按钮的点击事件
     * @param title         标题文字 null(无title)，""(默认title为温馨提示)，String（自定义）
     */
    public void displayTwoBtnDialog(Activity context, String title, String leftText, String rightText, String content,
                                    OnBtnClickL leftListener, OnBtnClickL rightListener, DialogInterface.OnCancelListener listener) {
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.content(content)
                .btnText(null == leftText ? "取消" : leftText, null == rightText ? "确定" : rightText)
                .showAnim(new BounceBottomEnter())
                .show();
        OnBtnClickL defaultListener = new OnBtnClickL() {
            @Override
            public void onBtnClick(DialogInterface dialog) {
                dialog.dismiss();
            }
        };
        dialog.setOnBtnClickL(null == leftListener ? defaultListener : leftListener,
                null == rightListener ? defaultListener : rightListener);
        if (null != listener) {
            dialog.setOnCancelListener(listener);
        }
        if (null == title) {
            dialog.isTitleShow(false);
        } else {
            dialog.isTitleShow(true);
            if (!"".equals(title)) {
                dialog.title(title);
            }
        }
        dialog.setCanceledOnTouchOutside(false);
    }
}
