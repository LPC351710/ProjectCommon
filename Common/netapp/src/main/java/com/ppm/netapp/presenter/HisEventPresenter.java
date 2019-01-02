package com.ppm.netapp.presenter;

import com.ppm.netapp.model.HisEvent;
import com.ppm.netapp.network.HttpUtils;
import com.ppm.netapp.network.callback.JsonCallBack;
import com.ppm.netapp.view.IHisEventView;
import com.ppm.ppcomon.base.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public class HisEventPresenter extends BasePresenter<IHisEventView> {

    public void getHisEventList() {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = new HashMap<>();
        params.put("key","0dbb338d2f0bf8392d1ff773df23a555");
        params.put("v","1.0");
        params.put("month","1");
        params.put("day","1");
        httpUtils.sendGetRequest("http://api.juheapi.com/japi/toh", params, new JsonCallBack<HisEvent>() {
            @Override
            public void success(HisEvent event) {
                if (event != null) {
                    getView().onGetHistoryEvent(event.toString());
                }
            }

            @Override
            public void fail(int code, String err) {

            }
        });
    }
}
