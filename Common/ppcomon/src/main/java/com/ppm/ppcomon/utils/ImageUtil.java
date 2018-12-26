package com.ppm.ppcomon.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.ppm.ppcomon.constant.CfgConstant;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片处理工具类.
 *
 * @author daiwei02
 */
public class ImageUtil {
    private final static String TAG = "ImageUtil";

    /**
     * 放大缩小图片
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    /**
     * 将Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 获得圆角图片的方法
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config
                .ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static String handlePhotoByCut(Uri uri, Context context) {
        if (uri == null) {
            return "";
        }
        String imagePath = "";
        String tempStr = uri.toString();
        if ((tempStr.substring(0, 7)).equals("file://")) {
            imagePath = tempStr.substring(7, tempStr.length());
        } else if (tempStr.substring(0, 10).equals("content://")) {
            imagePath = getImagePathFromUri(uri, context);
        }
        imagePath = imagePath.replaceAll("%20", " ");// 处理路径中存在空格的问题
        return imagePath;
    }

    public static String getImagePathFromUri(Uri uri, Context context) {
        Cursor cursor = null;
        try {
            ContentResolver resolver = context.getContentResolver();
            cursor = resolver.query(uri, new String[]{"_data"}, null, null, null);
            cursor.moveToFirst();
            String path = cursor.getString(0);
            cursor.close();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return "";
    }

    /**
     * 获得带倒影的图片方法
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2,
                matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config
                .ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection
                .getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);
        return bitmapWithReflection;
    }

    /**
     * 根据图片路径将图片编码为bitmap对象
     *
     * @param path
     * @param displayWidth
     * @param displayHeight
     * @return
     */
    public static Bitmap decodeBitmap(String path, int displayWidth, int displayHeight) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(path, op); // 获取尺寸信息
        // 获取比例大小
        int wRatio = (int) Math.ceil(op.outWidth / (float) displayWidth);
        int hRatio = (int) Math.ceil(op.outHeight / (float) displayHeight);
        // 如果超出指定大小，则缩小相应的比例
        if (wRatio > 1 && hRatio > 1) {
            if (wRatio > hRatio) {
                op.inSampleSize = wRatio;
            } else {
                op.inSampleSize = hRatio;
            }
        }
        op.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(path, op);
        if (bmp == null) {
            Log.w("david", "imageutil file [" + path + "] not found.");
            return null;
        } else {
            return Bitmap.createScaledBitmap(bmp, displayWidth, displayHeight, true);
        }
    }

    /**
     * 采用复杂计算来决定缩放
     *
     * @param path
     * @param maxImageSize
     * @return
     */
    public static Bitmap decodeBitmap(String path, int maxImageSize) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(path, op); // 获取尺寸信息
        int scale = 1;
        if (op.outWidth > maxImageSize || op.outHeight > maxImageSize) {
            scale = (int) Math.pow(
                    2,
                    (int) Math.round(Math.log(maxImageSize / (double) Math.max(op.outWidth, op
                            .outHeight))
                            / Math.log(0.5)));
        }
        op.inJustDecodeBounds = false;
        op.inSampleSize = scale;
        bmp = BitmapFactory.decodeFile(path, op);
        return bmp;
    }

    /**
     * 查询所有图片
     *
     * @param context
     * @return
     */
    public static Cursor queryImagesCount(Activity context) {
        String[] columns = new String[]{" count(" + MediaStore.Images.Media._ID + ") as count"};
        return context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, null);
    }

    /**
     * 查询所有图片目录
     *
     * @param context
     * @return
     */
    public static Cursor queryBuckets(Activity context) {
        String[] columns = new String[]{" count(" + MediaStore.Images.Media.BUCKET_ID + ") as " +
                "count",
                MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        Cursor oCursor = context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns, "1=1) group by "
                        + MediaStore.Images.Media.BUCKET_ID + " --(", null, null);
        System.out.println("oCursor.getCount()=" + oCursor.getCount());
        return oCursor;
    }

    /**
     * 查询所有缩略图
     *
     * @param context
     * @return
     */
    public static Cursor queryThumbnails(Activity context) {
        String[] columns = new String[]{MediaStore.Images.Thumbnails.DATA, MediaStore.Images
                .Thumbnails._ID,
                MediaStore.Images.Thumbnails.IMAGE_ID};
        return context.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, columns,
                null, null,
                MediaStore.Images.Thumbnails.IMAGE_ID + " DESC");
    }

    /**
     * 根据查询条件查询缩略图
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static Cursor queryThumbnails(Activity context, String selection, String[]
            selectionArgs) {
        String[] columns = new String[]{MediaStore.Images.Thumbnails.DATA, MediaStore.Images
                .Thumbnails._ID,
                MediaStore.Images.Thumbnails.IMAGE_ID};
        return context.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, columns,
                selection,
                selectionArgs, MediaStore.Images.Thumbnails.IMAGE_ID + " DESC");
    }

    /**
     * 根据目录ID查询该目录最新4张图片的缩略图
     *
     * @param context
     * @param bucketId
     * @return
     */
    public static Cursor query4ThumbnailsByBucketId(Activity context, int bucketId) {
        String[] columns = new String[]{MediaStore.Images.Thumbnails.DATA, MediaStore.Images
                .Thumbnails._ID,
                MediaStore.Images.Thumbnails.IMAGE_ID};
        Cursor oCursor = context.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                columns,
                "image_id in (select _id from images where bucket_id = ? order by _id desc )",
                new String[]{bucketId
                        + ""}, MediaStore.Images.Thumbnails.IMAGE_ID + " desc limit 0,4");
        if (bucketId == -1) {
            oCursor = context.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                    columns, null, null,
                    MediaStore.Images.Thumbnails.IMAGE_ID + " desc limit 0,4");
        }

        System.out.println("query4ThumbnailsByBucketId oCursor.getCount()=" + oCursor.getCount());
        return oCursor;
    }

    /**
     * 根据目录ID查询该目录下所有图片对应的缩略图
     *
     * @param context
     * @param bucketId
     * @return
     */
    public static Cursor queryThumbnailsByBucketId(Activity context, int bucketId) {
        if (bucketId == -1) {
            return queryThumbnails(context);
        }
        String[] columns = new String[]{MediaStore.Images.Thumbnails.DATA, MediaStore.Images
                .Thumbnails._ID,
                MediaStore.Images.Thumbnails.IMAGE_ID};
        Cursor oCursor = context.managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                columns,
                "image_id in (select _id from images where bucket_id = ? order by _id desc )",
                new String[]{bucketId
                        + ""}, MediaStore.Images.Thumbnails.IMAGE_ID + " desc");
        System.out.println("queryThumbnailsByBucketId oCursor.getCount()=" + oCursor.getCount());
        return oCursor;
    }

    /**
     * 根据目录ID查询该目录下所有图片
     *
     * @param context
     * @param bucketId
     * @return
     */
    public static Cursor queryImagesByBucketId(Activity context, int bucketId) {
        if (bucketId == -1) {
            return queryImages(context);
        } else {
            return queryImages(context, "bucket_id = ?", new String[]{bucketId + ""});
        }
    }

    /**
     * 根据缩略图ID查询缩略图
     *
     * @param context
     * @param thumbId
     * @return
     */
    public static Bitmap queryThumbnailById(Activity context, int thumbId) {
        String selection = MediaStore.Images.Thumbnails._ID + " = ?";
        String[] selectionArgs = new String[]{thumbId + ""};
        Cursor cursor = ImageUtil.queryThumbnails(context, selection, selectionArgs);

        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images
                    .Thumbnails.DATA));
            cursor.close();
            return ImageUtil.decodeBitmap(path, 100, 100);
        } else {
            cursor.close();
            return null;
        }
    }

    /**
     * 获取全部
     *
     * @param context
     * @return
     */
    public static List<Bitmap> queryThumbnailList(Activity context) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        Cursor cursor = ImageUtil.queryThumbnails(context);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images
                    .Thumbnails.DATA));
            Bitmap b = ImageUtil.decodeBitmap(path, 100, 100);
            bitmaps.add(b);
        }
        cursor.close();
        return bitmaps;
    }

    /**
     * 根据缩略图id查询对应的缩略图
     *
     * @param context
     * @param thumbIds
     * @return
     */
    public static List<Bitmap> queryThumbnailListByIds(Activity context, int[] thumbIds) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (int i = 0; i < thumbIds.length; i++) {
            Bitmap b = ImageUtil.queryThumbnailById(context, thumbIds[i]);
            bitmaps.add(b);
        }

        return bitmaps;
    }

    /**
     * 查询所有图片
     *
     * @param context
     * @return
     */
    public static Cursor queryImages(Activity context) {
        String[] columns = new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME};
        return context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null,
                MediaStore.Images.Media.DATE_ADDED + " desc");
    }

    /**
     * 根据条件查询图片
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static Cursor queryImages(Activity context, String selection, String[] selectionArgs) {
        String[] columns = new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME};
        return context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
                selection, selectionArgs,
                MediaStore.Images.Media.DATE_ADDED + " desc");
    }

    /**
     * 根据imageid查询图片
     *
     * @param context
     * @param imageId
     * @return
     */
    public static Bitmap queryImageById(Activity context, int imageId) {
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = new String[]{imageId + ""};
        Cursor cursor = ImageUtil.queryImages(context, selection, selectionArgs);
        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media
                    .DATA));
            cursor.close();
            // return ImageUtil.decodeBitmap(path, 260, 260);
            return ImageUtil.decodeBitmap(path, 220);
        } else {
            cursor.close();
            return null;
        }
    }

    /**
     * 根据imageid查询缩略图
     *
     * @param context
     * @param imageId
     * @return
     */
    public static Bitmap queryThumbnailByImageId(Activity context, int imageId) {
        String selection = MediaStore.Images.Thumbnails.IMAGE_ID + "=?";
        String[] selectionArgs = new String[]{imageId + ""};
        Cursor cursor = ImageUtil.queryThumbnails(context, selection, selectionArgs);
        if (cursor.moveToFirst()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images
                    .Thumbnails.DATA));
            cursor.close();
            // return ImageUtil.decodeBitmap(path, 260, 260);
            return ImageUtil.decodeBitmap(path, 100, 100);
        } else {
            cursor.close();
            return null;
        }
    }

    /**
     * 关闭数据库表的游标
     *
     * @param cursor
     */
    public static void closeCursor(Cursor cursor) {
        try {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据缩略图的Id获取对应的大图
     *
     * @param context
     * @param thumbId
     * @return
     */
    public static Bitmap queryImageByThumbnailId(Activity context, Integer thumbId) {

        String selection = MediaStore.Images.Thumbnails._ID + " = ?";
        String[] selectionArgs = new String[]{thumbId + ""};
        Cursor cursor = ImageUtil.queryThumbnails(context, selection, selectionArgs);

        if (cursor.moveToFirst()) {
            int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails
                    .IMAGE_ID));
            cursor.close();
            return ImageUtil.queryImageById(context, imageId);
        } else {
            cursor.close();
            return null;
        }
    }

    /**
     * 根据联系人Id获取头像图片
     *
     * @param context
     * @return
     */
    public static Bitmap queryContactImageByPhotoId(Activity context, String photoId) {
        Bitmap headBitmap = null;
        Cursor cursor = null;
        String[] projection = new String[]{ContactsContract.Data.DATA15};
        String selection = ContactsContract.Data._ID + " = " + photoId;
        try {
            cursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    projection, selection, null,
                    null);

            if (cursor.moveToFirst()) {
                byte[] contactIcon = cursor.getBlob(0);
                headBitmap = BitmapFactory.decodeByteArray(contactIcon, 0, contactIcon.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return headBitmap;
    }

    /**
     * 获取图片，如果本地有，则从本地获取
     *
     * @param url
     * @return
     */
    public static Bitmap getImageView(String url) {
        return getImageView(url, 460, 460);
    }

    /**
     * 获取图片，如果本地有，则从本地获取
     *
     * @param url
     * @return
     */
    public static Bitmap getImageView(String url, int width, int height) {
        if (StringUtil.isBlank(url)) {
            return null;
        }

        String tmpUrl = "";
        int index = url.lastIndexOf("_");
        if (index == -1) {
            tmpUrl = url;
        } else {
            tmpUrl = url.substring(0, index);
        }
        String localFilePath = convertUrl2LocalPath(tmpUrl);

        LogUtils.i(TAG, "本地图片路径：" + localFilePath + "!");
        if (localFilePath != null) {
            try {
                Bitmap bmp = decodeBitmap(localFilePath, width, height);
                if (bmp != null) {
                    LogUtils.i(TAG, "读取本地图片[" + localFilePath + "]成功!");
                    return bmp;
                } else {
                    LogUtils.i(TAG, "读取本地图片[" + localFilePath + "]失败!");
                }
            } catch (Exception e) {
                LogUtils.e(TAG, "读取本地图片文件出错：", e);
            }
        }
        LogUtils.i(TAG, "返回网络图片!");
        return generateImageView(url, width, height);
    }

    /**
     * 根据url在线获取图片
     *
     * @param url
     * @return
     */
    public static Bitmap generateImageView(String url) {
        return generateImageView(url, 460, 460);
    }

    /**
     * 根据url在线获取图片
     *
     * @param url
     * @return
     */
    public static Bitmap generateImageView(String url, int width, int height) {
        if (StringUtil.isEmpty(url)) {
            return null;
        }

        Bitmap mapBitMap = null;
        URL imageUrl = null;

        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String tmpUrl = "";
            int index = url.lastIndexOf("_");
            if (index == -1) {
                tmpUrl = url + "_" + width + "x" + height;
            } else {
                url = url.substring(0, index);
                tmpUrl = url;
            }

            imageUrl = new URL(tmpUrl);
            LogUtils.i(TAG, "download image url is : " + imageUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is);
            mapBitMap = BitmapFactory.decodeStream(bis);

            // 保存本地文件
            String localFilePath = convertUrl2LocalPath(url);
            if (localFilePath != null) {
                bos = new BufferedOutputStream(new FileOutputStream(localFilePath));
                mapBitMap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "download image has problem .", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {

                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {

                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }

        return mapBitMap;
    }

    /**
     * 根据url转换成本地地址
     *
     * @param url
     * @return
     */
    private static String convertUrl2LocalPath(String url) {
        if (StringUtil.isBlank(url)) {
            return null;
        }
        StringBuffer sbRet = new StringBuffer("");
        sbRet.append(Environment.getExternalStorageDirectory() + CfgConstant
                .SDCARD_ROOT_DIR_URL_IMAGE);

        try {
            URL aUrl = new URL(url);
            sbRet.append("/" + aUrl.getHost() + "_" + aUrl.getPort());

            List<String> pathList = StringUtil.split2list(aUrl.getPath(), "\\/");
            if (pathList == null) {
                pathList = new ArrayList<String>(0);
            }

            int pathListSize = pathList.size();
            for (int i = 0; i < (pathListSize - 1); i++) {
                sbRet.append("/" + pathList.get(i));
            }

            if (aUrl.getQuery() != null) {
                sbRet.append("/" + aUrl.getQuery());
            }

            File fileDir = new File(sbRet.toString());
            fileDir.mkdirs();

            if (pathListSize > 0) {
                sbRet.append("/" + pathList.get(pathListSize - 1));
            }

            return sbRet.toString();
        } catch (Exception e) {
            LogUtils.e(TAG, "parse url[" + url + "] error: ", e);
        }
        return null;
    }

    /**
     * 将view转换为图像
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        if (view == null) {
            return null;
        }
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());

        return bitmap;
    }

    public static Bitmap printImage(View view) {
        if (null == view) {
            return null;
        }

        //打开图像缓存
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View
                .MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config
                .ARGB_8888);
        view.draw(new Canvas(bitmap));
        view.buildDrawingCache();

        //获得可视组件的截图
        return view.getDrawingCache();
    }

    public static Bitmap printImage2(View view) {
        if (null == view) {
            return null;
        }

        //打开图像缓存
        view.setDrawingCacheEnabled(true);
//        view.measure(View.MeasureSpec.makeMeasureSpec(400, MeasureSpec.EXACTLY), View
//                .MeasureSpec.makeMeasureSpec(400, View.MeasureSpec.EXACTLY));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config
//                .ARGB_8888);
//        view.draw(new Canvas(bitmap));
//        view.buildDrawingCache();

        //获得可视组件的截图
        return view.getDrawingCache();
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return "";
    }

    public static void getBitmap(Context context, String url, final ImageListener imageListener) {
        Uri uri = Uri.parse(url);
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(uri);
        ImageRequest imageRequest = builder.build();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline
                .fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
            @Override
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (!dataSource.isFinished()) {
                    return;
                }
                CloseableReference<CloseableImage> imageReference = dataSource.getResult();
                if (imageReference != null) {
                    final CloseableReference<CloseableImage> closeableReference = imageReference
                            .clone();
                    try {
                        CloseableImage closeableImage = closeableReference.get();
                        if (closeableImage instanceof CloseableBitmap) {
                            CloseableBitmap closeableBitmap = (CloseableBitmap) closeableImage;
                            Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                            if (bitmap != null && !bitmap.isRecycled()) {
                                final Bitmap tempBitmap = bitmap.copy(bitmap.getConfig(), false);
                                if (imageListener != null) {
                                    imageListener.onSuccess(tempBitmap);
                                }
                            }
                        }
                    } finally {
                        imageReference.close();
                        closeableReference.close();
                    }
                }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                Throwable throwable = dataSource.getFailureCause();
                if (imageListener != null) {
                    imageListener.onFail(throwable);
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }
}
