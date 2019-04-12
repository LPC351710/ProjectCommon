package com.ppm.designpattern.proxy;

public class Main {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(realSubject);
        proxySubject.visit();
    }
}
