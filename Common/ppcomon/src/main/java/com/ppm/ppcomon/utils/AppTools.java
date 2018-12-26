package com.ppm.ppcomon.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.ppm.ppcomon.BuildConfig;
import com.ppm.ppcomon.base.app.App;
import com.ppm.ppcomon.widget.BaseToast;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xinyuan on 2016/1/11.
 */
public class AppTools {
    private static final String TAG = "AppTools";
    private static long lastClickTime;
    private static SharedPreferences pref;

    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            LogUtils.e(TAG, "获取本应用版本号出错: ", e);
        }
        return verCode;
    }

    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtils.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static void installApk(Context context, String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + "" +
                    ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static void goToMarket(Context context) {
        try {
            String mAddress = "market://details?id=" + context.getPackageName();
            Intent marketIntent = new Intent("android.intent.action.VIEW");
            marketIntent.setData(Uri.parse(mAddress));
            context.startActivity(marketIntent);
        } catch (Exception e) {
            BaseToast.showShort("您没有安装应用市场");
        }
    }

    /**
     * 检查当前网络是否可用
     *
     * @return 网络可用true，不可用false
     */
    public static boolean isNetworkAvailable() {
        // 获取手机所有连接管理对象
        ConnectivityManager connectivityManager = (ConnectivityManager) App
                .getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = connectivityManager.getAllNetworks();
                if (networks != null && networks.length > 0) {
                    for (Network network : networks) {

                        NetworkInfo info = connectivityManager.getNetworkInfo(network);
                        if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            } else {
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                if (networkInfos != null && networkInfos.length > 0) {
                    for (NetworkInfo networkInfo : networkInfos) {
                        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State
                                .CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean checkPermission(Activity activity, String permission, int requestCode) {
        boolean flag = false;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        requestCode);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        requestCode);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            flag = true;
        }
        return flag;
    }

    public static PackageInfo getPackageInfo() {
        try {
            return App.getContext().getPackageManager().getPackageInfo(App.getContext()
                            .getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getString(int res) {
        return App.getContext().getString(res);
    }

    public static int getColor(int res) {
        //noinspection deprecation
        return App.getContext().getResources().getColor(res);
    }

    public static Drawable getDrawable(int resId) {
        return App.getContext().getResources().getDrawable(resId);
    }

    public static int getDimensionPixelSize(int resId) {
        return App.getContext().getResources().getDimensionPixelSize(resId);
    }

    //强制显示系统键盘
    public static void showSoftInput(final View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) view.getContext().getSystemService
                        (Context.INPUT_METHOD_SERVICE);
                m.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 500);
    }

    //强制关闭系统键盘
    public static void hideSoftInput(final View view) {
        view.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) view.getContext().getSystemService
                        (Context.INPUT_METHOD_SERVICE);
                m.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                        .HIDE_IMPLICIT_ONLY);
            }
        });
    }

    //强制关闭系统键盘
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService
                    (Context.INPUT_METHOD_SERVICE);
            if (inputmanger != null) {
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 防暴力点击
     *
     * @return 是否暴力点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 800) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取手机的IMEI号
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return manager != null ? manager.getDeviceId() : null;
    }

    /**
     * 获取设备ID（全球唯一码 IMEI 设备唯一id）
     */
    public static String getDeviceId() {
        String deviceId = PreferencesUtils.getSharePreStr(App.getContext(), PreferencesUtils
                .DATA_DEVICE_ID_INDEX);
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }

        deviceId = UUID.randomUUID().toString();
        PreferencesUtils.putSharePre(App.getContext(), PreferencesUtils.DATA_DEVICE_ID_INDEX,
                deviceId);
        return deviceId;
    }

    /**
     * 获取手机的IMSI号
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return manager != null ? manager.getSubscriberId() : null;
    }

    /**
     * 获取手机网络类型
     *
     * @param context 上下文
     * @return 网络类型
     */
    public static String getNetworkType(Context context) {
        String type = "";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "wifi";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager
                    .NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType ==
                    TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType ==
                    TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3g";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4g";
            }
        }
        return type;
    }

    /**
     * 获取手机的分辨率 width,height
     *
     * @param context 上下文
     * @return 分辨率 width,height
     */
    public static Map<String, Integer> getDisplayResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        int width = wm.getDefaultDisplay().getWidth();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("height", height);
        map.put("width", width);
        return map;
    }

    /**
     * 获取手机品牌
     *
     * @return 手机品牌
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机系统版本
     *
     * @return 手机系统版本
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 拨打手机号码
     */
    public static void callPhone(final Activity context, String phoneNumber, String tips) {
        if (TextUtils.isEmpty(phoneNumber)) {
            BaseToast.showShort("电话号码不存在.");
            return;
        }

        if (TextUtils.isEmpty(tips)) {
            tips = phoneNumber;
        }

        if (!phoneNumber.startsWith("tel")) {
            phoneNumber = "tel:" + phoneNumber;
        }
        final String phone = phoneNumber;

        if (tips.startsWith("tel:")) {
            // 如果tel:打头显示的时候去掉
            tips = tips.substring(4);
        }

//        CommonDialogsInBase base = new CommonDialogsInBase();
//        base.displayTwoBtnDialog(context, "", null, null, tips, null, new OnBtnClickL() {
//            @Override
//            public void onBtnClick(DialogInterface dialog) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_CALL);
//                //uri:统一资源标示符（更广）
//                intent.setData(Uri.parse(phone));
//                //开启系统拨号器
//                context.startActivity(intent);
//            }
//        });

    }

    /**
     * 发送短信
     */
    public static void sendSMS(Context context, String phoneNumber) {
        BaseToast.showShort("暂时不支持发短信.");
    }

    /**
     * 发送邮件
     */
    public static void sendMail(Context context, String receiverParam) {
        if (TextUtils.isEmpty(receiverParam)) {
            BaseToast.showShort("收件人不存在.");
            return;
        }

        String receiver = receiverParam;
        if (!receiver.startsWith("mail")) {
            receiver = "mailto:" + receiverParam;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse(receiver));
        //开启系统拨号器
        context.startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
    }

    /**
     * 调起支付宝
     *
     * @param context
     * @param alipayUri
     */
    public static void launchAliPay(Context context, String alipayUri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(alipayUri));
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "未安装支付宝应用", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 跳转
     *
     * @param uri
     */
    public static void startIntent(Context context, String uri) {
        try {
            Intent intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            context.startActivity(intent);
        } catch (Exception e) {
            if (uri.contains("com.eg.android.AlipayGphone")) {
                Toast.makeText(context, "未安装支付宝应用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "应用未安装", Toast.LENGTH_SHORT).show();
            }
            e.printStackTrace();
        }
    }

    public static boolean saveTxtFile(String content, String path, boolean append) {
        File file = new File(path);
        PrintWriter printWriter = null;
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new RuntimeException("无法正确创建文件");
                }
            }
            fos = new FileOutputStream(file, append);
            printWriter = new PrintWriter(fos, true);
            printWriter.println(content);
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        return true;
    }

    public static String readTxtFile(String path) {
        StringBuilder ret = new StringBuilder();
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            File file = new File(path);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                reader = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    ret.append(line);
                }
            } else {
                LogUtils.d(TAG, "找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d(TAG, "读取文件内容出错");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret.toString();
    }

    public static String getUrlParams(String url, String name) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(name)) {
            return "";
        }
        Uri uri = Uri.parse(url);
        if (uri == null) {
            return "";
        }
        return uri.getQueryParameter(name);
    }

    public static String removeUrlParams(String url, String param) {
        if (StringUtil.isNotEmpty(url)) {
            String newUrl = url;
            if (url.contains("&" + param)) {
                String values = AppTools.getUrlParams(url, param);
                if (StringUtil.isNotEmpty(values)) {
                    String paramStr = "&" + param + "=" + values;
                    newUrl = url.replaceAll(paramStr, "");
                } else {
                    newUrl = url.replaceAll("&" + param, "");
                }
            } else if (url.contains(param)) { //如果是第一个参数没有&符号
                String values = AppTools.getUrlParams(url, param);
                if (StringUtil.isNotEmpty(values)) {
                    String paramStr = param + "=" + values;
                    newUrl = url.replaceAll(paramStr, "");
                } else {
                    newUrl = url.replaceAll(param, "");
                }
            }
            return newUrl;
        }
        return "";
    }

    public static String getPackageName() {
        return App.getContext().getPackageName();
    }

    /**
     * 根据包名获取App的名字
     */
    public static String getAppName(Context context) {
        String pkgName = getPackageName();
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo info = pm.getApplicationInfo(pkgName, 0);
            return info.loadLabel(pm).toString();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断锁屏
     *
     * @param constext
     * @return
     */
    public static boolean isKeyguard(Context constext) {
        KeyguardManager km = (KeyguardManager) constext.getSystemService(Context.KEYGUARD_SERVICE);
        return km.inKeyguardRestrictedInputMode();

    }

    public static void saveLogToFile(String str) {
        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) { // SD卡根目录的hello.text
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator +
                    "hello.txt";
        } else  // 系统下载缓存根目录的hello.text
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator +
                    "hello.txt";

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(str.getBytes());
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片到系统图库
     *
     * @param context
     * @param bitmap
     */
    public static String saveImageToGallery(Context context, Bitmap bitmap) {
        if (bitmap == null || context == null) {
            return null;
        }

        //解决安卓4.4的保存图片bug
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (pref == null) {
                pref = context.getSharedPreferences("fixMediaDir", context.MODE_PRIVATE);
            }
            boolean created = pref.getBoolean("created", false);
            //没有创建，就手动创建相册
            if (!created) {
                File sdcard = Environment.getExternalStorageDirectory();
                if (sdcard != null) {
                    File mediaDir = new File(sdcard, "DCIM/Camera");
                    if (!mediaDir.exists()) {
                        mediaDir.mkdirs();
                    }
                }
                //修复完成设置标志。
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("created", true);
                editor.commit();
            }
        }
        //把图片插入到系统图库
        String urlStr = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bitmap, null, null);
        //通知图库更新
        //context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
        // ("file://" + Environment.getExternalStorageDirectory())));

        return urlStr;
    }

    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    public static String getSystem() {
        String SYS = "";
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                SYS = SYS_MIUI;//小米
            } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || prop.getProperty(KEY_EMUI_VERSION, null) != null
                    || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                SYS = SYS_EMUI;//华为
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                SYS = SYS_FLYME;//魅族
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
            return SYS;
        }
        return SYS;
    }

    public static boolean isMiui() {
        return SYS_MIUI.equals(getSystem());
    }

    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static void copytoClipboard(Context c, String text) {
        if (TextUtils.isEmpty(text))
            return;

        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) c.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", text);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 判断当前Activity是不是栈里的唯一一个Activity
     *
     * @param ctx
     * @return
     */
    public static boolean isOnlyOneActivity(Context ctx) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfos = am.getRunningTasks(1);

        if (taskInfos != null && taskInfos.size() > 0) {
            ActivityManager.RunningTaskInfo taskInfo = taskInfos.get(0);

            if (taskInfo != null && taskInfo.numActivities == 1) {
                return true;
            }
        }

        return false;
    }

    public static void startApp(Context context, String packageName, Bundle datas) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);

            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities
                    (resolveIntent, 0);
            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String pm = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName(pm, className);
                intent.setComponent(cn);
                if (datas != null) {
                    intent.putExtras(datas);
                }
                context.startActivity(intent);
            } else {
                BaseToast.showShort("应用未安装");
            }
        } catch (Exception e) {
            ExceptionUtil.showException(e);
            BaseToast.showShort("应用未安装");
        }
    }

    public static boolean isAppExist(Context context, String pkgName) {
        if (StringUtil.isEmpty(pkgName)) {
            return false;
        }

        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(pkgName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 到系统浏览器
     */
    public static void goSystemBroswer(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            BaseToast.showShort("地址为空");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(intent);
        } catch (Exception e) {
            BaseToast.showShort("地址错误");
            LogUtils.d("地址错误");
        }
    }

    public static boolean isPic(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }

        int i = url.lastIndexOf(".");
        String suffix = url.substring(i, url.length());
        if (".jpg".equals(suffix) || ".jpeg".equals(suffix) || ".png".equals(suffix) || ".bmp".equals(suffix)) {
            return true;
        }

        return false;
    }

    public static boolean isListEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }


}
