package com.example.week06.domain.community.dto;


import com.example.week06.domain.community.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsResponseDto {

    private Long postId;
    private String title;
    private String imageURL;
    private String nickname;
    private String district;
    private String content;
    private String gadaoda;
    private String completed;
    private List<Comment> comments;

}
