package com.sparta.hangligarli.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    @NotNull
    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;

    @NotNull
    @Pattern(regexp = "^[가-힣]+$")
    private String nickname;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
}
