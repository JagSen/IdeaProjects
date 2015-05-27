package com.wreckj;

/**
 * Created by jagsir on 5/05/15.
 */
/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class IdeaOne {
    public static int getNumberSameAsIndex(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >>> 1);
            if (numbers[middle] == middle) {
                return middle;
            }

            if (numbers[middle] > middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
    }

    public static int getNumberSameAsIndexRRun(int[] numbers) {

    if(numbers==null||numbers.length==0) {
        return -1;
    } else {
        return getNumberSameAsIndexR(numbers, 0, numbers.length - 1);
    }
}
    public static int getNumberSameAsIndexR(int[] numbers, int start, int end) {
        int middle = start + ((end - start) >>> 1);
        if(numbers[middle] == middle) {
            return middle;
        }

        if(numbers[middle] > middle) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
        if(start <= end) {
            return getNumberSameAsIndexR(numbers, start, end);
        } else {
            return -1;
        }
    }

    // ----------------------- TEST CODE -----------------------
    private static void test(String testName, int[] numbers, int expected) {
        if(getNumberSameAsIndexRRun(numbers) == expected) {
            System.out.println(testName + " passed.");
        }
        else {
            System.out.println(testName + " FAILED.");
        }
    }

    private static void test1() {
        int[] numbers = {-3, -1, 1, 3, 5};
        int expected = 3;
        test("test1", numbers, expected);
    }

    private static void test2() {
        int[] numbers = {0, 1, 3, 5, 6};
        int expected = 0;
        test("test2", numbers, expected);
    }

    private static void test3() {
        int[] numbers = {-1, 0, 1, 2, 4};
        int expected = 4;
        test("test3", numbers, expected);
    }

    private static void test4() {
        int[] numbers = {-1, 0, 1, 2, 5};
        int expected = -1;
        test("test4", numbers, expected);
    }

    private static void test5() {
        int[] numbers = {0};
        int expected = 0;
        test("test5", numbers, expected);
    }

    private static void test6() {
        int[] numbers = {10};
        int expected = -1;
        test("test6", numbers, expected);
    }

    public static void main (String[] args) throws java.lang.Exception {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }
}