package com.example.week06.domain.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
    private boolean status;
    private String message;

}