package com.example.week06.domain.user.dto;

import com.example.week06.domain.user.entity.User;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupRequestDto {
    private String email;
    private String nickname;
    private String password;

    public User createUser(){
        return User.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .authority(new SimpleGrantedAuthority("ROLE_USER"))
                .build();
    }
}