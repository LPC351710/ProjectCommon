package com.ppm.algorithm.reflect.proxy;

import java.util.Map;

public class CacheFontProvider implements FontProvider {

    private FontProvider mFontProvider;

    private Map<String, Font> cached;

    public CacheFontProvider(FontProvider fontProvider) {
        mFontProvider = fontProvider;
    }

    @Override
    public Font getFont(String name) {
        Font font = cached.get(name);
        if (font == null) {
            font = mFontProvider.getFont(name);
            cached.put(name, font);
        }
        return font;
    }
}
