package com.ppm.algorithm.reflect.proxy.dynamic;

import com.ppm.algorithm.reflect.proxy.FontProvider;
import com.ppm.algorithm.reflect.proxy.FontProviderFromDisk;

import java.lang.reflect.Proxy;

public class ResourceProvider {

    public static FontProvider getFontProvider() {
        Class<FontProvider> targetClass = FontProvider.class;
        FontProvider fontProvider = (FontProvider) Proxy.newProxyInstance(targetClass.getClassLoader(),
                new Class[]{targetClass}, new ProviderHandler(new FontProviderFromDisk()));
        return fontProvider;
    }
}
