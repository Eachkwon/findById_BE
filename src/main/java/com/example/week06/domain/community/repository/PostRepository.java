package com.example.week06.domain.community.repository;

import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);
    Optional<Post> findByPostIdAndUser(Long postId, User user);
    Optional<Post> deleteByPostIdAndUser(Long postId, User user);

}