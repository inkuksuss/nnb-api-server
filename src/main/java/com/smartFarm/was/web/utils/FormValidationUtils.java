package com.smartFarm.was.web.utils;


public class FormValidationUtils {

    private FormValidationUtils() {}

    public static boolean illegalLongValue(Long value) {
        return value == null || value <= 0L;
    }

    public static boolean illegalString(String value) {
        return value == null || value.trim().equals("");
    }
}
