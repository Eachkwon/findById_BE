package com.example.week06.domain.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPostDto {

    private Long id;
    private String title;
    private String content;
    private String imgURL;
}