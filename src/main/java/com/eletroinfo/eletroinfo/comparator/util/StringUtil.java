package com.eletroinfo.eletroinfo.comparator.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String s){
        return (s == null || s.trim().equals(""));
    }

    public static String checkValue(String s){
        return isNullOrEmpty(s) ? "" : s;
    }

}
