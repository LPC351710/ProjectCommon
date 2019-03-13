package com.ppm.designpatterns.chain.simple;

public class ConcreteHandler1 extends Handler {

    @Override
    public void handleRequest(String condition) {
        if ("ConcreteHandler1".equals(condition)) {
            System.out.print("ConcreteHandler1 Handled");
        } else {
            successor.handleRequest(condition);
        }
    }
}
