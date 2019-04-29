package com.ppm.designpattern.composite.complex;

import java.util.Iterator;
import java.util.List;

public class Folder extends Dir {

    public Folder(String name) {
        super(name);
    }

    @Override
    public void addDir(Dir dir) {
        dirs.add(dir);
    }

    @Override
    public void removeDri(Dir dir) {
        dirs.remove(dir);
    }

    @Override
    public void clear() {
        dirs.clear();
    }

    @Override
    public void print() {
        System.out.println("print");
        Iterator<Dir> iterator = dirs.iterator();
        while (iterator.hasNext()) {
            Dir dir = iterator.next();
            dir.print();
            if (iterator.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.print(")");
    }

    @Override
    public List<Dir> getFiles() {
        return dirs;
    }
}
