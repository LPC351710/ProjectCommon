package com.ppm.ppcomon.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public final static String DATA_TOKEN_INDEX = "token";
    public final static String DATA_CID_INDEX = "cid"; // 个推使用的客户端ID
    public final static String DATA_DEVICE_ID_INDEX = "deviceid";
    public final static String DATA_NEW_WARNING_COUNT_INDEX = "message_count"; // 未读消息
    public final static String DATA_NOTIFICATION_SWITCH_CLOSED_INDEX = "notification_switch_closed"; // 推送开关


    /**
     * 普通字段存放地址
     */
    public static String UQ = "duxing-microstore_android";


    public static String getSharePreStr(Context mContext, String field) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        String s = sp.getString(field, "");//如果该字段没对应值，则取出字符串0
        return s;
    }

    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreInt(Context mContext, String field) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        int i = sp.getInt(field, 0);//如果该字段没对应值，则取出0
        return i;
    }

    //取出whichSp中field字段对应的boolean类型的值
    public static boolean getSharePreBoolean(Context mContext, String field) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        boolean i = sp.getBoolean(field, false);//如果该字段没对应值，则取出0
        return i;
    }

    //保存string类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String field, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        sp.edit().putString(field, value).apply();
    }

    //保存int类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String field, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        sp.edit().putInt(field, value).apply();
    }

    //保存boolean类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext, String field, Boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
        sp.edit().putBoolean(field, value).apply();
    }

    //清空保存的数据
    public static void clearSharePre(Context mContext) {
        try {
            SharedPreferences sp = mContext.getSharedPreferences(UQ, Context.MODE_PRIVATE);
            sp.edit().clear().apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
