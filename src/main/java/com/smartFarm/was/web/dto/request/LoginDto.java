package com.smartFarm.was.web.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
@RequiredArgsConstructor
public class LoginDto {

    @NotNull @Email
    private String memberEmail;

    @NotNull
    private String memberPassword;
}
