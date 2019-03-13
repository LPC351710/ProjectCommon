package com.ppm.designpatterns.chain.simple;

public class ConcreteHandler extends Handler {
    @Override
    public void handleRequest(String condition) {
        if ("ConcreteHandler".equals(condition)) {
            System.out.println("ConcreteHandler handled");
        } else {
            successor.handleRequest(condition);
        }
    }
}
