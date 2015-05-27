package com.wreckj;

public class Euclid {
    public static void main (String[] argv) {
        int num1 = 792;
        int num2 = 348;
        Divisor_Calc euclid = new Divisor_Calc();
        System.out.println
                ("The gcd of " + num1 + " and " + num2 + " is: " +
                        euclid.gcd (num1, num2));
        long LIMIT = 1000000000l;
        int num3;
        long start, end;
        start = System.nanoTime();
        for (long li = 0; li < LIMIT; li ++ ) {
            if(num2 <= num1)
                num3 = num2;
        }
        end = System.nanoTime();
        System.out.println
                ("total time for remainder operation: " + (end -start) + " " + ", <= reps.");
        start = System.nanoTime();
        for (long li = 0; li < LIMIT; li ++ ) {
            num3 = num1 / num2;
        }
        end = System.nanoTime();
        System.out.println
                ("total time for remainder operation: " + (end -start) + " " + ", / reps.");

        start = System.nanoTime();
        for (long li = 0; li < LIMIT; li ++ ) {
            num3 = num1 % num2;
        }
        end = System.nanoTime();
        System.out.println
                ("total time for remainder operation: " + (end -start) + " " + ", % reps.");

    }
}

class Divisor_Calc {
    public int gcd (int num1, int num2) {
        if ( num1 % num2 == 0 && num2 <= num1 ) {
            return num2;
        } else {
            if (num1 < num2) {
                return gcd (num2, num1);
            } else {
                return gcd (num2, num1 % num2);
            }
        }
    }
}