package com.ppm.designpattern.bridge.complex;

public class LargeCoffee extends Coffee {

    public LargeCoffee(CoffeeAdditives impl) {
        super(impl);
    }

    @Override
    void makeCoffee() {
        impl.addSomething();
        System.out.println("large Coffee");
    }
}
