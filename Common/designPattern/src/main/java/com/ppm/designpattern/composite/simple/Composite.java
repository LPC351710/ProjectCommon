package com.ppm.designpattern.composite.simple;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    private List<Component> components = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void doSomething() {
        System.out.println(name);
        if (null != components) {
            for (Component component : components) {
                component.doSomething();
            }
        }
    }

    public void addChild(Component child) {
        components.add(child);
    }

    public void removeChild(Component child) {
        components.remove(child);
    }

    public Component getChildren(int index) {
        return components.get(index);
    }

}
