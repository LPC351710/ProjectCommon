package com.ppm.netapp;

import android.support.annotation.NonNull;
import okhttp3.*;

import java.io.IOException;

public class HttpUtils {

    private void okHttp(String url) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();

//            ResponseBody responseBody = response.body();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override public void onResponse(@NonNull Call call, @NonNull Response response) {

                }
            });

//            if (responseBody != null) {
//                LogUtils.d("response: " + responseBody.string());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
