package com.ppm.algorithm.reflect;

public class TestReflect {
    private String test;

    private int test2;

    public TestReflect() {
    }

    public TestReflect(String test) {
        this.test = test;
    }

    public TestReflect(String test, int test2) {
        this.test = test;
        this.test2 = test2;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getTest2() {
        return test2;
    }

    public void setTest2(int test2) {
        this.test2 = test2;
    }

    public void reflectMethod(String reStr) {
        System.out.println(reStr);
    }

    private void privateMethod(String s) {
        System.out.println(s);
    }
}
