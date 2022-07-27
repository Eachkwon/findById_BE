package com.example.week06.repository;

import com.example.week06.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);
    Optional<Post> findByPostIdAndUserId(Long postId, Long userId);
    Optional<Post> deleteByPostIdAndUserId(Long postId, Long userId);
}