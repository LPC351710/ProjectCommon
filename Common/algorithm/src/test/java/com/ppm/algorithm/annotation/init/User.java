package com.ppm.algorithm.annotation.init;

public class User {

    @Init("test")
    public String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    @Init(value = "100")
    public void setAge(String age) {
        this.age = age;
    }
}
