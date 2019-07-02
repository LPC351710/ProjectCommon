package com.ppm.netapp;

import com.qujianpan.common.log.XLog;

public class Test {
    private static final String LOG_MSG = "KLog is a so cool Log Tool!";

    public void initLog() {
        XLog.init(true, "Kai");
    }

    public void testLog() {
        XLog.v(LOG_MSG);
        XLog.d(LOG_MSG);
        XLog.i(LOG_MSG);
        XLog.w(LOG_MSG);
        XLog.e(LOG_MSG);
        XLog.a(LOG_MSG);
    }
}
