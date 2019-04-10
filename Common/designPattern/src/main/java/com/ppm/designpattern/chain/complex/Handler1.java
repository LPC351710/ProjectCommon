package com.ppm.designpattern.chain.complex;

public class Handler1 extends AbstractHandler {
    @Override
    public int getHandleLevel() {
        return 1;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.print("Handler1 handle level " + getHandleLevel());
    }
}
