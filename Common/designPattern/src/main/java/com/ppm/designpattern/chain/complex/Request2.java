package com.ppm.designpattern.chain.complex;

public class Request2 extends AbstractRequest {

    public Request2(Object object) {
        super(object);
    }

    @Override
    public int getRequestLevel() {
        return 2;
    }
}
