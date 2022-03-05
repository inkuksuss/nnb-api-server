package com.smartFarm.was.domain.request.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class LoginForm {

    @NotNull @Email
    private String memberEmail;

    @NotNull
    private String memberPassword;
}
