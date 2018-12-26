package com.ppm.ppcomon.utils;

import android.util.Log;


/**
 * Log中输出错误信息
 */
public class ExceptionUtil {

    public final static String TAG = "AndroidRuntime";

    private final static boolean DEBUG = LogUtils.isDebug;

    public static void showException(Throwable e) {
        StackTraceElement[] stes = e.getStackTrace();
        log("<=======================Exception====================>");
        log(e.toString());
        for (StackTraceElement ste : stes) {
            log(ste.toString());
        }

        if (e.getCause() != null) {
            log("<================Exception Cause=====================>");
            log(e.getCause().toString());
            StackTraceElement[] stesCause = e.getCause().getStackTrace();
            if (stesCause != null) {
                for (StackTraceElement ste : stesCause) {
                    log(ste.toString());
                }
            }
        }
        log("<============================================================>");
    }

    private static void log(String msg) {
        if (DEBUG && msg != null)
            Log.e(TAG, getStackTraceMsg() + ": " + msg);
    }

    /**
     * 打印时添加调用日志处的类名，函数名，行数信息
     */
    private static String getStackTraceMsg() {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[5];
        return stackTrace.getFileName() + "(" + stackTrace.getLineNumber() + ") " + stackTrace.getMethodName();
    }
}
