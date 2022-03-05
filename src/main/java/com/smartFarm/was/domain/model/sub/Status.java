package com.smartFarm.was.domain.model.sub;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    OWNER("owner"),
    PUBLIC("public"),
    PRIVATE("private"),
    DELETE("delete");

    private final String statusValue;
}
