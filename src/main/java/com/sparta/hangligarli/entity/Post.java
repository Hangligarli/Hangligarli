package com.sparta.hangligarli.entity;

import com.sparta.hangligarli.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String nickname;

    @ColumnDefault("1")
    @Column(nullable = false)
    private int minperson;

    @Column(nullable = false)
    private int maxperson;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post (PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.nickname = user.getNickname();
        this.minperson = requestDto.getMinperson();
        this.maxperson = requestDto.getMaxperson();
        this.level = requestDto.getLevel();
        this.image = requestDto.getImage();
        this.content = requestDto.getContent();
        this.time = requestDto.getTime();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.nickname = user.getNickname();
        this.minperson = postRequestDto.getMinperson();
        this.maxperson = postRequestDto.getMaxperson();
        this.level = postRequestDto.getLevel();
        this.image = postRequestDto.getImage();
        this.content = postRequestDto.getContent();
        this.time = postRequestDto.getTime();
    }
}
