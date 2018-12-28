package com.ppm.netapp.network.callback;

public final class ErrCode {

    public static final int SUCCESS = 0;

    public static final int CODE_DATA_ILLEGAL = 1001;

    public static final int CODE_NO_NETWORK = 1002;

    public static final int CODE_SERVER_ERR = 1003;

    public static final String MSG_DATA_ILLEGAL = "数据异常";

    public static final String MSG_NO_NETWORK = "无网络或网络异常";

    public static final String MSG_SERVER_ER = "服务异常，请稍后重试";
}
