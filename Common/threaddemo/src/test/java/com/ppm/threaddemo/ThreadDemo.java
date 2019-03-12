package com.ppm.threaddemo;

import org.junit.Test;

public class ThreadDemo extends Thread {


    public void run() {
        System.out.println("Hello World");
    }

    @Test
    public static void main(String[] args) {
        Thread mThread = new ThreadDemo();
        mThread.start();
    }
}
