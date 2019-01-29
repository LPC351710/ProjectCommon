package com.ppm.netapp.view;

import android.content.Intent;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import butterknife.BindView;
import com.ppm.netapp.R;
import com.ppm.netapp.model.HisEvent;
import com.ppm.netapp.presenter.HisEventPresenter;
import com.ppm.netapp.view.adapter.HisEventListAdapter;
import com.ppm.ppcomon.base.view.activity.BaseActivity;
import com.ppm.ppcomon.widget.adapter.BaseRVAdapter;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity<IHisEventView, HisEventPresenter>
        implements IHisEventView, BaseRVAdapter.OnItemClickListener {

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
        hisEventListAdapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView parent, View view, int position, Object data) {
        Intent intent = new Intent(MainActivity.this, EventDetailsActivity.class);
        if (data instanceof HisEvent.ResultBean) {
            HisEvent.ResultBean resultBean = (HisEvent.ResultBean) data;
            intent.putExtra("event_id", resultBean.get_id());
        }
        startActivity(intent);
    }
}
