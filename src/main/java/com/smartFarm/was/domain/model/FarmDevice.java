package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.model.sub.Authority;

import java.sql.Timestamp;

public class FarmDevice {

    private Integer farmId;
    private Integer deviceId;
    private Timestamp fdCreated;
    private Authority authority;
    private String status;
}
