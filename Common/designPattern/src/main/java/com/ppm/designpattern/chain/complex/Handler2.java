package com.ppm.designpattern.chain.complex;

public class Handler2 extends AbstractHandler{
    @Override
    public int getHandleLevel() {
        return 2;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.print("Handler2 handle level " + getHandleLevel());
    }
}
