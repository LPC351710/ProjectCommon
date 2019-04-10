package com.ppm.designpattern.chain.simple;

public abstract class Handler {
    public Handler successor;

    public abstract void handleRequest(String condition);

}
