package com.ppm.ppcomon.widget.window;

import java.io.Serializable;

/**
 * @author by lpc on 2018/1/24.
 */
public class TypeBean implements Serializable {

    /**
     * id : 1
     * name : 完善手机
     */

    private String id;
    private String name;

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
}
