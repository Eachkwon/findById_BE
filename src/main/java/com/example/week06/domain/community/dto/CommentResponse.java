package com.example.week06.domain.community.dto;

import com.example.week06.domain.community.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {

    private long commentId;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
