package com.ppm.designpatterns.chain.complex;

public class Handler3 extends AbstractHandler {
    @Override
    public int getHandleLevel() {
        return 3;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.print("Handler3 handle level " + getHandleLevel());
    }
}
