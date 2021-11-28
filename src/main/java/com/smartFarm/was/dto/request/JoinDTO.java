package com.smartFarm.was.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.sql.Timestamp;

@RequiredArgsConstructor
@Getter
public class JoinDTO {

    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberPhone;
    private String memberAddress;
    private String privacyConsent;
    private Timestamp memberBirthday;

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}
