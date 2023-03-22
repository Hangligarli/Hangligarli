package com.sparta.hangligarli.service;

import com.sparta.hangligarli.dto.LoginRequestDto;
import com.sparta.hangligarli.dto.SignupRequestDto;
import com.sparta.hangligarli.entity.Post;
import com.sparta.hangligarli.entity.User;
import com.sparta.hangligarli.exception.CustomErrorCode;
import com.sparta.hangligarli.exception.CustomException;
import com.sparta.hangligarli.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        // 회원아이디 중복 확인
        boolean found = userRepository.findByUsername(signupRequestDto.getUsername()).isPresent();
        if (found) {
            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
        }

        //닉네임 중복 확인
        found = userRepository.findByNickname(signupRequestDto.getNickname()).isPresent();
        if (found) {
            throw new CustomException(CustomErrorCode.DUPLICATE_NICKNAME);
        }

        User user = new User(signupRequestDto, passwordEncoder.encode(signupRequestDto.getPassword()));
        userRepository.save(user);

        return "회원가입 성공";
    }

    @Transactional(readOnly = true)
    public Map<String, String> login(LoginRequestDto loginRequestDto) {
        // 사용자 확인
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new CustomException(CustomErrorCode.USER_NOT_FOUND)
        );
        // 비밀번호 확인
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw  new CustomException(CustomErrorCode.NOT_PROPER_PASSWORD);
        }

        Map<String, String> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());

        return result;
    }

    @Transactional
    public String checkUsername(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new CustomException(CustomErrorCode.DUPLICATE_USER);
        }
        return "사용가능한 아이디입니다.";
    }

    @Transactional
    public String checkNickname(String nickname) {
        if (userRepository.existsUserByNickname(nickname)) {
            throw new CustomException(CustomErrorCode.DUPLICATE_NICKNAME);
        }
        return "사용가능한 닉네임입니다.";
    }

    @Transactional
    public String unregister(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(
                () -> new CustomException(CustomErrorCode.USER_NOT_FOUND)
        );
        postService.deletePostCreatedByUser(user);
        userRepository.deleteByNickname(nickname);
        return "회원 탈퇴 성공";
    }
}
