package com.example.week06.model;


import com.example.week06.Dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "userid", nullable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    private Post post_id;

    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }
}
