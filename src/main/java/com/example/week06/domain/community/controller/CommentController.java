package com.example.week06.domain.community.controller;


import com.example.week06.domain.community.dto.CommentRequestDto;
import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/api/posts/{postId}/comments")
    public void createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String email = userDetails.getUsername();
        commentService.createComment(commentRequestDto, email, postId);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String email = userDetails.getUsername();
        commentService.deleteComment(commentId, email);
    }

    @PutMapping("/api/comments/{commentId}")
    public void putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String email = userDetails.getUsername();
        commentService.putComment(commentId, commentRequestDto, email);
    }


}