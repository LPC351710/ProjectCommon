package com.ppm.netapp.presenter;

import com.ppm.netapp.model.HisDetails;
import com.ppm.netapp.network.HttpUtils;
import com.ppm.netapp.network.callback.JsonCallBack;
import com.ppm.netapp.network.constant.Constant;
import com.ppm.netapp.view.IDetailsView;
import com.ppm.ppcomon.base.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public class HisDetailsPresenter extends BasePresenter<IDetailsView> {

    public void getHisDetails(String eventId) {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = getCommonParams();
        params.put("id", eventId);
        httpUtils.sendGetRequest(Constant.GET_EVENT_DETAILS, params, new JsonCallBack<HisDetails>() {
            @Override
            public void success(HisDetails data) {

            }

            @Override
            public void fail(int code, String err) {

            }
        });
    }

    protected Map<String, String> getCommonParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key", Constant.KEY);
        params.put("v", "1.0");
        return params;
    }
}
