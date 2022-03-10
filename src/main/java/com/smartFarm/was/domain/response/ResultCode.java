package com.smartFarm.was.domain.response;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(0, "SUCCESS"),

    PAGE_NOT_FOUND(9001, "PAGE_NOT_FOUND"),
    DENIED(9002, "DENIED"),
    INTERVAL_SERVER_ERROR(9003, "ERROR"),

    UNAUTHORIZED(8001, "UNAUTHORIZED"),
    USER_NOT_FOUND(8002, "USER_NOT_FOUND"),
    EXISTED_MEMBER(8003, "EXISTED_MEMBER"),

    INVALID_PARAMETER(7001, "INVALID_PARAMETER");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
