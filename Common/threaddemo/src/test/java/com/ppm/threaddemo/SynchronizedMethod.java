package com.ppm.threaddemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedMethod {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private synchronized void method() {

    }

    private void method1() {
        reentrantLock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void method2() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(); //锁分段

    }
}
