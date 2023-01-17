package com.example.week06.domain.community.repository;

import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
    Optional<Comment> findById(Long id);
}