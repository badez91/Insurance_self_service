package com.my.insurance.util;

import java.time.LocalDate;

public class NricUtil {

    public static LocalDate extractDob(String nric) {
        String yy = nric.substring(0,2);
        String mm = nric.substring(2,4);
        String dd = nric.substring(4,6);

        int year = Integer.parseInt(yy);
        year += (year > 30) ? 1900 : 2000;
        return LocalDate.of(year, Integer.parseInt(mm), Integer.parseInt(dd));
    }

    public static String extractGender(String nric){
        int lastDigit = Integer.parseInt(nric.substring(11));
        return (lastDigit % 2 == 0) ? "F" : "M";
    }
}
