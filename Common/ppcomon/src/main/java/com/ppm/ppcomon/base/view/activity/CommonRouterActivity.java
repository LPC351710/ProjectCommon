package com.ppm.ppcomon.base.view.activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ppm.ppcomon.R;

public class CommonRouterActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_router;
    }

    @Override
    protected void initView() {
        super.initView();

        findViewById(R.id.click).setOnClickListener(v -> ARouter.getInstance().build("test/activity").navigation());
    }
}
