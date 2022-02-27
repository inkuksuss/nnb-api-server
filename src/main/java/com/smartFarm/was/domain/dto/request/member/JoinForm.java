package com.smartFarm.was.domain.dto.request.member;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Builder
public class JoinForm {

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

    @NotNull
    private String memberAuthority;

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}
