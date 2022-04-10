package com.smartFarm.was.domain.request.member;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class LoginForm {

    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String memberEmail;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String memberPassword;
}
