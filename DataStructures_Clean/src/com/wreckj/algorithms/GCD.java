package com.wreckj.algorithms;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by jagsir on 3/05/15.
 */
public class GCD {
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
    static String getInputFromUser(){
        System.out.println("Do you want to continue: \nQuit: (Q/N) \nContinue: (C/Y)\n\t ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    public static void main(String[] args){
        Random rand = new Random();
        int first;
        int second;

        do {
            int gcd = 0;
            do {
                first = rand.nextInt();
                second = rand.nextInt();
                first = Math.abs(first);
                second = Math.abs(second);
                if (first < second) {
                    int temp = second;
                    second = first;
                    first = temp;
                }
                gcd = gcd(first, second);
                System.out.printf("\nGCD of %d and %d is %d", first, second, gcd);
            } while (gcd < 1000);
        } while (getInputFromUser().equalsIgnoreCase("Y"));
    }
}
