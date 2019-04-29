package com.ppm.designpattern.composite.simple;

public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void doSomething();
}
