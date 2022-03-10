package com.smartFarm.was.web.exception.custom;

public class ExistedMemberException extends RuntimeException {

    public ExistedMemberException() {}

    public ExistedMemberException(String message) {
        super(message);
    }
}
