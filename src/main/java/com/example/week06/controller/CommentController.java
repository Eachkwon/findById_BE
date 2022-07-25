package com.example.week06.controller;

import com.example.week06.Dto.CommentRequestDto;
import com.example.week06.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public void createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(commentRequestDto, postId, userDetails);
    }

    @DeleteMapping("/api/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId, userDetails);
    }

    @PutMapping("/api/comments/{commentId}")
    public String putComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.putComment(commentRequestDto, commentId);
    }


}
