package com.sparta.hangligarli.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class PostRequestDto {
    @NotBlank(message = "제목 입력은 필수 입니다.")
    private String title;
    private String level;
    private int time;
    private int minperson;
    private int maxperson;

    // url의 확장자가 jpg,png,gif 가 맞는지 확인하는 정규식
    @NotBlank()
    @Pattern(regexp = "^(https?|ftp)://[a-zA-Z0-9]+([-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]{2,}(:(\\\\d{1,5}))?([/?#]\\\\S*)?$")
    @Pattern(regexp = "^https?://(?:[a-z0-9\\-]+\\.)+[a-z]{2,6}(?:/[^/#?]+)+\\.(?:jpe?g|png|gif)$")
    private String image;
    private String content;
}