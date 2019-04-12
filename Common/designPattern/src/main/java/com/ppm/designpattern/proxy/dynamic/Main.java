package com.ppm.designpattern.proxy.dynamic;

import java.lang.reflect.Proxy;

public class Main {

    /**
     * 动态代理
     */
    public static void main(String[] args) {
        ILawsuit xiAoMin = new XiaoMin();
        DynamicProxy dynamicProxy = new DynamicProxy(xiAoMin);
        ClassLoader classLoader = xiAoMin.getClass().getClassLoader();
        ILawsuit lawsuit = (ILawsuit) Proxy.newProxyInstance(classLoader,
                new Class[]{ILawsuit.class}, dynamicProxy);
        lawsuit.submit();
        lawsuit.burden();
        lawsuit.defend();
        lawsuit.finish();
    }
}
