package com.ppm.designpattern.bridge.complex;

public abstract class Coffee {

    protected CoffeeAdditives impl;

    public Coffee(CoffeeAdditives impl) {
        this.impl = impl;
    }

    abstract void makeCoffee();
}
