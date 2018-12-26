package com.ppm.ppcomon.base.presenter;

import com.ppm.ppcomon.base.view.activity.BaseActivity;
import com.ppm.ppcomon.base.view.fragment.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * @author by lpc on 2017/8/28.
 */
public class BasePresenter<V> {

    protected WeakReference<V> mView;

    public void attachView(V view) {
        mView = new WeakReference<V>(view);
    }

    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public V getView() {
        if (mView == null) {
            return null;
        }
        return mView.get();
    }

    public BaseActivity getActivity() {
        if (getView() instanceof BaseActivity) {
            return (BaseActivity) getView();
        } else if (getView() instanceof BaseFragment) {
            return (BaseActivity) ((BaseFragment) getView()).getActivity();
        }

        return null;
    }
}
