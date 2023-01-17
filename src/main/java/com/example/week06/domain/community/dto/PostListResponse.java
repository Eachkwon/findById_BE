package com.example.week06.domain.community.dto;

import com.example.week06.domain.community.entity.Post;
import lombok.*;

@Getter
@Setter
public class PostListResponse {

    private Long postId;
    private String title;
    private String district;

    public PostListResponse(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.district = post.getDistrict();
    }
}
