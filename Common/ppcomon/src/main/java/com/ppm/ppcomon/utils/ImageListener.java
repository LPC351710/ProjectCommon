package com.ppm.ppcomon.utils;

import android.graphics.Bitmap;

public interface ImageListener {

    void onFail(Throwable throwable);

    void onSuccess(Bitmap bitmap);
}
