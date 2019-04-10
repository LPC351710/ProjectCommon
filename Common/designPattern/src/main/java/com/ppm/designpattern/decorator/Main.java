package com.ppm.designpattern.decorator;

public class Main {

    public static void main(String[] args) {
        Person person = new Boy();
        PersonCloth cheapCloth = new CheapCloth(person);
        cheapCloth.dressed();

        PersonCloth clothExPensive = new ExpensiveCloth(person);
        clothExPensive.dressed();
    }
}
