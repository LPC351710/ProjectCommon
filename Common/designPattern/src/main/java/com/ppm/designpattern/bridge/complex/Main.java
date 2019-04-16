package com.ppm.designpattern.bridge.complex;

public class Main {

    public static void main(String[] args) {
        Ordinary ordinary = new Ordinary();
        Sugar sugar = new Sugar();

        LargeCoffee largeCoffee = new LargeCoffee(ordinary);
        largeCoffee.makeCoffee();
        SmallCoffee smallCoffee = new SmallCoffee(sugar);
        smallCoffee.makeCoffee();
    }
}
