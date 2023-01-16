package com.example.week06.domain.community.controller;


import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public void createComment(@RequestBody Map<String, String> map, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.createComment(map.get("content"), postId, userDetails.getUser());
    }

    @DeleteMapping("/api/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
    }

    @PutMapping("/api/comments/{commentId}")
    public void putComment(@PathVariable Long commentId, @RequestBody Map<String, String> map, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.putComment(commentId, map.get("content"), userDetails.getUser());
    }


}