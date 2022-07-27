package com.example.week06.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long postId;
    private String title;
    private String district;
    private String imageURL;

}
