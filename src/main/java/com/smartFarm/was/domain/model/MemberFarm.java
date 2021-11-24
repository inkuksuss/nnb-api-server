package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.model.sub.Authority;

import java.sql.Timestamp;

public class MemberFarm {

    private Integer farmId;
    private Integer memberId;
    private Timestamp fmCreated;
    private String status;
    private Authority authority;
}

