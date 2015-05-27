package com.wreckj.problems;

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Created by jagsir on 5/05/15.
 */

class Point {
    public int x = 0;
    public int y = 0;
    public int xMax;
    public int yMax;
    //TODO: logic to check if this node has been visited just now.
    Point(){

    }
    Point(int xMax, int  yMax){
        this.xMax = xMax;
        this.yMax = yMax;
    }

    void setPos(int x, int y){
        if(x < xMax) {
            this.x = x;
        }
        if(y < yMax) {
            this.y = y;
        }
        assert (this.x >= xMax);
        assert (this.y >= yMax);
    }

    boolean equals(Point sec) {
        return (this.x == sec.x) && (this.y == sec.y);
    }
    boolean lastNode(){
        return (this.x == this.xMax-1) && (this.y == this.yMax-1);
    }
    boolean lastReachedRightWall() {
        return (this.y == this.yMax - 1);
    }
    boolean lastReachedDownWall() {
        return (this.x == this.xMax - 1);
    }
}
public class MaxGiftValue {
    public static int getRetrieveMaxGiftValue(int [][]giftMatrix) {
        int [][]gm = giftMatrix;
        int maxRows = gm.length;
        int maxCols = gm[maxRows-1].length;
        Point current = new Point(maxRows, maxCols);
        Point right = new Point(maxRows, maxCols);
        Point down = new Point(maxRows, maxCols);

        ArrayList<Integer> gifts = new ArrayList<Integer>();
        int totalValue = 0;
        gifts.add(gm[current.x][current.y]);

        loop:
        for(;;) {
            right.setPos(current.x, current.y + 1);
            down.setPos(current.x + 1, current.y);

            if (gm[down.x][down.y] < gm[right.x][right.y]) {
                current.setPos(right.x, right.y);
            } else {
                current.setPos(down.x, down.y);
            }
            if(down.lastNode()){
                gifts.add(gm[down.x][down.y]);
                break loop;
            }
            if (right.lastNode()||right.lastReachedRightWall()) {
                gifts.add(gm[right.x][right.y]);
                break loop;
            }
            gifts.add(gm[current.x][current.y]);
        }

        for(int gift : gifts) {
            totalValue += gift;
        }
        return totalValue;
    }
    // ----------------------- TEST CODE -----------------------
    private static void test(String testName, int[][] numbers, int expected) {
        if(getRetrieveMaxGiftValue(numbers) == expected) {
            System.out.println(testName + " passed.");
        }
        else {
            System.out.println(testName + " FAILED.");
        }
    }

    private static void test1() {
        int[] numbers1 = { 1, 10,  3,  8};
        int[] numbers2 = {12,  2,  9,  6};
        int[] numbers3 = { 5,  7,  4, 11};
        int[] numbers4 = { 3,  7, 16,  5};

        int [][]giftMatrix = {numbers1, numbers2, numbers3, numbers4};
        int expected = 53;
        test("test1", giftMatrix, expected);
    }

    private static void test2() {
        int[] numbers1 = { 1, 10,  3,  8};
        int[] numbers2 = {12,  2,  9,  6};
        int[] numbers3 = { 5,  7,  8, 11};
        int[] numbers4 = { 3,  7, 16,  5};

        int [][]giftMatrix = {numbers1, numbers2, numbers3, numbers4};
        int expected = 54;
        test("test2", giftMatrix, expected);
    }
    private static void test3() {
        int[] numbers1 = { 1, 10,  3,  8, 10};
        int[] numbers2 = {12,  2,  9,  6, 11};
        int[] numbers3 = { 5,  7,  8, 18, 12};
        int[] numbers4 = { 3,  7, 16,  5,  3};
        int[] numbers5 = { 3,  7, 16,  5,  8};

        int [][]giftMatrix = {numbers1, numbers2, numbers3, numbers4, numbers5};
        int expected = 56;
        test("test3", giftMatrix, expected);
    }
        public static void main (String[] args) throws java.lang.Exception {
            test1();
            test2();
            test3();
        }
    }