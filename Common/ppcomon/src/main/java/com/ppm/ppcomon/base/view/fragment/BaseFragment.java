package com.ppm.ppcomon.base.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.ppm.ppcomon.R;
import com.ppm.ppcomon.base.presenter.BasePresenter;
import com.ppm.ppcomon.base.view.intf.IBaseView;
import com.ppm.ppcomon.widget.DefaultProgressDialog;
import com.ppm.ppcomon.widget.ErrorHintView;
import com.ppm.ppcomon.widget.actionbar.BaseActionBar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public abstract class BaseFragment<V extends IBaseView, T extends BasePresenter<V>> extends DialogFragment implements IBaseView {

    private BaseActionBar mBaseActionBar;
    private ErrorHintView mErrorHintView;
    protected DefaultProgressDialog mProgressHUD;
    private Handler mHandler = new Handler();

    protected T mPresenter;

    protected View rootView;
    private Unbinder unbinder;

    protected String mPageTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();

        if (mPresenter != null) {
            //noinspection unchecked
            mPresenter.attachView((V) this);
        }

        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getBaseActionBar() != null && getBaseActionBar().getTvTitle() != null) {
            mPageTitle = getBaseActionBar().getTvTitle().getText().toString();
        }

        if (TextUtils.isEmpty(mPageTitle)) {
            mPageTitle = this.getClass().getSimpleName();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), null, false);
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView == null ? super.onCreateView(inflater, container, savedInstanceState) : rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBaseActionBar = new BaseActionBar(this);
        mErrorHintView = new ErrorHintView(getContext(), mHandler, R.mipmap.ic_default_error, getString(R.string.default_hint_error));
        mProgressHUD = new DefaultProgressDialog(getContext());
        initView();
    }

    protected int getLayoutId() {
        return 0;
    }

    protected T createPresenter() {
        return null;
    }

    protected void initView() {
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
        if (getView() != null) {
            mErrorHintView.showErrorHintView(getView(), errorCode);
        }
    }

    @Override
    public void showErrorHintView(int resId, String msg) {
        if (getView() != null) {
            mErrorHintView.showErrorHintView(getView(), resId, msg);
        }
    }

    @Override
    public Handler getHandler() {
        return mHandler;
    }

    @Subscribe
    public void onEvent() {

    }

}
