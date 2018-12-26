package com.ppm.ppcomon.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.*;

/**
 * 位图操作工具类
 */
public class BitmapOptUtil {

    /**
     * 按照ScaleType的centerCrop来剪切位图  <br />
     * <p>
     * (1)当图片大于ImageView的宽高：以图片的中心点和ImageView的中心点为基准，按比例缩小图片，直到图片的宽高有一边等于ImageView的宽高，
     * 则对于另一边，图片的长度大于或等于ImageView的长度，最后用ImageView的大小居中截取该图片。<br />
     * (2)当图片小于ImageView的宽高：以图片的中心店和ImageView的中心点为基准，按比例扩大图片，直到图片的宽高大于或等于ImageView的宽高，
     * 并按ImageView的大小居中截取该图片。<br />
     *
     * @param src             原位图
     * @param containerWidth  容器的宽度（目标宽度）
     * @param containerHeight 容器高度（目标高度）
     * @return 目标宽高的位图
     */
    public static Bitmap resizeBitmapByCenterCrop(Bitmap src, int containerWidth, int containerHeight) {

        if (src == null || containerWidth == 0 || containerHeight == 0) {
            return null;
        }

        int bitmapWidth = src.getWidth();
        int bitmapHeight = src.getHeight();

        //容器和位图的宽度比值
        float widthRatio = (float) containerWidth / (float) bitmapWidth;
        //容器和位图的高度比值
        float heightRatio = (float) containerHeight / (float) bitmapHeight;

        Bitmap scaleUpBitmap = widthRatio > heightRatio ?
                getScaleBitmap(src, widthRatio) : getScaleBitmap(src, heightRatio);

        int firstPixelX = (scaleUpBitmap.getWidth() - containerWidth) / 2;
        int firstPixelY = (scaleUpBitmap.getHeight() - containerHeight) / 2;
        return Bitmap.createBitmap(scaleUpBitmap, firstPixelX, firstPixelY, containerWidth, containerHeight);
    }

