package com.ppm.designpattern.bridge.complex;

public class SmallCoffee extends Coffee {
    public SmallCoffee(CoffeeAdditives impl) {
        super(impl);
    }

    @Override
    void makeCoffee() {
        impl.addSomething();
        System.out.println("small Coffee");
    }
}
