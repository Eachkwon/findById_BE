package com.example.week06.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String nickname;

    public User(String email,String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

}
