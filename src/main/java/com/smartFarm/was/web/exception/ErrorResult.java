package com.smartFarm.was.web.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter @Setter
@RequiredArgsConstructor
public class ErrorResult {

    private HttpStatus httpStatus;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    public ErrorResult(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}