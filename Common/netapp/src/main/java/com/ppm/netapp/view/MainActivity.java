package com.ppm.netapp.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ppm.netapp.R;
import com.ppm.netapp.model.HisEvent;
import com.ppm.netapp.presenter.HisEventPresenter;
import com.ppm.netapp.view.adapter.HisEventListAdapter;
import com.ppm.ppcomon.base.view.activity.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IHisEventView, HisEventPresenter>
        implements IHisEventView {

    @BindView(R.id.list_his_event)
    RecyclerView listHisEvent;

    private HisEventListAdapter hisEventListAdapter;

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

        hisEventListAdapter = new HisEventListAdapter(MainActivity.this);
        listHisEvent.setAdapter(hisEventListAdapter);
        listHisEvent.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onGetHistoryEvent(HisEvent hisEvent) {
        if (hisEvent != null && hisEvent.getResult() != null) {
            hisEventListAdapter.refresh(hisEvent.getResult());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent() {
    }
}
