package com.ppm.algorithm.algorithm;

import org.junit.Test;

public class Algorithm {

    @Test
    public void main() {
//        int nums[] = {1, 2, 2, 2, 3, 3, 4, 4, 5};
//        removeDuplicates(nums);

//        System.out.print(gcd(100, 120));

        String s = "2019-03-12 19:37:08";
        System.out.println(s.substring(0, 10));
        System.out.println(s.substring(11, s.length()));
    }

    /**
     * 原地删除数组中重复的值
     */
    private int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return nums.length;
        }

        int number = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[number]) {
                number++;
                nums[number] = nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

        return ++number;
    }

    /**
     * 翻转数组
     */
    public void reverse() {

    }

    private int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                max = prices[i + 1] - prices[i];
            }
        }

        return max;
    }

    /**
     * 欧几里得算法
     * 计算两个非负整数p和q的最大公约数：若 q是0，则最大公约数为p。
     * 否则，将p除以q得到余数r,p和q的最大公约数即为q和r的最大公约数
     */
    private int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
