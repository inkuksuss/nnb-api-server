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
        if (!FormValidationUtils.illegalString(value)) {
            return value.equals(Status.DELETE.name());
        } else {
            return false;
        }
    }

    public static boolean isPublic(String value) {
        if (!FormValidationUtils.illegalString(value)) {
            return value.equals(Status.PUBLIC.name());
        } else {
            return false;
        }
    }

    public static boolean isPrivate(String value) {
        if (!FormValidationUtils.illegalString(value)) {
            return value.equals(Status.PRIVATE.name());
        } else {
            return false;
        }
    }

    public static boolean isOwner(String value) {
        if (!FormValidationUtils.illegalString(value)) {
            return value.equals(Status.OWNER.name());
        } else {
            return false;
        }
    }

    public static boolean querySuccess(int result) {
        return result == 1;
    }
}
