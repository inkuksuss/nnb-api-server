package com.smartFarm.was.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    @NotNull
    @Size(min = 3, max = 10)
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
