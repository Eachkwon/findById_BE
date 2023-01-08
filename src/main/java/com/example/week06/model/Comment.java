package com.example.week06.model;


import com.example.week06.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
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