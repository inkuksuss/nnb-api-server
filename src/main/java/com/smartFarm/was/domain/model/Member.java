package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.model.sub.Authority;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Member {

    private Integer memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String privacyConsent;
    private Timestamp memberBirthday;
    private Timestamp memberCreated;
    private Timestamp memberLastUpdated;
    private Timestamp memberLastAccessed;
    private Authority authority;


    public Member(Integer memberId, String memberName, String memberPassword, String memberEmail, String memberPhone, String memberAddress, String privacyConsent, Timestamp memberBirthday, Timestamp memberCreated, Timestamp memberLastUpdated, Timestamp memberLastAccessed, Authority authority) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.privacyConsent = privacyConsent;
        this.memberBirthday = memberBirthday;
        this.memberCreated = memberCreated;
        this.memberLastUpdated = memberLastUpdated;
        this.memberLastAccessed = memberLastAccessed;
        this.authority = authority;
    }
}
