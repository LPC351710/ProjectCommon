package com.ppm.ppcomon.widget.addresspicker.entity;

import android.text.TextUtils;

/**
 * 省市县抽象，为了使用FastJson及LiteOrm之类的库，本类及其子类不可混淆
 * <br/>
 * Author:李玉江[QQ:1032694760]
 * DateTime:2016-10-15 19:06
 * Builder:Android Studio
 */
public abstract class Area extends JavaBean implements LinkageItem {
    private String area_id;
    private String area_name;

    public Area() {
        super();
    }

    public Area(String areaName) {
        this.area_name = areaName;
    }

    public Area(String areaId, String areaName) {
        this.area_id = areaId;
        this.area_name = areaName;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    @Override
    public Object getId() {
        return area_id;
    }

    @Override
    public String getName() {
        return area_name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Area)) {
            return false;
        }
        Area obj1 = (Area) obj;
        if (!TextUtils.isEmpty(area_id)) {
            return area_id.equals(obj1.getArea_id());
        }
        return area_name.equals(obj1.getArea_name());
    }

    @Override
    public String toString() {
        return "areaId=" + area_id + ",areaName=" + area_name;
    }

}