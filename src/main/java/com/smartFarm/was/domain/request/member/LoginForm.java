package com.smartFarm.was.domain.request.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@RequiredArgsConstructor
public class LoginForm {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email
    private String memberEmail;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String memberPassword;
}
