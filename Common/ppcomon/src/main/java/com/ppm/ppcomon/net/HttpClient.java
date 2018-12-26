package com.ppm.ppcomon.net;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ppm.ppcomon.base.app.App;
import com.ppm.ppcomon.net.callback.Callback;
import com.ppm.ppcomon.utils.ExceptionUtil;
import com.ppm.ppcomon.utils.LogUtils;
import com.ppm.ppcomon.utils.NetworkManager;
import com.ppm.ppcomon.widget.BaseToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

import static com.ppm.ppcomon.net.ErrorCode.ERR_CODE_SUCCESS;
import static com.ppm.ppcomon.net.ErrorCode.MAP_ERROR_CODE;


/**
 * @author lpc on 2017/05/03.
 */
public class HttpClient {

    private final String TAG = "HttpClient";

    private boolean showToast = App.IS_DEBUG;

    public void sendGetRequest(String url, final Callback callback) {
        sendGetRequest(url, null, callback);
    }

    public void sendGetRequest(String url, Map<String, String> params, Callback callback) {
        if (null == params) {
            params = new HashMap<>();
        }
        LogUtils.d(TAG, "sendGetRequest: Url=" + url + "\nparams: " + params);
        if (callback != null) {
            callback.onStart();
        }

        if (NetworkManager.isConnectingToInternet(App.getContext())) {
            OkHttpUtils.get().url(url).params(params).build().execute(getCommonCallBack(callback,
                    url));
        } else {
            if (callback != null) {
                callback.onFail(ErrorCode.ERR_CODE_NO_NET, "暂无网络");
                callback.onStop();
            }
        }
    }

    public void sendPostRequest(String url, final Callback callback) {
        sendPostRequest(url, null, callback);
    }

    public void sendPostRequest(String url, Map<String, String> params, final Callback callback) {
        LogUtils.d(TAG, "sendPostRequest: Url=" + url + "\nparams: " + params);

        if (null == params) {
            params = new HashMap<>();
        }

        if (callback != null) {
            callback.onStart();
        }

        if (NetworkManager.isConnectingToInternet(App.getContext())) {
            OkHttpUtils.post().url(url).params(params).build().execute(getCommonCallBack
                    (callback, url));
        } else {
            if (callback != null) {
                callback.onFail(ErrorCode.ERR_CODE_NO_NET, "暂无网络");
                callback.onStop();
            }
        }
    }

    public void sendPutRequest(String url, final Callback callback) {
        sendPutRequest(url, null, callback);
    }

    public void sendPutRequest(String url, RequestBody body, Callback callback) {
        LogUtils.d(TAG, "sendPutRequest: Url=" + url + "\nparams: " + body);
        if (callback != null) {
            callback.onStart();
        }

        if (NetworkManager.isConnectingToInternet(App.getContext())) {
            OkHttpUtils.put().url(url).requestBody(body).build().execute(getCommonCallBack
                    (callback, url));
        } else {
            if (callback != null) {
                callback.onFail(ErrorCode.ERR_CODE_NO_NET, "暂无网络");
                callback.onStop();
            }
        }
    }

    public void sendDeleteRequest(String url, final Callback callback) {
        sendDeleteRequest(url, null, callback);
    }

    public void sendDeleteRequest(String url, RequestBody body, Callback callback) {
        LogUtils.d(TAG, "sendPutRequest: Url=" + url + "\nparams: " + body);
        if (callback != null) {
            callback.onStart();
        }

        if (NetworkManager.isConnectingToInternet(App.getContext())) {
            OkHttpUtils.delete().url(url).requestBody(body).build()
                    .execute(getCommonCallBack(callback, url));
        } else {
            if (callback != null) {
                callback.onFail(ErrorCode.ERR_CODE_NO_NET, "暂无网络");
                callback.onStop();
            }
        }
    }

    private StringCallback getCommonCallBack(final Callback callback, final String url) {
        return new StringCallback() {
            public void onError(okhttp3.Call call, Exception e, int id) {
                try {
                    LogUtils.d(TAG, "Url: " + url);
                    LogUtils.d(TAG, "onError: " + e);
                    ExceptionUtil.showException(e);

                    if (callback != null) {
                        callback.onFail(ErrorCode.ERR_CODE_HTTP_RESPONSE_ERROR, MAP_ERROR_CODE
                                .get(ErrorCode.ERR_CODE_HTTP_RESPONSE_ERROR));
                    }

                } catch (Exception me) {
                    ExceptionUtil.showException(me);
                    if (showToast) {
                        BaseToast.showShort(me.getLocalizedMessage());
                    }
                } finally {
                    if (callback != null) {
                        try {
                            callback.onStop();
                        } catch (Exception e1) {
                            ExceptionUtil.showException(e1);
                        }
                    }
                }
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Log.i(TAG, Thread.currentThread().getName());
                    handleResponse(response, callback, url);
                } catch (Exception e) {
                    ExceptionUtil.showException(e);
                    if (showToast) {
                        BaseToast.showShort(e.getLocalizedMessage());
                    }
                } finally {
                    if (callback != null) {
                        try {
                            callback.onStop();
                        } catch (Exception e) {
                            ExceptionUtil.showException(e);
                        }
                    }
                }
            }
        };
    }

    /**
     * 解析接口返回
     *
     * @param response response
     * @param callback callback
     */
    private void handleResponse(String response, Callback callback, String url) {
        LogUtils.d(TAG, "Url: " + url);
        LogUtils.d(TAG, "onResponse: " + response);

        if (TextUtils.isEmpty(response)) {
            if (showToast) {
                BaseToast.showShort("无法解析返回数据");
            }

            if (callback != null) {
                callback.onFail(ErrorCode.ERR_CODE_DATA_ILLEGAL, MAP_ERROR_CODE.get(ErrorCode
                        .ERR_CODE_DATA_ILLEGAL));
            }

            return;
        }

        int code = ErrorCode.ERR_CODE_DATA_ILLEGAL;
        String message = MAP_ERROR_CODE.get(ErrorCode.ERR_CODE_DATA_ILLEGAL);
        try {
            JSONObject jsonObject = JSON.parseObject(response);
            code = jsonObject.getIntValue("errcode");
            message = jsonObject.getString("errmsg");

            if (ERR_CODE_SUCCESS == code) {
                if (callback != null) {
                    callback.onSuccess(jsonObject);
                }
                return;
            }
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            if (showToast) {
                BaseToast.showShort(e.getLocalizedMessage());
            }
        }
        callback.onFail(code, message);
    }
}