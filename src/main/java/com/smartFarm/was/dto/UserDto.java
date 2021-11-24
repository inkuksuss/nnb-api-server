package com.smartFarm.was.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {


    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 10)
    private String password;

    @NotNull
    @Size(min = 3, max = 10)
    private String nickname;
}
