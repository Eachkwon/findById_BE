package com.example.week06.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequestDto {

    private String email;
    private String password;
}