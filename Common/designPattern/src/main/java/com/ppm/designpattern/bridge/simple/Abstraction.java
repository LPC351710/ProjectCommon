package com.ppm.designpattern.bridge.simple;

public abstract class Abstraction {
    private Implementor mImplementor;

    public Abstraction(Implementor mImplementor) {
        this.mImplementor = mImplementor;
    }

    public void operation() {
        mImplementor.operationImpl();
    }
}
