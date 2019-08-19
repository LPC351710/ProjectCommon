package com.ppm.kotlindemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Collection {


    @Test
    public void main() {

        List<Object> list = new ArrayList<>();
        list.add("132");
        list.add(new Collection());

        List<? super String> list1 = new ArrayList<>();
    }
}
