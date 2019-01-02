package com.ppm.ppcomon.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ppm.ppcomon.R;

public final class ImageLoader {

    public static void loadImage(Activity activity, String url, ImageView imageView) {
        loadImage(activity, url, imageView, R.mipmap.ic_default_photo, R.mipmap.ic_default_photo);
    }

    public static void loadImage(Activity activity, String url, ImageView imageView, int defaultPic, int errPic) {
        if (activity == null || imageView == null) {
            return;
        }

        if (StringUtil.isEmpty(url)) {
            imageView.setImageResource(R.mipmap.ic_default_photo);
        }
        Glide.with(activity).load(url).into(imageView);
    }

    public static void loadImage(Fragment fragment, String url, ImageView imageView, int defaultPic, int errPic) {
        if (fragment == null || imageView == null) {
            return;
        }

        if (StringUtil.isEmpty(url)) {
            imageView.setImageResource(R.mipmap.ic_default_photo);
            return;
        }

        Glide.with(fragment).load(url).into(imageView);
    }
}
