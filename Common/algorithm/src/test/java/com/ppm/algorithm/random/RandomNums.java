package com.ppm.algorithm.random;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomNums {

    @Test
    public void testRandom() {
        List<RandomBean> randomBeans = new ArrayList<>();
        randomBeans.add(new RandomBean(0.8f, 1));
        randomBeans.add(new RandomBean(0.25f, 2));
        randomBeans.add(new RandomBean(0.25f, 3));
        randomBeans.add(new RandomBean(0.25f, 4));

        int countl = 0;
        int counts = 0;
        int countw = 0;
        int countd = 0;
        for (int i = 0; i < 2000000; i++) {
            int res = calRandom(randomBeans);
            if (res == 1) {
                countl++;
            } else if (res == 2) {
                counts++;
            } else if (res == 3) {
                countw++;
            } else if (res == 4) {
                countd++;
            }
        }

        System.out.println("1: " + countl);
        System.out.println("2: " + counts);
        System.out.println("3: " + countw);
        System.out.println("4: " + countd);
    }

    private int calRandom(List<RandomBean> configList) {
        double random = Math.random();
        double p = 0;
        for (int i = 0; i < configList.size(); i++) {
            RandomBean adConfig = configList.get(i);
            double weight = adConfig.pro;
            if (random > p && random < (p += weight)) {
                return adConfig.id;
            }
        }
        return -1;
    }

    public class RandomBean {

        public RandomBean(float pro, int id) {
            this.pro = pro;
            this.id = id;
        }

        float pro;
        int id;
    }
}
