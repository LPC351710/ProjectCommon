package com.ppm.ppcomon.base.model;


import com.ppm.ppcomon.base.view.intf.IAccountInfo;
import com.ppm.ppcomon.net.HttpClient;
import okhttp3.FormBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by lpc on 2017/8/28.
 */
public class BaseBiz {

    private IAccountInfo accountInfo;

    protected HttpClient mHttpClient;

    public HttpClient getClient() {
        return mHttpClient;
    }

    public BaseBiz() {
        mHttpClient = new HttpClient();
    }

    public void setAccountInfo(IAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    protected Map<String, String> getCommonParams() {
        Map<String, String> commonParams = new HashMap<>();
        commonParams.put("device_type", "2");
        return commonParams;
    }

    protected FormBody.Builder getCommonRequestBody() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("device_type", "2");
        return builder;
    }
}
