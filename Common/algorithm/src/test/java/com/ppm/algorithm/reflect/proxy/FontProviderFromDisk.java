package com.ppm.algorithm.reflect.proxy;

public class FontProviderFromDisk implements FontProvider {

    @Override
    public Font getFont(String name) {
        Font font = new Font();
        font.setColor("red");
        return font;
    }
}
