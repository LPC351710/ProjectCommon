package com.ppm.ppcomon.base.model;

import java.io.Serializable;

public class GiftBean implements Serializable {

    /**
     * id : 1
     * name : 元旦大礼包
     * img : member/2018/03/29/1widMUNHZAuL9bj8NIPZDq4XQyAX.jpg
     * send_object : 1
     */

    private String id;
    private String name;
    private String img;
    private String send_object;
    private String start_at;
    private String end_at;
    private String bgcolor;
    private String flow_img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSend_object() {
        return send_object;
    }

    public void setSend_object(String send_object) {
        this.send_object = send_object;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getFlow_img() {
        return flow_img;
    }

    public void setFlow_img(String flow_img) {
        this.flow_img = flow_img;
    }
}
