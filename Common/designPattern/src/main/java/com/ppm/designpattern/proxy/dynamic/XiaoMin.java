package com.ppm.designpattern.proxy.dynamic;

public class XiaoMin implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("Xsubmit");
    }

    @Override
    public void burden() {
        System.out.println("Xburden");
    }

    @Override
    public void defend() {
        System.out.println("Xdefend");
    }

    @Override
    public void finish() {
        System.out.println("Xfinish");
    }
}
