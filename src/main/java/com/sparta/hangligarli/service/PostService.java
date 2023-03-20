package com.sparta.hangligarli.service;

import com.sparta.hangligarli.dto.PostRequestDto;
import com.sparta.hangligarli.dto.PostResponseDto;
import com.sparta.hangligarli.dto.PostResponseDtoList;
import com.sparta.hangligarli.entity.Post;
import com.sparta.hangligarli.entity.User;
import com.sparta.hangligarli.exception.CustomErrorCode;
import com.sparta.hangligarli.exception.CustomException;
import com.sparta.hangligarli.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public PostResponseDto getPost (Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    //게시물 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDtoList> getPostList () {
        List<Post> postList = postRepository.findAll();

        return postList.stream().map(PostResponseDtoList::new).collect(Collectors.toList());
    }

    //게시물 수정
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = findPost(id);

        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new CustomException(CustomErrorCode.NOT_AUTHOR);
        }

        post.update(postRequestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    //게시물 삭제
    @Transactional
    public String deletePost(Long id, User user) {
        Post post = findPost(id);

        if (!post.getUser().getNickname().equals(user.getNickname())) {
            throw new CustomException(CustomErrorCode.NOT_AUTHOR);
        }

        postRepository.delete(post);
        return "게시물 삭제 성공";
    }

    private Post findPost (Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.POST_NOT_FOUND));
    }
}


