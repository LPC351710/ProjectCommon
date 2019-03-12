package com.ppm.threaddemo;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

    public static class MyTestCallable implements Callable {

        @Override
        public String call() throws Exception {
            System.out.print("thread: " + Thread.currentThread().getName());
            return "Callable";
        }
    }

    @Test
    public static void main(String[] args) {
        MyTestCallable myTestCallable = new MyTestCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(myTestCallable);

        try {
            System.out.print(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
