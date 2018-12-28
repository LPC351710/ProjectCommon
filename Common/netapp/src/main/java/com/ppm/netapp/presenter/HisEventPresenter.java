package com.ppm.netapp.presenter;

import com.ppm.netapp.model.HisEvent;
import com.ppm.netapp.network.HttpUtils;
import com.ppm.netapp.network.callback.JsonCallBack;

import java.util.HashMap;
import java.util.Map;

public class HisEventPresenter {

    public void getHisEventList() {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = new HashMap<>();
        httpUtils.sendGetRequest("", params, new JsonCallBack<HisEvent>() {
            @Override
            public void success(HisEvent event) {
            }

            @Override
            public void fail(int code, String err) {
            }
        });
    }
}
