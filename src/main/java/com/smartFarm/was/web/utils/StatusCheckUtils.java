package com.smartFarm.was.web.utils;

import com.smartFarm.was.domain.entity.sub.Status;

public class StatusCheckUtils {

    private StatusCheckUtils() {}

    public static boolean isDeleted(char value) {
        if (value == 'Y') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDeleted(String value) {
        if (!FormValidationUtils.illegalStringValue(value)) {
            return value.equals(Status.DELETE);
        } else {
            return false;
        }
    }

    public static boolean isPublic(String value) {
        if (!FormValidationUtils.illegalStringValue(value)) {
            return value.equals(Status.PUBLIC);
        } else {
            return false;
        }
    }

    public static boolean isPrivate(String value) {
        if (!FormValidationUtils.illegalStringValue(value)) {
            return value.equals(Status.PRIVATE);
        } else {
            return false;
        }
    }

    public static boolean isOwner(String value) {
        if (!FormValidationUtils.illegalStringValue(value)) {
            return value.equals(Status.OWNER);
        } else {
            return false;
        }
    }
}
