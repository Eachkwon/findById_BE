package com.example.week06.domain.community.repository;

import com.example.week06.domain.community.entity.Comment;
import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtDesc(Post post);
    Optional<Comment> findByIdAndUser(Long id, User user);
}