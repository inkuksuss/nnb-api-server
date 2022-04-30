package com.smartFarm.was.domain.dto.farm;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
public class FarmDto {

    /**
     * Farm
     */
    private Long fFarmId;
    private String fFarmType;
    private String fFarmName;
    private String fFarmAddress;
    private String fStateDel;
    private String fFarmKind;
    private Timestamp fFarmCreated;


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
    private long mMemberId;
    private String mMemberName;
    private String mMemberPassword;
    private String mMemberEmail;
    private String mMemberPhone;
    private String mMemberAddress;
    private String mPrivacyConsent;
    private String mMemberAuthority;
    private Timestamp mMemberBirthday;
    private Timestamp mMemberCreated;
    private Timestamp mMemberLastUpdated;
    private Timestamp mMemberLastAccessed;
    private char mStateDel;

    private String keyword;
}
