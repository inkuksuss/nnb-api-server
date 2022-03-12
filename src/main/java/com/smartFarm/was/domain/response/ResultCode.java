package com.smartFarm.was.domain.response;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(0, "SUCCESS"),

    /**
     * common
     */
    PAGE_NOT_FOUND(9001, "PAGE_NOT_FOUND"),
    DENIED(9002, "DENIED"),
    FAIL(9003, "FAIL"),
    DB_ERROR(9004, "DB_ERROR"),
    INTERVAL_SERVER_ERROR(9005, "ERROR"),

    /**
     * member
     */
    UNAUTHORIZED(8001, "UNAUTHORIZED"),
    MEMBER_NOT_FOUND(8002, "MEMBER_NOT_FOUND"),
    EXISTED_MEMBER(8003, "EXISTED_MEMBER"),

    /**
     * validate
     */
    INVALID_PARAMETER(7001, "INVALID_PARAMETER");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
