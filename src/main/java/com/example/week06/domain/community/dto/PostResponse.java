package com.example.week06.domain.community.dto;


import com.example.week06.domain.community.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class PostResponse {

    private Long postId;
    private String title;
    private String nickname;
    private String district;
    private String content;
    private String found_and_lost;
    private String completed;
    private List<Comment> comments;

}
