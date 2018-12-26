package com.ppm.ppcomon.net;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lpc on 2017/05/03
 */
public class ErrorCode {

    public static final int ERR_CODE_SUCCESS = 0;

    public static final int LOGIN_FAIL_TIP1 = 20051;

    public static final int LOGIN_FAIL_TIP2 = 20011;

    public static final int LOGIN_FAIL_TIP3 = 20009;

    public static final int LOGIN_FAIL_TIP4 = 20012;

    public static final int LOGIN_FAIL_TIP5 = 20005;

    public static final String SUCCESS = "0";

    public static final int ERR_CODE_USER_TOKEN_INVALID = 10001;

    public static final int ERR_CODE_DATA_ILLEGAL = 100002;

    public static final int ERR_CODE_NO_DATA = -14002110;

    public static final int ERR_CODE_NO_NET = -14002111;

    public static final int ERR_CODE_HTTP_SOCKET_FAIL = -14002112;

    public static final int ERR_CODE_HTTP_CODE_ERROR = -14002113;

    public static final int ERR_CODE_HTTP_RESPONSE_ERROR = -14002114;

    public static final int ERR_CODE_HTTP_EXCEPTION = -14002115;

    public static final int ERR_CODE_HTTP_RESPONSE_ERROR_WIFI = -14002116;

    public static final int ERR_CODE_HTTP_AIRPLANE_MODE = -14002117;

    public static final Map<Integer, String> MAP_ERROR_CODE = new HashMap<Integer, String>(0);

    static {
        MAP_ERROR_CODE.put(ERR_CODE_SUCCESS, "成功");
        MAP_ERROR_CODE.put(ERR_CODE_NO_DATA, "没有数据");
        MAP_ERROR_CODE.put(ERR_CODE_NO_NET, "手机没有联网");
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_SOCKET_FAIL, "网络连接异常，请检测您的网络是否连接正常");      // "连接不上后台服务"
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_CODE_ERROR, "网络连接异常，请检测您的网络是否连接正常");        //
        // "后台服务HTTP响应异常"
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_RESPONSE_ERROR, "网络连接异常，请检测您的网络是否连接正常");        //
        // "后台服务响应数据异常" http CODE != 200
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_EXCEPTION, "网络连接异常，请检测您的网络是否连接正常");        // "HTTP解析异常"
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_RESPONSE_ERROR_WIFI, "wifi连接异常,请检测你的网络");
        MAP_ERROR_CODE.put(ERR_CODE_HTTP_AIRPLANE_MODE, "您设置了飞行模式");
        MAP_ERROR_CODE.put(ERR_CODE_DATA_ILLEGAL, "服务器返回数据异常");
        MAP_ERROR_CODE.put(ERR_CODE_USER_TOKEN_INVALID, "已过期，请重新登录");
    }
}
