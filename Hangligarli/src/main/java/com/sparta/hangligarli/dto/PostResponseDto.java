package com.sparta.hangligarli.dto;
import com.sparta.hangligarli.entity.Post;
import lombok.Getter;



@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private String level;
    private int time;
    private int minperson;
    private int maxperson;
    private String image;
    private String content;

    public PostResponseDto (Post post)  {
        this.id = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.level = post.getLevel();
        this.time = post.getTime();
        this.minperson = post.getMinperson();
        this.maxperson = post.getMaxperson();
        this.image = post.getImage();
        this.content = post.getContent();
    }

}