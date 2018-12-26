package com.ppm.ppcomon.base.model;

import java.io.Serializable;

/**
 * @author by lpc on 2018/1/23.
 */
public class ShareBean implements Serializable {

    /**
     * id : 101827762
     * title : 商品1
     * img : 2017/04/26/FiCKeh2-0McNrWR8Yv5WVCtx32nN.jpg
     * url : http: //www.baidu.com
     * intro : sdfsadfdsadf
     * description : sdfasdfasfddas
     */

    private String id;
    private String title;
    private String img;
    private String url;
    private String intro;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
