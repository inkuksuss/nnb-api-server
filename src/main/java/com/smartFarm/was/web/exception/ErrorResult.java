package com.smartFarm.was.web.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ErrorResult {

    private HttpStatus status;
    private String message;

    public ErrorResult(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}