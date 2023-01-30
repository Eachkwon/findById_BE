package com.example.week06.domain.community.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min=1, max=20, message = "제목을 1~20자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max=500, message = "내용을 500자 이내로 입력해주세요.")
    private String content;

    private String found_and_lost;

    private String district;

}
