package com.ppm.algorithm;

import org.junit.Test;

public class Algorithm {

    @Test
    public void main() {
        int nums[] = {1, 2, 2, 2, 3, 3, 4, 4, 5};
        removeDuplicates(nums);
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
}
