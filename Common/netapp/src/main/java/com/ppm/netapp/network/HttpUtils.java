package com.ppm.netapp.network;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.ppm.netapp.network.callback.ReqCallback;
import com.ppm.ppcomon.utils.LogUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    private Handler mainThreadHandler;

    public HttpUtils() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }


    public void sendGetRequest(final String url, Map<String, String> params, ReqCallback reqCallback) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            String appendUrl = appendParams(url, params);
            LogUtils.d("url: " + appendUrl);
            Request request = new Request.Builder().url(appendUrl).build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtils.d("Err: " + e.getLocalizedMessage());
                    callBackFail(reqCallback);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    ResponseBody responseBody = response.body();
                    String responseStr;
                    if (responseBody != null) {
                        try {
                            responseStr = responseBody.string();
                            LogUtils.d("response: " + responseStr);
                            callBackSuccess(reqCallback, responseStr);
                        } catch (Exception e) {
                            callBackFail(reqCallback);
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            callBackFail(reqCallback);
        }
    }

    private void callBackSuccess(ReqCallback reqCallback, String response) {
        if (mainThreadHandler != null) {
            mainThreadHandler.post(() -> {
                if (reqCallback != null) {
                    reqCallback.success(response);
                }
            });
        }
    }

    private void callBackFail(ReqCallback reqCallback) {
        if (mainThreadHandler != null) {
            mainThreadHandler.post(() -> {
                if (reqCallback != null) {
                    reqCallback.fail();
                }
            });
        }
    }


    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
}
