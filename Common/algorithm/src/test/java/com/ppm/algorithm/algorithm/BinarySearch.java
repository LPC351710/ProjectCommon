package com.ppm.algorithm.algorithm;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = {6, 8, 4, 1, 5, 7, 9, 3};
        Arrays.sort(whitelist);

        for (int i = 0; i < whitelist.length; i++) {
            System.out.println(whitelist[i]);
        }
        int key = 8;
        System.out.println(String.valueOf(rank(key, whitelist)));
    }
}
