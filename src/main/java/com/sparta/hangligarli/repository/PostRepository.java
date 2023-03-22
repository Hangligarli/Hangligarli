package com.sparta.hangligarli.repository;

import com.sparta.hangligarli.entity.Post;
import com.sparta.hangligarli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByUser(User user);
    List<Post> findAllByUser(User user);

}
