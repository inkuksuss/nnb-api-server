package com.smartFarm.was.domain.entity.sub;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    OWNER("owner", true),
    PUBLIC("public", false),
    PRIVATE("private", false),
    DELETE("delete", false);


    private final String statusValue;
    private final boolean isOwner;
}
