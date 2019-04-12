package com.ppm.designpattern.proxy;

public class RealSubject extends Subject {

    @Override
    void visit() {
        System.out.println("Real Subject");
    }
}
