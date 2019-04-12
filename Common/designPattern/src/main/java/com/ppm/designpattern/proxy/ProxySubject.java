package com.ppm.designpattern.proxy;

public class ProxySubject extends Subject {

    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    void visit() {
        realSubject.visit();
    }
}
