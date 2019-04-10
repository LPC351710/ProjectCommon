package com.ppm.designpattern.decorator;

public class ExpensiveCloth extends PersonCloth {

    public ExpensiveCloth(Person person) {
        super(person);
    }

    private void dressShirt() {
        System.out.println("dress Shirt");
    }

    private void dressLeather() {
        System.out.println("dress Leather");
    }

    private void dressJean() {
        System.out.println("dress jeans");
    }

    @Override
    public void dressed() {
        super.dressed();
        dressShirt();
        dressJean();
        dressLeather();
    }
}
