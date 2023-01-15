package com.example.week06.domain.community.dto;

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

}
