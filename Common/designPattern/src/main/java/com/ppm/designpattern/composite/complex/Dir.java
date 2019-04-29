package com.ppm.designpattern.composite.complex;

import java.util.ArrayList;
import java.util.List;

public abstract class Dir {

    public List<Dir> dirs = new ArrayList<>();

    private String name;

    public Dir(String name) {
        this.name = name;
    }

    public abstract void addDir(Dir dir);

    public abstract void removeDri(Dir dir);

    public abstract void clear();

    public abstract void print();

    public abstract List<Dir> getFiles();

    public String getName() {
        return name;
    }
}
