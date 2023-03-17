package com.sparta.hangligarli.service;

import com.sparta.hangligarli.dto.PostRequestDto;
import com.sparta.hangligarli.dto.PostResponseDto;
import com.sparta.hangligarli.dto.PostResponseDtoList;
import com.sparta.hangligarli.entity.Post;
import com.sparta.hangligarli.entity.User;
import com.sparta.hangligarli.exception.CustomErrorCode;
import com.sparta.hangligarli.exception.CustomException;
import com.sparta.hangligarli.repository.PostRepository;
import com.sparta.hangligarli.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시물 작성
    @Transactional
    public String createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user);
        postRepository.save(post);
        return "게시물 작성 성공";
    }

    //게시물 선택 조회
    public PostResponseDto getPost (Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.POST_NOT_FOUND));
        return new PostResponseDto(post);
    }

    //게시물 목록 조회
    public List<PostResponseDtoList> getPostList () {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDtoList> ResponseList = new ArrayList<>();

        for (Post post : postList) {
            PostResponseDtoList responseDto = new PostResponseDtoList(post);
            ResponseList.add(responseDto);
        }

        return ResponseList;
    }

    //업데이트
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new CustomException(CustomErrorCode.NOT_AUTHOR);
        }

        post.update(postRequestDto, user);
        return new PostResponseDto(post);
    }


    public String deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new CustomException(CustomErrorCode.NOT_AUTHOR);
        }

        postRepository.delete(post);
        return "게시물 삭제 성공";
    }
}


