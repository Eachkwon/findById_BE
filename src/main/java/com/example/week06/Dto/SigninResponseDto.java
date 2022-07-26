package com.example.week06.dto;

import com.example.week06.security.UserDetailsImpl;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponseDto {
    private String email;
    private String nickname;
    private String token;

    public SigninResponseDto(UserDetailsImpl userDetails, String token){
        this.email = userDetails.getUsername();
        this.nickname = userDetails.getNickname();
        this.token = "Bearer " + token;
    }
}