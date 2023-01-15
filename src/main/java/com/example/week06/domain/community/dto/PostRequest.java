package com.example.week06.domain.community.dto;

import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostRequest {

    private String title;
    private String content;
    private String gadaoda;
    private String district;



    public PostRequest toEntity() {
        return PostRequest.builder()
                .title(title)
                .content(content)
                .gadaoda(gadaoda)
                .district(district)
                .build();
    }

}
