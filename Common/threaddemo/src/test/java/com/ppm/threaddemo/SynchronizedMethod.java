package com.ppm.threaddemo;

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


}
