package com.smartFarm.was.domain.model;

import java.sql.Timestamp;

public class Token {

    private Integer tokenId;
    private Integer memberId;
    private String accessTokenValue;
    private Timestamp accessTokenCreated;
    private Timestamp accessTokenDestroyed;
    private String status;

}
