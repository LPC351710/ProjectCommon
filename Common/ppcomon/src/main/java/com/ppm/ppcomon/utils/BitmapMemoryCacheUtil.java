package com.ppm.ppcomon.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.collection.LruCache;
import android.util.Log;
import com.ppm.ppcomon.base.app.App;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 缓存图片到内存和SD卡功能类。
 * 按照最后最近使用原则，在超出限定最大内存或者sd卡空间后，将删除最长时间不用的位图
 */
public class BitmapMemoryCacheUtil {

    private static final String TAG = "bitmapCache";

    private static final boolean DEBUG = false;

    private static BitmapMemoryCacheUtil instance;

    /**
     * 设置图片缓存大小为程序最大可用内存的1/8（单位：KB）
     */
    final static int MAX_MEMORY_CACHE_SPACE = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;
    /**
     * 限定此处能使用的最大外部存储空间为10M
     */
    final static long MAX_EXTERNAL_CACHE_SPACE = 10 * 1024 * 1024;

    /**
     * 图片内存缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉
     */
    private LruCache<String, Bitmap> mMemoryCache;

    /**
     * 图片硬盘缓存核心类
     */
    private DiskLruCache mDiskLruCache;

    private BitmapMemoryCacheUtil(Context context) {

        log("异步图片缓存最大内存" + (MAX_MEMORY_CACHE_SPACE / 1024) + "M");
        log("异步图片缓存最大硬盘空间" + (MAX_EXTERNAL_CACHE_SPACE / 1024 / 1024) + "M");

        mMemoryCache = new LruCache<String, Bitmap>(MAX_MEMORY_CACHE_SPACE) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;//（单位：KB）
            }
        };

        try {
            File cacheDir = getDiskCacheDir(context, "bitmapCache");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, MAX_EXTERNAL_CACHE_SPACE);
        } catch (Exception e) {
            ExceptionUtil.showException(e);
        }
    }

    public static BitmapMemoryCacheUtil getInstance() {
        if (instance == null) {
            instance = new BitmapMemoryCacheUtil(App.getContext());
        }
        return instance;
    }

    private static void log(String log) {
        if (DEBUG) {
            Log.d(TAG, log);
        }
    }

    /**
     * 得到某一个key经MD5编码作为key的图片缓存
     *
     * @param key
     * @return
     */
    public Bitmap get(String key) {
        log("已用内存缓存" + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + "M");
        log("剩余内存缓存" + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + "M");
        String MD5key = getMD5hashKey(key);
        if (null == mMemoryCache.get(MD5key)) {
            long time1 = System.currentTimeMillis();
            Bitmap b = getBitmapFromSDCache(MD5key);
            if (null != b) {
                log("读硬盘时间：" + (System.currentTimeMillis() - time1));
                log("硬盘缓存" + key);
                mMemoryCache.put(MD5key, b);
                return b;
            } else {
                log("无缓存" + key);
                return null;
            }
        } else {
            log("内存缓存" + key);
            long time1 = System.currentTimeMillis();
            Bitmap bitmap = mMemoryCache.get(MD5key);
            log("读内存时间：" + (System.currentTimeMillis() - time1));
            return bitmap;
        }
    }

    /**
     * 把某个key经MD5编码作为key，位图作为value放入缓存
     */
    public void put(final String key, final Bitmap value) {
        log("放入缓存" + key);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String md5hashKey = getMD5hashKey(key);
                mMemoryCache.put(md5hashKey, value);
                putBitmapCacheToSDCard(md5hashKey, value);
            }
        });
        thread.start();
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            ExceptionUtil.showException(e);
        }
        return 1;
    }

    /**
     * 对url进行MD5，使之可以作为一个文件名存储
     *
     * @param key
     * @return
     */
    public String getMD5hashKey(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 把bitmap写入sd卡，由diskLruCache管理
     *
     * @param key
     * @param bitmap
     */
    private void putBitmapCacheToSDCard(String key, Bitmap bitmap) {
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
            }
            mDiskLruCache.flush();
        } catch (Exception e) {
            ExceptionUtil.showException(e);
        }
    }

    /**
     * 从SD卡中读取位图缓存
     *
     * @param key
     * @return
     */
    private Bitmap getBitmapFromSDCache(String key) {
        try {
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                return BitmapFactory.decodeStream(is);
            }
        } catch (Exception e) {
            ExceptionUtil.showException(e);
        }
        return null;
    }
}
