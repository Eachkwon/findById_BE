package com.example.week06.controller;

import com.example.week06.Dto.PostRequestDto;
import com.example.week06.model.Post;
import com.example.week06.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();
        Post post = postService.createPost(requestDto, userId);

        return post;
    }

    @GetMapping("api/posts")
    public List<Post> getPosts() {
        List<Post> posts = postService.getPosts();

        return posts;
    }

}
