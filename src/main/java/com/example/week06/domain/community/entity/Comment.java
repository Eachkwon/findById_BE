package com.example.week06.domain.community.entity;


import com.example.week06.global.Timestamped;
import com.example.week06.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    public Comment(String content, User user, Post post){
        this.content = content;
        this.user = user;
        this.post = post;
    }


    public void update(String content){
        this.content = content;
    }
}