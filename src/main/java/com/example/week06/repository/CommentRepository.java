package com.example.week06.repository;

import com.example.week06.model.Comment;
import com.example.week06.model.Post;
import com.example.week06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostOrderByCreatedAtDesc(Post post);
    Optional<Comment> findByIdAndUser(Long id, User user);
}