package com.ppm.algorithm.generic;

public class GenericMethodTest {

    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s", element);
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.println();
        printArray(intArray);
        System.out.println();
        printArray(doubles);
        System.out.println();
        printArray(charArray);
    }
}
