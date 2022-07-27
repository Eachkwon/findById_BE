package com.example.week06.repository;

import com.example.week06.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostidOrderByCreatedAtDesc(Long postId);
}