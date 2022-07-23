package com.example.week06.controller;

import com.example.week06.Dto.CommentRequestDto;
import com.example.week06.Dto.CommentResponseDto;
import com.example.week06.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/posts/{postId}/comments")
    public void createComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long postId){
        commentService.createComment(commentRequestDto, postId);
    }

    @GetMapping("/api/posts/{postId}/comments")
    public CommentRequestDto getComment(@PathVariable Long restaurantId){
        return commentService.getComment(restaurantId);
    }


}
