package com.ppm.designpattern.chain.complex;

public abstract class AbstractRequest {
    private Object mObject;

    public AbstractRequest(Object object) {
        mObject = object;
    }

    public Object getContent() {
        return mObject;
    }

    public abstract int getRequestLevel();
}
