package com.smartFarm.was.domain.dto.farm;

import com.smartFarm.was.domain.request.farm.AddFarmForm;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
public class FarmDto {

    /**
     * Farm
     */
    private Long farmId;
    private String farmType;
    private String farmName;
    private String farmAddress;
    private String farmKind;
    private Timestamp farmCreated;
    private char farmStateDel;

    /**
     * farm_member
     */
    private Long fmMemberId;
    private Long fmFarmId;
    private Timestamp fmCreated;
    private String fmAuthority;
    private String fmStatus;
    private char fmStateDel;

    /**
     * member
     */
    private long memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String PrivacyConsent;
    private String memberAuthority;
    private Timestamp memberBirthday;
    private Timestamp memberCreated;
    private Timestamp memberLastUpdated;
    private Timestamp memberLastAccessed;
    private char memberStateDel;

    private String keyword;

    public static FarmDto addFarmDto(AddFarmForm farm) {
        FarmDto farmDto = new FarmDto();
        farmDto.farmType = farm.getFarmType();
        farmDto.farmName = farm.getFarmName();
        farmDto.farmAddress = farm.getFarmAddress();
        farmDto.farmKind = farm.getFarmKind();
        farmDto.farmCreated = new Timestamp(System.currentTimeMillis());
        farmDto.farmStateDel = 'N';
        return farmDto;
    }
}
