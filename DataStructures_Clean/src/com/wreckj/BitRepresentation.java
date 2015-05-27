package com.wreckj;

/**
 * Created by jagsir on 25/04/15.
 */
public class BitRepresentation {
    static public void main(String []args) {
        int MAX_VALUE =0xFFF0FCFF;
        int test_value = (-1) * 0xCCABCDEF;

        int one = 1;
        int mask = one;
        for(int i = 32; i >= 0; i--) {
            mask = one << i;
            if((mask & test_value) == 0) {
                System.out.print("0");
            } else {
                System.out.print("1");
            }
        }
    }
}
