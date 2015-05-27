package com.wreckj;

/**
 * Created by jagsir on 19/05/15.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
class In {
    public static Integer[] readInts(String filename) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {

        } catch (Exception e) {

        }
        return (Integer[])al.toArray();
    }
}
public class BinarySearch
{
    public static int rank(int key, int[] a)
    {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        Integer[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        /*while (!StdIn.isEmpty())
        {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }*/
    }
}