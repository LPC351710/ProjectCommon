package com.ppm.designpattern.decorator;

public class CheapCloth extends PersonCloth {

    public CheapCloth(Person person) {
        super(person);
    }

    private void dressShort() {
        System.out.println("dressShort");
    }

    @Override
    public void dressed() {
        super.dressed();
        dressShort();
    }
}
