package com.ppm.algorithm.reflect.proxy.statics;

import com.ppm.algorithm.reflect.proxy.CacheFontProvider;
import com.ppm.algorithm.reflect.proxy.FontProvider;
import com.ppm.algorithm.reflect.proxy.FontProviderFromDisk;

public class ResourceProvider {

    public static FontProvider getFontProvider() {
        return new CacheFontProvider(new FontProviderFromDisk());
    }
}
