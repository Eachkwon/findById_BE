package com.example.week06.global.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadResponse {

    private String url;
    private String filename;
}