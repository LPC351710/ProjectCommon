package com.ppm.ppcomon.base.view.intf;

import android.os.Handler;
import com.ppm.ppcomon.widget.DefaultProgressDialog;
import com.ppm.ppcomon.widget.ErrorHintView;
import com.ppm.ppcomon.widget.actionbar.BaseActionBar;

/**
 * @author by lpc on 2017/8/28.
 */
public interface IBaseView {

    BaseActionBar getBaseActionBar();

    DefaultProgressDialog getProgressHUD();

    ErrorHintView getErrorHintView();

    void showErrorHintView(int errorCode);

    void showErrorHintView(int resId, String msg);

    Handler getHandler();
}
