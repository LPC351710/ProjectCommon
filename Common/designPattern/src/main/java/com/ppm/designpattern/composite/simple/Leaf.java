package com.ppm.designpattern.composite.simple;

public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println(name);
    }
}
