package com.ppm.algorithm.reflect;

public class StudentImpl implements Student {

    @Override
    public void login() {
        System.out.println("login");
    }

    @Override
    public void submit() {
        System.out.println("submit");
    }
}
