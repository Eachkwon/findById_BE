package com.example.week06.dto;

import com.example.week06.model.DistrictEnum;
import com.example.week06.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class PostRequestDto {

    private String title;
    private String imageURL;
    private DistrictEnum district;
    private boolean gadaoda;
    private String content;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .district(district)
                .gadaoda(gadaoda)
                .content(content)
                .build();
    }

}
