package com.example.week06.domain.community.dto;

import lombok.*;

@Getter
@Setter
public class PostRequest {

    private String title;
    private String content;
    private String found_and_lost;
    private String district;

}
