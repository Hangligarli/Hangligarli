package com.sparta.hangligarli.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class PostRequestDto {
    @NotBlank(message = "제목입력은 필수 입니다.")
    private String title;
    private String level;
    private int time;
    private int minperson;
    private int maxperson;
    @URL(message = "유효하지 않은 URL 입니다.") //url이 유효하지 않으면 ConstraintViolationException 발생시킴
//    @Pattern(regexp = "(http(s?):)([/|.|\\\\w|\\\\s|-])*\\\\.(?:jpg|gif|png)")
    private String image;
    private String content;
}