    /**
     * 按比例缩放位图
     *
     * @param mBitmap
     * @param scaleRatio
     * @return
     */
    public static Bitmap getScaleBitmap(Bitmap mBitmap, float scaleRatio) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(scaleRatio, scaleRatio);
        Bitmap mScaleBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);

        return mScaleBitmap;
    }

    /**
     * 倾斜图片
     *
     * @param xRatio >0时，向左倾斜，<0时，向右倾斜
     * @param yRatio >0时，变瘦，<0时，变胖
     * @return
     */
    public static Bitmap getSkewBitmap(Bitmap mBitmap, float xRatio, float yRatio) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postSkew(xRatio, yRatio);
        Bitmap mScrewBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);

        return mScrewBitmap;
    }

    /**
     * 旋转图片
     *
     * @param degrees 旋转角度
     * @return
     */
    public static Bitmap getRotatedBitmap(Bitmap mBitmap, float degrees) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap mRotateBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);

        return mRotateBitmap;
    }

    /**
     * 反转图片
     *
     * @return
     */
    public static Bitmap getInverseBitmap(Bitmap mBitmap) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);
        Bitmap mInverseBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);

        return mInverseBitmap;
    }

    /**
     * 位图加倒影
     *
     * @return
     */
    public static Bitmap getReflectedBitmap(Bitmap mBitmap) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);

        //创建反转后的图片Bitmap对象，图片高是原图的一半。
        //Bitmap mInverseBitmap = Bitmap.createBitmap(mBitmap, 0, height/2, width, height/2, matrix, false);
        //创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
        //注意两种createBitmap的不同
        //Bitmap mReflectedBitmap = Bitmap.createBitmap(width, height*3/2, Config.ARGB_8888);

        Bitmap mInverseBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
        Bitmap mReflectedBitmap = Bitmap.createBitmap(width, height * 2, Config.ARGB_8888);

        // 把新建的位图作为画板
        Canvas mCanvas = new Canvas(mReflectedBitmap);
        //绘制图片
        mCanvas.drawBitmap(mBitmap, 0, 0, null);
        mCanvas.drawBitmap(mInverseBitmap, 0, height, null);

        //添加倒影的渐变效果
        Paint mPaint = new Paint();
        Shader mShader = new LinearGradient(0, height, 0, mReflectedBitmap.getHeight(), 0x70ffffff, 0x00ffffff, TileMode.MIRROR);
        mPaint.setShader(mShader);
        //设置叠加模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //绘制遮罩效果
        mCanvas.drawRect(0, height, width, mReflectedBitmap.getHeight(), mPaint);

        return mReflectedBitmap;
    }

    /**
     * 提取图像Alpha位图，抠出位图轮廓
     *
     * @param mBitmap 位图背景要透明
     * @param color   要填充的颜色
     * @return
     */
    public static Bitmap getAlphaBitmap(Bitmap mBitmap, int color) {

        //BitmapDrawable的getIntrinsicWidth（）方法，Bitmap的getWidth（）方法
        //注意这两个方法的区别
        //Bitmap mAlphaBitmap = Bitmap.createBitmap(mBitmapDrawable.getIntrinsicWidth(), mBitmapDrawable.getIntrinsicHeight(), Config.ARGB_8888);
        Bitmap mAlphaBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);

        Canvas mCanvas = new Canvas(mAlphaBitmap);
        Paint mPaint = new Paint();
        mPaint.setColor(color);
        //从原位图中提取只包含alpha的位图
        Bitmap alphaBitmap = mBitmap.extractAlpha();
        //在画布上（mAlphaBitmap）绘制alpha位图
        mCanvas.drawBitmap(alphaBitmap, 0, 0, mPaint);

        return mAlphaBitmap;
    }

    /**
     * 给位图描边
     *
     * @param color 描边颜色
     * @param x     左右描边的宽度
     * @param y     上下描边的高度
     * @return
     */
    public static Bitmap getStrokeBitmap(Bitmap mBitmap, int color, int x, int y) {
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        Bitmap mAlphaBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mAlphaBitmap);
        Paint mPaint = new Paint();

        mPaint.setColor(Color.BLUE);
        Bitmap alphaBitmap = mBitmap.extractAlpha();
        mCanvas.drawBitmap(alphaBitmap, 0, 0, mPaint);

        //创建图像的矩形区域
        Rect srcRect = new Rect(0, 0, width, height);
        //创建图像的内矩形区域
        Rect innerRect = new Rect(srcRect);
        //向内缩进两个像素
        innerRect.inset(x, y);
        //绘制原始图像
        mCanvas.drawBitmap(mBitmap, srcRect, innerRect, mPaint);

        return mAlphaBitmap;
    }

    /**
     * 图片圆角处理
     *
     * @return
     */
    public static Bitmap getRoundCornerBitmap(Bitmap mBitmap, float roundPx) {
        Bitmap bgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);
        //把创建的位图作为画板
        Canvas mCanvas = new Canvas(bgBitmap);

        Paint mPaint = new Paint();
        Rect mRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF mRectF = new RectF(mRect);
        mPaint.setAntiAlias(true);
        //先绘制圆角矩形
        mCanvas.drawRoundRect(mRectF, roundPx, roundPx, mPaint);
        //设置图像的叠加模式
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制图像
        mCanvas.drawBitmap(mBitmap, mRect, mRect, mPaint);

        return bgBitmap;
    }

    /**
     * 绘制圆形图片
     */
    public static Bitmap getCircularBitmap(Bitmap source, int diameter) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(diameter, diameter, Config.ARGB_8888);
        //产生一个同样大小的画布
        Canvas canvas = new Canvas(target);
        //首先绘制圆形
        canvas.drawCircle(diameter / 2, diameter / 2, diameter / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制图片
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 图片灰化处理
     *
     * @return
     */
    public static Bitmap getGrayBitmap(Bitmap mBitmap) {
        Bitmap mGrayBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mGrayBitmap);
        Paint mPaint = new Paint();
        //创建颜色变换矩阵
        ColorMatrix mColorMatrix = new ColorMatrix();
        //设置饱和度
        mColorMatrix.setSaturation(0);
        //创建颜色过滤矩阵
        ColorMatrixColorFilter mColorFilter = new ColorMatrixColorFilter(mColorMatrix);
        //设置画笔的颜色过滤矩阵
        mPaint.setColorFilter(mColorFilter);
        //使用处理后的画笔绘制图像
        mCanvas.drawBitmap(mBitmap, 0, 0, mPaint);

        return mGrayBitmap;
    }

    /**
     * 保存位图到路径
     *
     * @param bitmap
     * @param path   绝对路径
     */
    public static void saveBitmap(Bitmap bitmap, String path) {
        FileOutputStream mFileOutputStream = null;
        try {
            File mFile = new File(path);
            mFile.createNewFile();
            mFileOutputStream = new FileOutputStream(mFile);
            //保存Bitmap到PNG文件
            //图片压缩质量为75，对于PNG来说这个参数会被忽略
            bitmap.compress(CompressFormat.PNG, 75, mFileOutputStream);
            mFileOutputStream.flush();
        } catch (IOException e) {
        } finally {
            try {
                if (mFileOutputStream != null) {
                    mFileOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static String changeImageToBase64(ByteArrayOutputStream stream) {

        byte[] bytes = null;
        try {
            bytes = new byte[stream.size()];
            bytes = stream.toByteArray();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = new String(Base64.encode(bytes, Base64.DEFAULT));
        return str;
    }

    public static Bitmap changeBase64ToImage(String strImg) {
        Bitmap bp = null;
        if (strImg == null || "".equals(strImg))
            return null;
        try {
            byte[] data;
            data = Base64.decode(strImg, Base64.DEFAULT);
            for (int i = 0; i < data.length; i++) {
                if (data[i] < 0) {
                    data[i] += 256;
                }
            }
            bp = BitmapFactory.decodeByteArray(data, 0, data.length);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bp;
    }

    /**
     * 把drawable转化成bitmap
     */
    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    /**
     * 把bitmap转化成drawable
     */
    public static Drawable getDrawableFromBitmap(Context context, Bitmap bitmap) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    /**
     * 获取assets目录下的图片资源
     *
     * @param assetsfileName eg. "android.png"
     */
    public static Bitmap getAssetsBitmap(Context context, String assetsfileName) {
        Bitmap mBitmap = null;
        AssetManager mAssetManager = context.getAssets();
        try {
            InputStream mInputStream = mAssetManager.open(assetsfileName);
            mBitmap = BitmapFactory.decodeStream(mInputStream);
        } catch (IOException e) {
        }

        return mBitmap;
    }

    /**
     * 模糊图像处理
     *
     * @param sentBitmap 原图
     */
    public static Bitmap doBlur(Bitmap sentBitmap) {
        return doBlur(sentBitmap, 10);
    }

    /**
     * 模糊图像处理
     *
     * @param sentBitmap 原图
     * @param radius     模糊半径
     */
    public static Bitmap doBlur(Bitmap sentBitmap, int radius) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        if (null == sentBitmap || null == sentBitmap.getConfig()) {
            return null;
        }
        Bitmap bitmap;
        bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            try {
                for (x = 0; x < w; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }
}
