package com.example.week06.model;


import com.example.week06.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post_id;

    public Comment(CommentRequestDto commentRequestDto, User user_id, Post post_id){
        this.comment = commentRequestDto.getComment();
        this.user_id = user_id;
        this.post_id = post_id;
    }


    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }
}