package com.ppm.netapp.presenter;

import com.ppm.netapp.model.HisDetails;
import com.ppm.netapp.model.HisEvent;
import com.ppm.netapp.network.HttpUtils;
import com.ppm.netapp.network.callback.JsonCallBack;
import com.ppm.netapp.network.constant.Constant;
import com.ppm.netapp.view.IHisEventView;
import com.ppm.ppcomon.base.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public class HisEventPresenter extends BasePresenter<IHisEventView> {

    public void getHisEventList() {
        HttpUtils httpUtils = new HttpUtils();
        Map<String, String> params = getCommonParams();
        params.put("month", "1");
        params.put("day", "1");
        httpUtils.sendGetRequest(Constant.GET_EVENT_LIST, params, new JsonCallBack<HisEvent>() {
            @Override
            public void success(HisEvent event) {
                if (event != null) {
                    getView().onGetHistoryEvent(event);
                }
            }

            @Override
            public void fail(int code, String err) {

            }
        });
    }

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
