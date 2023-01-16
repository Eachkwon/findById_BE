package com.example.week06.domain.community.controller;

import com.example.week06.domain.community.dto.PostResponse;
import com.example.week06.domain.community.dto.PostRequest;
import com.example.week06.domain.community.dto.PostListResponse;
import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    //게시판 목록 조회
    @GetMapping("api/posts")
    public List<PostListResponse> getPostList(@RequestParam String lost_and_found) {
        return postService.getPostList(lost_and_found);
    }

    // 게시글 조회
    @GetMapping("api/posts/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    // 게시글 작성
    @PostMapping("api/posts")
    public void createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody PostRequest postRequest ) {
        postService.createPost(userDetails.getUser(), postRequest);
    }

    // 게시글 해결완료
    @PutMapping("/api/posts/{postId}/completed")
    public void complete(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.complete(postId, userDetails.getUser());
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postId}")
    public void updatePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody PostRequest postRequest ) {
        postService.updatePost(postRequest, postId, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postUd}")
    public void deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        postService.deletePost(postId, userDetails.getUser());
    }

}

