package com.ppm.threaddemo;

public class Bank {

    private double[] accounts;
    private Object lock = new Object();

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int form, int to, int amount) {
        synchronized (lock) {

        }
    }





}
