package com.ppm.netapp.view;

import com.ppm.netapp.R;
import com.ppm.netapp.presenter.HisEventPresenter;
import com.ppm.ppcomon.base.view.activity.BaseActivity;

public class MainActivity extends BaseActivity<IHisEventView, HisEventPresenter>
        implements IHisEventView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected HisEventPresenter createPresenter() {
        return new HisEventPresenter();
    }

    @Override
    protected void initView() {
        super.initView();
        mPresenter.getHisEventList();
    }

    @Override
    public void onGetHistoryEvent(String eventStr) {

    }

}
