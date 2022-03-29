package com.smartFarm.was.domain.entity.sub;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    ADMIN("ROLE_ADMIN", "ADMIN"),
    MEMBER("ROLE_MEMBER", "MEMBER"),
    ANONYMOUS("ROLE_ANONYMOUS", "ANONYMOUS");

    private final String role;
    private final String alias;
}
