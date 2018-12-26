package com.ppm.ppcomon.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapDownloader {

    public void download(String url, ImageLoadedListener listener) {
        this.listener = listener;
        new DownloadThread(url).start();
    }

    // 开辟一个下载线程
    private class DownloadThread extends Thread {

        private String url;

        public DownloadThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            Bitmap bmp = loadBitmapFromURL(url);
            sendResult(bmp);
        }
    }

    private ImageLoadedListener listener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case WHAT:
                    listener.imageLoaded((Bitmap) message.obj);
            }
        }
    };

    // 发送消息通知：bitmap已经下载完成。
    private void sendResult(Bitmap bitmap) {
        Message message = handler.obtainMessage();
        message.what = WHAT;
        message.obj = bitmap;
        handler.sendMessage(message);
    }

    // 回调函数
    public interface ImageLoadedListener {
        void imageLoaded(Bitmap bitmap);
    }

    // 给定一个URL，从这个URL下载Bitmap
    public Bitmap loadBitmapFromURL(String url) {
        Bitmap bmp = null;
        try {
            byte[] imageBytes = loadRawDataFromURL(url);
            bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private final int WHAT = 0xe001;
    private static int TIMEOUT = 30 * 1000;

    public byte[] loadRawDataFromURL(String u) throws Exception {
        URL url = new URL(u);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 配置基础网络链接参数
        conn.setConnectTimeout(TIMEOUT);
        conn.setReadTimeout(TIMEOUT);
        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final int BUFFER_SIZE = 1024 * 5;
        final int EOF = -1;
        int c;
        byte[] buf = new byte[BUFFER_SIZE];
        while (true) {
            c = bis.read(buf);
            if (c == EOF)
                break;
            baos.write(buf, 0, c);
        }
        conn.disconnect();
        is.close();
        byte[] data = baos.toByteArray();
        baos.flush();
        return data;
    }

}
