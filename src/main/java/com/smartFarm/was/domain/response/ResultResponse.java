package com.smartFarm.was.domain.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResultResponse<T> {

    private final HttpStatus httpStatus;
    private final int resultCode;
    private final String resultMessage;
    private final T data;

    public ResultResponse(HttpStatus httpStatus, int resultCode, String resultMessage) {
        this(httpStatus, resultCode, resultMessage, null);
    }
    public ResultResponse(HttpStatus httpStatus, int resultCode, String resultMessage, T data) {
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }
}
