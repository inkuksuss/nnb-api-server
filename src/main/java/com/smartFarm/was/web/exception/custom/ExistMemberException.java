package com.smartFarm.was.web.exception.custom;

public class ExistMemberException extends RuntimeException {
    public ExistMemberException() {
    }

    public ExistMemberException(String message) {
        super(message);
    }
}
