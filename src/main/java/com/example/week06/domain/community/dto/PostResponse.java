package com.example.week06.domain.community.dto;

import com.example.week06.domain.community.entity.Post;
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
    private List<CommentResponse> comments;

    public PostResponse(Post post, List<CommentResponse> comments) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getNickname();
        this.district = post.getDistrict();
        this.content = post.getContent();
        this.found_and_lost = post.getLost_and_found();
        this.completed = post.getCompleted();
        this.comments = comments;
    }
}
