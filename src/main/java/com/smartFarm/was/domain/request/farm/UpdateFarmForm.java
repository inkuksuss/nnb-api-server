package com.smartFarm.was.domain.request.farm;

import lombok.Getter;

@Getter
public class UpdateFarmForm {

    private String farmType;
    private String farmName;
    private String farmAddress;
    private String farmKind;

    private String fmFarmStatus;
}
