package com.example.week06.domain.user.dto;

import com.example.week06.domain.user.entity.User;
import com.example.week06.global.security.UserDetailsImpl;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class MypageResponse {
    private String email;
    private String nickname;

    public MypageResponse(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}