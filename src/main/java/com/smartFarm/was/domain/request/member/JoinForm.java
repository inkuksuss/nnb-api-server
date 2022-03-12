package com.smartFarm.was.domain.request.member;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Builder
public class JoinForm {

    @NotEmpty(message = "이름을 입력해주세요.")
    private String memberName;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String memberEmail;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String memberPassword;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String memberPhone;

    @NotEmpty(message = "주소를 입력해주세요.")
    private String memberAddress;

    @NotEmpty(message = "개인정보동의 해주세요.")
    private String privacyConsent;

    @NotEmpty(message = "생년월일을 입력해주세요.")
    private Timestamp memberBirthday;

    @NotEmpty(message = "권한이 올바르지 않습니다.")
    private String memberAuthority;

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
}
