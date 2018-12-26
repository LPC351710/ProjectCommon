package com.ppm.ppcomon.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class MediaDecoder {
    public interface OnMediaDecoderListener {
        void onDecodeFrame(String key, Bitmap bitmap);
    }

    private static Reference<MediaDecoder> mediaDecoder;

    private OnMediaDecoderListener listener;
    private int type;
    public final static int TYPE_VIDEO_LOCAL = 0x001;
    public final static int TYPE_VIDEO_NET = 0x002;
    public final static int VIDEO_START = 0x001;
    public final static int VIDEO_CUSTOM = 0x002;
    public final static int VIDEO_END = 0x003;

    @IntDef({TYPE_VIDEO_LOCAL, TYPE_VIDEO_NET})
    @Retention(RetentionPolicy.SOURCE)
    private @interface MediaDecoderType {

    }

    @IntDef({VIDEO_START, VIDEO_CUSTOM, VIDEO_END})
    @Retention(RetentionPolicy.SOURCE)
    private @interface MediaDecoderTime {

    }

    private static MediaDecoder getMediaDecoder() {
        if (!checkNotNull()) {
            mediaDecoder = new WeakReference<>(new MediaDecoder());
        }
        return mediaDecoder.get();
    }

    public static MediaDecoder create() {
        return getMediaDecoder();
    }

    public MediaDecoder setType(@MediaDecoderType int mediaType) {
        type = mediaType;
        return getMediaDecoder();
    }

    public MediaDecoder setListener(OnMediaDecoderListener onMediaDecoderListener) {
        mediaDecoder.get().listener = onMediaDecoderListener;
        return getMediaDecoder();
    }


    public void start(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

        Bitmap bitmap = BitmapMemoryCacheUtil.getInstance().get(path);
        if (bitmap != null) {
            if (listener != null) {
                listener.onDecodeFrame(path, bitmap);
            }
        } else {
            new Thread(new GetFrameThread(path)).start();
        }
    }

    private static boolean checkNotNull() {
        return (mediaDecoder != null && mediaDecoder.get() != null);
    }

    class GetFrameThread implements Runnable {

        private String url;
        private Reference<MediaMetadataRetriever> retriever;

        GetFrameThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                setDataSource(url);
                Bitmap bitmap;
                //获得第一帧图片
                MediaMetadataRetriever mediaMetadataRetriever = retriever.get();
                if (mediaMetadataRetriever != null) {
                    bitmap = retriever.get().getFrameAtTime();
                    sendResult(url, bitmap);
                    putInCache(url, bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (retriever != null && retriever.get() != null) retriever.get().release();
            }
        }

        public void setDataSource(String path) {
            retriever = new WeakReference<>(new MediaMetadataRetriever());
            switch (type) {
                case TYPE_VIDEO_LOCAL:
                    retriever.get().setDataSource(path);
                    break;
                case TYPE_VIDEO_NET:
                    retriever.get().setDataSource(path, new HashMap<String, String>());
                    break;
            }
        }

    }

    private final int WHAT = 0xe001;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case WHAT:
                    if (listener != null) {
                        ResultBean resultBean = (ResultBean) message.obj;
                        listener.onDecodeFrame(resultBean.key, resultBean.bitmap);
                    }
            }
        }
    };

    private void sendResult(String key, Bitmap bitmap) {
        Message message = handler.obtainMessage();
        message.what = WHAT;
        ResultBean resultBean = new ResultBean();
        resultBean.key = key;
        resultBean.bitmap = bitmap;
        message.obj = resultBean;
        handler.sendMessage(message);
    }


    private void putInCache(String key, Bitmap bitmap) {
        if (TextUtils.isEmpty(key) || bitmap == null) {
            return;
        }
        BitmapMemoryCacheUtil.getInstance().put(key, bitmap);
    }


    class ResultBean implements Serializable {
        public String key;
        public Bitmap bitmap;
    }

}
