package com.smartFarm.was.domain.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class Result<T> {

    private final T data;
    private final Timestamp timestamp;

    public Result() {
        this(null);
    }

    public Result(@Nullable T data) {
        this.data = data;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
