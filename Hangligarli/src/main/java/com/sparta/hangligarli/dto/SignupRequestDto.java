package com.sparta.hangligarli.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    //영어 소문자, 숫자만 입력 가능. 4글자 이상 12글자 미만으로 입력해야 함
    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,12}$")
    private String username;

    //한글만 입력 가능. 1글자 이상 10글자 미만으로 입력해야 함
    @NotNull
    @Pattern(regexp = "^[가-힣]{1,10}$")
    private String nickname;

    //영어 대소문자, 숫자, 특수기호만 입력 가능. 8글자 이상 15글자 미만으로 입력해야 함
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9`~!@#$%^&*()-_=+]{8,15}$")
    private String password;
}
