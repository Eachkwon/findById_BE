package com.example.week06.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private String nickname;
    private String comment;
    private String createdAt;
}
