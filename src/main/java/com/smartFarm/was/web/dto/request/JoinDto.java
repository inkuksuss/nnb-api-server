package com.smartFarm.was.web.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
public class JoinDto {

    @NotNull
    private String memberName;

    @NotNull @Email
    private String memberEmail;

    @NotNull
    private String memberPassword;

    @NotNull
    private String memberPhone;

    @NotNull
    private String memberAddress;

    @NotNull
    private String privacyConsent;

    @NotNull
    private Timestamp memberBirthday;

    public JoinDto(String memberName, String memberEmail, String memberPassword, String memberPhone, String memberAddress, String privacyConsent, Timestamp memberBirthday) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.privacyConsent = privacyConsent;
        this.memberBirthday = memberBirthday;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    @Override
    public String toString() {
        return "JoinDto{" +
                "memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", memberBirthday=" + memberBirthday +
                '}';
    }
}
