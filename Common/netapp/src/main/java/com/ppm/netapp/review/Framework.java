package com.ppm.netapp.review;

public class Framework {

    //ActivityManagerService

    //WindowManagerService

    //LayoutInflateService

    //PackageManagerService

    //InputManagerService

    //WindowManager

    //PackageManager

    //instrumentation 主要用来监控应用程序和系统的交互

    //ActivityManagerNative.getDefault()获取ActivityManageService(AMS)
    // ActivityManagerService是个远程service 通过Binder通信 由 ActivityManagerProxy代理
    //ActivityManagerProxy(AMP)实现IActivityManager接口 IActivityManager 是一个AIDL接口
    //AMP 是ActivityManagerNative 的内部类

    //ActivityStarter  ActivityStackSupervisor  ActivityStack

    //ApplicationThreadNative 实现

}
