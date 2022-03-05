package com.smartFarm.was.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class ResultVO<T> {

    private final int resultCode;
    private final String resultMessage;
    private final T data;
}
