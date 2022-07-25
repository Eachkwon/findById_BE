package com.example.week06.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String email;
    private String nickname;
    private String password;
    private String passwordChk;

}
