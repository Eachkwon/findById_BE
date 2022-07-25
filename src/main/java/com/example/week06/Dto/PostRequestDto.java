package com.example.week06.Dto;

import com.example.week06.model.DistrictEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    private String imageURL;
    private String content;
    private boolean odagada;
    private DistrictEnum district;


}
