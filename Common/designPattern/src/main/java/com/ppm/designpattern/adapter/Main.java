package com.ppm.designpattern.adapter;

public class Main {

    public static void main(String[] args) {

        VoltAdapter voltAdapter = new VoltAdapter();
        System.out.println(voltAdapter.getVolt5());
    }
}
