package com.example.week06.dto;

import com.example.week06.security.UserDetailsImpl;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {
    private String email;
    private String nickname;

    public UserInfoResponseDto(UserDetailsImpl userDetailsImpl){
        this.email = userDetailsImpl.getUsername();
        this.nickname = userDetailsImpl.getNickname();
    }
}