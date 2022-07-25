package com.example.week06.controller;

import com.example.week06.Dto.CommentRequestDto;
import com.example.week06.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public void createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        commentService.createComment(commentRequestDto, postId, username);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        commentService.deleteComment(commentId, username);
    }

    @PutMapping("/api/comments/{commentId}")
    public void putComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        commentService.putComment(commentId, commentRequestDto, username);
    }


}
