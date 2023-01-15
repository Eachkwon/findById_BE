package com.example.week06.domain.community.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long postId;
    private String title;
    private String district;
    private String imageURL;

}
