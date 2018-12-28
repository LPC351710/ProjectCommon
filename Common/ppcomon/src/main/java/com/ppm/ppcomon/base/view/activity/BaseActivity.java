package com.ppm.ppcomon.base.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.base.presenter.BasePresenter;
import com.ppm.ppcomon.base.view.intf.IBaseView;
import com.ppm.ppcomon.widget.CommonStatusBar;
import com.ppm.ppcomon.widget.DefaultProgressDialog;
import com.ppm.ppcomon.widget.ErrorHintView;
import com.ppm.ppcomon.widget.actionbar.BaseActionBar;
import com.ppm.ppcomon.widget.swipelayout.app.SwipeBackActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * @author lpc 16/6/30.
 */
@SuppressLint("Registered")
public abstract class BaseActivity<V extends IBaseView, T extends BasePresenter<V>> extends
        SwipeBackActivity implements IBaseView {

    private BaseActionBar mBaseActionBar;
    protected DefaultProgressDialog mProgressHUD;
    private ErrorHintView mErrorHintView;
    private Handler mHandler = new Handler();

    private Unbinder unbinder;
    protected T mPresenter;

    protected String mPageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        getSwipeBackLayout().setEnableGesture(true);
        unbinder = ButterKnife.bind(this);

        if (mPresenter != null) {
            //noinspection unchecked
            mPresenter.attachView((V) this);
        }

        getParameters();
        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    protected int getLayoutId() {
        return 0;
    }

    protected T createPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);

        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }

        super.onDestroy();
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化控件
     */
    protected void initView() {
        mBaseActionBar = new BaseActionBar(this);
        mErrorHintView = new ErrorHintView(this, mHandler, R.mipmap.ic_default_error, getString(R
                .string.default_hint_error));
        mProgressHUD = new DefaultProgressDialog(this);

        setStatusBar();
    }

    protected void setStatusBar() {
        CommonStatusBar.setStatusBarByColorActionBar(this, Color.WHITE);
    }

    protected void setStatusBarBlack() {
        CommonStatusBar.setStatusBarByColorActionBar(this, Color.BLACK);
    }

    /**
     * google分析 页面跟踪标题自定义
     */
    protected String getGATraceTitle() {
        return "";
    }

    /**
     * 传递的参数处理
     */
    protected void getParameters() {
    }

    @Override
    public BaseActionBar getBaseActionBar() {
        return mBaseActionBar;
    }

    @Override
    public DefaultProgressDialog getProgressHUD() {
        return mProgressHUD;
    }

    @Override
    public ErrorHintView getErrorHintView() {
        return mErrorHintView;
    }

    @Override
    public void showErrorHintView(int errorCode) {
        mErrorHintView.showErrorHintView(errorCode);
    }

    @Override
    public void showErrorHintView(int resId, String msg) {
        mErrorHintView.showErrorHintView(resId, msg);
    }

    @Override
    public Handler getHandler() {
        return this.mHandler;
    }

    @Subscribe
    public void onEvent() {
    }
}
