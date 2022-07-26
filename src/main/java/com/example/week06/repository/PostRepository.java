package com.example.week06.repository;

import com.example.week06.model.Post;
import com.example.week06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);
    Optional<Post> findById(Long postid);
    Optional<Post> findByPostIdAndUserId(Long postid, User user_Id);
    Optional<Post> deleteByPostIdAndUserId(Long postid, User user_Id);
}