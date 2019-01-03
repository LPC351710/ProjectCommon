package com.ppm.netapp.view;

import com.ppm.netapp.R;
import com.ppm.netapp.model.HisDetails;
import com.ppm.netapp.presenter.HisDetailsPresenter;
import com.ppm.ppcomon.base.view.activity.BaseActivity;

public class EventDetailsActivity extends BaseActivity<IDetailsView, HisDetailsPresenter>
        implements IDetailsView {

    @Override
    protected HisDetailsPresenter createPresenter() {
        return new HisDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_his_details;
    }

    @Override
    protected void initView() {
        super.initView();
        String eventId = getIntent().getStringExtra("event_id");
        mPresenter.getHisDetails(eventId);
    }

    @Override
    public void onGetHisDetails(HisDetails hisDetails) {

    }
}
