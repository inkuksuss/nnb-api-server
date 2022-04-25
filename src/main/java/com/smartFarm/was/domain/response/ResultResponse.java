package com.smartFarm.was.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ResultResponse<T> {

    private HttpStatus httpStatus;
    private int resultCode;
    private String resultMessage;
    private T data;

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
