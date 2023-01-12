package com.example.week06.domain.user.dto;

import com.example.week06.domain.user.entity.User;
import com.example.week06.global.security.UserDetailsImpl;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDto {
    private String email;
    private String nickname;

    public UserInfoResponseDto(UserDetailsImpl userDetails){
        User user = userDetails.getUser();

        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}