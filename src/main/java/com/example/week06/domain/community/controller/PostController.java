package com.example.week06.domain.community.controller;

import com.example.week06.domain.community.dto.PostRequest;
import com.example.week06.global.common.SuccessResponse;
import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.community.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    //게시판 목록 조회
    @GetMapping("api/posts")
    public ResponseEntity<?> getPostList(@RequestParam String lost_and_found) {
        String msg = "게시판 목록 조회에 성공하였습니다.";
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, msg, postService.getPostList(lost_and_found)));
    }

    // 게시글 조회
    @GetMapping("api/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        String msg = "게시글 조회에 성공하였습니다.";
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, msg, postService.getPost(postId)));
    }

    // 게시글 작성
    @PostMapping("api/posts")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody PostRequest postRequest ) {
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK,postService.createPost(userDetails.getUser(), postRequest)));
    }

    // 게시글 해결완료
    @PutMapping("/api/posts/{postId}/completed")
    public ResponseEntity<?> complete(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, postService.complete(postId, userDetails.getUser())));
    }

    // 게시글 수정
    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails,
                           @RequestBody PostRequest postRequest ) {
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, postService.updatePost(postRequest, postId, userDetails.getUser())));
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, postService.deletePost(postId, userDetails.getUser())));
    }

}

