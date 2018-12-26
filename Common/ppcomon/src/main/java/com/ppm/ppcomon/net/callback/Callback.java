package com.ppm.ppcomon.net.callback;


import com.alibaba.fastjson.JSONObject;

/**
 * @author lpc on 2017/05/03
 */
public interface Callback {
    void onStart();

    void onSuccess(JSONObject response);

    void onFail(int errorCode, String errorMessage);

    void onStop();
}
