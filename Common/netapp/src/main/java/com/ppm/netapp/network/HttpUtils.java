package com.ppm.netapp.network;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.ppm.netapp.network.callback.ReqCallback;
import com.ppm.ppcomon.utils.LogUtils;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpUtils {

    private Handler mainThreadHandler;

    public HttpUtils() {
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }


    public void sendGetRequest(final String url, Map<String, String> params, ReqCallback reqCallback) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                OkHttpClient okHttpClient = new OkHttpClient();

                SSLContext sc = SSLContext.getInstance("SSL");
                X509TrustManager x509TrustManager = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                try {
                    sc.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                builder.sslSocketFactory(sc.getSocketFactory(), x509TrustManager);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                String appendUrl = appendParams(url, params);
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
        });
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
