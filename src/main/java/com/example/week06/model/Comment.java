package com.example.week06.model;


import javax.persistence.*;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Comment;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "postid", nullable = false)
    private Post post_id;

}
