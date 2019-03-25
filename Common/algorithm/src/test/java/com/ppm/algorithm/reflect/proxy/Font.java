package com.ppm.algorithm.reflect.proxy;

public class Font {
    private String color;
    private String size;
    private String style;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void draw() {
        System.out.print(color + "draw");
    }
}
