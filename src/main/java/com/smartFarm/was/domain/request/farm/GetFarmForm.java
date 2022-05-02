package com.smartFarm.was.domain.request.farm;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class GetFarmForm {

    /**
     * Farm
     */
    private String farmType;
    private String farmName;
    private String farmAddress;
    private String farmKind;

    /**
     * farm_member
     */
    private String fmAuthority;
    private String fmStatus;

    private String keyword;
}
