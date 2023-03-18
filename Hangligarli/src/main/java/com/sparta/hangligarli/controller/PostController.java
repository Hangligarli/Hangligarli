package com.sparta.hangligarli.controller;

import com.sparta.hangligarli.dto.PostRequestDto;
import com.sparta.hangligarli.exception.CustomErrorCode;
import com.sparta.hangligarli.exception.CustomException;
import com.sparta.hangligarli.exception.ResponseMessage;
import com.sparta.hangligarli.security.UserDetailsImpl;
import com.sparta.hangligarli.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 입력
    @PostMapping("/api/posts/")
    public ResponseEntity createPost(@Valid @RequestBody PostRequestDto postRequestDto, BindingResult bindingResult,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (bindingResult.hasErrors()) {
            throw new CustomException(CustomErrorCode.NOT_PROPER_URLFORM);
        }
        return ResponseMessage.SuccessResponse(postService.createPost(postRequestDto, userDetails.getUser()),"");
    }

    //게시글 전체 출력
    @GetMapping("/api/posts/list")
    public ResponseEntity getPostList(){
        return ResponseMessage.SuccessResponse("게시물 조회 성공", postService.getPostList());
    }

    //게시글 선택 출력
    @GetMapping("/api/posts/detail/{id}")
    public ResponseEntity getPost(@PathVariable Long id){
        return ResponseMessage.SuccessResponse("게시물 조회 성공", postService.getPost(id));
    }

    //게시글 수정
    @PutMapping("/api/posts/update/{id}")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseMessage.SuccessResponse("게시물 수정 성공", postService.updatePost(id, postRequestDto, userDetails.getUser()));
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseMessage.SuccessResponse(postService.deletePost(id, userDetails.getUser()), "");
    }

}
