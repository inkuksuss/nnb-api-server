package com.smartFarm.was.domain.model;

import java.sql.Timestamp;

public class Token {

    private final int second = 86400 * 1000;

    private Long tokenId;
    private Long memberId;
    private String accessTokenValue;
    private Timestamp accessTokenCreated;
    private Timestamp accessTokenDestroyed;

    public Token(Long memberId, String accessTokenValue) {
        this.memberId = memberId;
        this.accessTokenValue = accessTokenValue;
        this.accessTokenCreated = new Timestamp(System.currentTimeMillis());
        this.accessTokenDestroyed = new Timestamp(System.currentTimeMillis() + second);
    }
}
