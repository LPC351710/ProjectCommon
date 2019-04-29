package com.ppm.designpattern.composite.complex;

import java.util.List;

public class File extends Dir {

    public File(String name) {
        super(name);
    }

    @Override
    public void addDir(Dir dir) {
        throw new UnsupportedOperationException("File can not support this Action");
    }

    @Override
    public void removeDri(Dir dir) {
        throw new UnsupportedOperationException("File can not support this Action");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("File can not support this Action");
    }

    @Override
    public void print() {
        System.out.print(getName());
    }

    @Override
    public List<Dir> getFiles() {
        throw new UnsupportedOperationException("File can not support this Action");
    }
}
