package com.ppm.netapp.network;

import android.net.Uri;
import com.ppm.netapp.network.callback.ReqCallback;
import com.ppm.ppcomon.utils.LogUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpUtils {


    public void sendGetRequest(String url, Map<String, String> params, ReqCallback reqCallback) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            url = appendParams(url, params);
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    LogUtils.d("response: " + responseBody.string());

                    if (reqCallback != null) {
                        reqCallback.success(responseBody.string());
                    }
                }
            } else {
                if (reqCallback != null) {
                    reqCallback.fail();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
