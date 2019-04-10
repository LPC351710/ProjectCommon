package com.ppm.algorithm.reflect.proxy;

import com.ppm.algorithm.reflect.proxy.statics.ResourceProvider;

public class View {
    private Font font;

    public View() {
        font = ResourceProvider.getFontProvider().getFont("");
    }

    private void draw() {
        if (font != null) {
            font.draw();
        }
    }
}
