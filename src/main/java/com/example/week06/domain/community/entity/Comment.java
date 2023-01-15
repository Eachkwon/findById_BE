package com.example.week06.domain.community.entity;


import com.example.week06.domain.community.dto.CommentRequestDto;
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

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, User user, Post post){
        this.comment = commentRequestDto.getComment();
        this.user = user;
        this.post = post;
    }


    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }
}