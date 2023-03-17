package com.sparta.hangligarli.repository;

import com.sparta.hangligarli.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
