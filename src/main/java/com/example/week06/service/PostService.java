package com.example.week06.service;

import com.example.week06.Dto.PostRequestDto;
import com.example.week06.model.Post;
import com.example.week06.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostRequestDto requestDto, Long userId) {
        Post post = new Post(requestDto, userId);

        postRepository.save(post);

        return post;
    }

    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();

        return posts;
    }
}