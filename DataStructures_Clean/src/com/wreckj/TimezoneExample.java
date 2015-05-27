package com.wreckj;

/**
 * Created by jagsir on 25/04/15.
 */
import java.util.*;
import java.text.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimezoneExample {


    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        Date localTime = new Date(ts);
        String format = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        System.out.println(sdf.format(new Date()));

        // Convert Local Time to PDT (Works Fine)
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        Date gmtTime = new Date(sdf.format(localTime));
        System.out.println("Local:" + localTime.toString() + "," + localTime.getTime() + " --> PST time:"
                + gmtTime.toString() + "," + gmtTime.getTime());

        // Convert UTC to Local Time
        Date fromGmt = new Date(gmtTime.getTime() + TimeZone.getDefault().getOffset(localTime.getTime()));
        System.out.println("PST time:" + gmtTime.toString() + "," + gmtTime.getTime() + " --> Local:"
                + fromGmt.toString() + "-" + fromGmt.getTime());
    }

    public static void main_localTime(String[] args) {
        // Create a calendar object and set it time based on the local
        // time zone
        Calendar localTime = Calendar.getInstance();

        int hour = localTime.get(Calendar.HOUR);
        int minute = localTime.get(Calendar.MINUTE);
        int second = localTime.get(Calendar.SECOND);


        // Print the local time
        System.out.printf("Local time  : %02d:%02d:%02d\n", hour, minute, second);


        Calendar sydneyTime = new GregorianCalendar(TimeZone.getTimeZone("AEDT"));
        sydneyTime.setTimeInMillis(localTime.getTimeInMillis());
        hour = sydneyTime.get(Calendar.HOUR);
        minute = sydneyTime.get(Calendar.MINUTE);
        second = sydneyTime.get(Calendar.SECOND);


        // Print the local time in Germany time zone
        System.out.printf("Germany time: %02d:%02d:%02d\n", hour, minute, second);
    }
}