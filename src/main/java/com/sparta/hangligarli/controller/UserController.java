package com.sparta.hangligarli.controller;

import com.sparta.hangligarli.dto.LoginRequestDto;
import com.sparta.hangligarli.dto.SignupRequestDto;
import com.sparta.hangligarli.exception.CustomErrorCode;
import com.sparta.hangligarli.exception.CustomException;
import com.sparta.hangligarli.exception.ResponseMessage;
import com.sparta.hangligarli.jwt.JwtUtil;
import com.sparta.hangligarli.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomException(CustomErrorCode.NOT_PROPER_INPUTFORM);
        }
        return ResponseMessage.SuccessResponse(userService.signup(signupRequestDto) , "");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Map<String, String> user = userService.login(loginRequestDto);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get("username"), user.get("nickname")));
        return ResponseMessage.SuccessResponse("너무해", "");
    }

    @PostMapping("/check/username")
    public ResponseEntity checkUsername(@RequestBody HashMap<String, String> username) {
        return ResponseMessage.SuccessResponse(userService.checkUsername(username.get("username")), "");
    }

    @PostMapping("/check/nickname")
    public ResponseEntity checkNickname(@RequestBody HashMap<String, String> nickname) {
        return ResponseMessage.SuccessResponse(userService.checkNickname(nickname.get("nickname")), "");
    }

}
// 너무해