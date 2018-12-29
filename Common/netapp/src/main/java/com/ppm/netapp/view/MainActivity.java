package com.ppm.netapp.view;

import android.widget.TextView;
import butterknife.BindView;
import com.ppm.netapp.R;
import com.ppm.netapp.presenter.HisEventPresenter;
import com.ppm.ppcomon.base.view.activity.BaseActivity;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity<IHisEventView, HisEventPresenter>
        implements IHisEventView {

    @BindView(R.id.txt_history)
    TextView txtHistory;

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
        txtHistory.setText(eventStr);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent() {
    }
}
