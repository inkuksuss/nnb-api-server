package com.smartFarm.was.domain.response;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(0, "SUCCESS"),

    PAGE_NOT_FOUND(9001, "PAGE_NOT_FOUND"),
    DENIED(9002, "DENIED"),
    FORBIDDEN(9003, "FORBIDDEN"),
    FAIL(9004, "FAIL"),
    DB_ERROR(9005, "DB_ERROR"),
    INTERVAL_SERVER_ERROR(9006, "ERROR"),

    UNAUTHORIZED(8001, "UNAUTHORIZED"),
    MEMBER_NOT_FOUND(8002, "MEMBER_NOT_FOUND"),
    EXISTED_MEMBER(8003, "EXISTED_MEMBER"),
    DELETED_CONTENTS(8004, "DELETED_CONTENTS"),
    PRIVATE_CONTENTS(8005, "PRIVATE_CONTENTS"),

    INVALID_PARAMETER(7001, "INVALID_PARAMETER");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
