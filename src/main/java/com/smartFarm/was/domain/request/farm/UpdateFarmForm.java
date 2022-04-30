package com.smartFarm.was.domain.request.farm;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UpdateFarmForm {

    private String farmType;
    private String farmName;
    private String farmAddress;
    private String farmKind;
}
