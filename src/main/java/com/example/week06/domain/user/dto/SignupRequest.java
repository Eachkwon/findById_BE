package com.example.week06.domain.user.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {
    private String email;
    private String nickname;
    private String password;
    private String passwordConfirm;

}