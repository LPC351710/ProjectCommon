package com.ppm.designpatterns.chain.simple;

public abstract class Handler {
    public Handler successor;

    public abstract void handleRequest(String condition);

}
