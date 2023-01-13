package com.example.week06.domain.user.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    @Column
    private String password;

    public User(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setNickname(String nickname) { this.nickname = nickname; }

}
