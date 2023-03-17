package com.sparta.hangligarli.dto;

import com.sparta.hangligarli.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDtoList {
    private Long id;
    private String title;
    private String image;

    public PostResponseDtoList (Post post)  {
        this.id = post.getId();
        this.title = post.getTitle();
        this.image = post.getImage();
    }
}
