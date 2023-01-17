package com.example.week06.domain.community.repository;

import com.example.week06.domain.community.entity.Post;
import com.example.week06.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);
    List<Post> findAllByLost_and_foundContainingOrderByCreatedAtDesc(String lost_and_found);

}