package com.smartFarm.was.domain.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class Result<T> {

    private T data;
    private Timestamp timestamp;

    public Result(T data) {
        this.data = data;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
